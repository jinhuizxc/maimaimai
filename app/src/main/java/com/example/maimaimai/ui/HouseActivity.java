package com.example.maimaimai.ui;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.Request.Method;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.StringRequest;
import com.example.maimaimai.R;
import com.example.maimaimai.adapter.CategoryItemAdapter;
import com.example.maimaimai.cls.ProductInfo;
import com.example.maimaimai.cls.Username_All;
import com.example.maimaimai.utils.MyApp;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewDebug.IntToString;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class HouseActivity extends Activity implements OnItemClickListener {

	private Gallery gallery;
	private ImageAdapter imageAdapter;
	TextView tv1;
	TextView tv2;
	// 声明图片的数组
	private int[] resIds = { R.drawable.gallery01, R.drawable.gallery02,
			R.drawable.gallery03, R.drawable.gallery04};

	ArrayList<ProductInfo> data = new ArrayList<ProductInfo>();
	ListView listView;
	String url_host = Username_All.getUrl_host();
	CategoryItemAdapter adapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_houseappliances);

		gallery = (Gallery) findViewById(R.id.houseappliances_gallery);
		imageAdapter = new ImageAdapter(this);
		gallery.setAdapter(imageAdapter);

		tv1 = (TextView) findViewById(R.id.tv1);
		tv2 = (TextView) findViewById(R.id.tv2);
		listView = (ListView) findViewById(R.id.listView1);
		//请求服务器图片;
		RequestImage();
	}

	private void RequestImage() {
		// TODO Auto-generated method stub

		final ProgressDialog dialog = ProgressDialog.show(HouseActivity.this, "请稍等", "数据加载中");

		dialog.setCancelable(true);//设置进度条是否可以按退回键取消
		dialog.setCanceledOnTouchOutside(true);//设置点击进度对话框外的区域对话框消失

		final String title = getIntent().getStringExtra("title");

		StringRequest request = new StringRequest(Method.POST,
				url_host + "categorymesssageinfo.jsp", new Listener<String>() {

			@Override
			public void onResponse(String response) {
				// TODO Auto-generated method stub
				Log.d("Tag", " response = " + response);

				try {
					JSONArray array = new JSONArray(response);
					for (int i = 0; i < array.length(); i++) {
						JSONObject object = array.getJSONObject(i);
						String p_url = object.getString("url");
						String p_name = object.getString("productname");
						String p_price = object.getString("price");
						Log.d("Tag", p_url + " " + p_name + " " + p_price);
						data.add(new ProductInfo(url_host + p_url,
								p_name, p_price));

					}
					dialog.dismiss();
					adapter.notifyDataSetChanged();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}, new Response.ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				// TODO Auto-generated method stub
				Log.d("Tag", " error = " + error);
			}
		}) {
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				// TODO Auto-generated method stub
				HashMap<String, String> map = new HashMap<String, String>();

				map.put("category", title);
				return map;
			}
		};
		request.setTag("category_info");
		MyApp.getRequestQueue().add(request);

		tv1.setText(title + "类");
		tv2.setText(title + "类");
		adapter = new CategoryItemAdapter(data, HouseActivity.this);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
							long id) {
		// TODO Auto-generated method stub
		//点击item项将图片、名字、价格、传过来,进行界面的跳转
		Intent intent = new Intent();
		intent.putExtra("url", data.get(position).getUrl());
		intent.putExtra("productname", data.get(position).getProductname());
		intent.putExtra("price", data.get(position).getPrice());
		Log.d("Tag", "HouseActivity url" + data.get(position).getUrl());
		Log.d("Tag", "HouseActivity url" + data.get(position).getProductname());
		Log.d("Tag", "HouseActivity url" + data.get(position).getPrice());
		intent.setClass(HouseActivity.this, CommodityActivity.class);
		startActivity(intent);

	}


	//图片适配器
	public class ImageAdapter extends BaseAdapter {

		private Context context;

		public ImageAdapter(Context context) {
			this.context = context;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return Integer.MAX_VALUE; // 返回当前适配器最大值，是整数类型的。
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return resIds[position]; // 返回数组resIds[]的位置信息
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub

			ImageView imageView = new ImageView(context);
			// 表示往imageView中填充图片的信息，填充的时候传递是resIds[]的下标
			imageView.setImageResource(resIds[position % resIds.length]);
			// 表示当前ImageView 把图片 不按比例扩大/缩小到View的大小显示
			imageView.setScaleType(ImageView.ScaleType.FIT_XY);
			// 设置ImageView的大小
			imageView.setLayoutParams(new Gallery.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			return imageView;
		}

	}

}
