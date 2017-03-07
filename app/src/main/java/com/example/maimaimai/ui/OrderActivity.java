package com.example.maimaimai.ui;

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
import com.example.maimaimai.cls.AddressInfo;
import com.example.maimaimai.cls.Username_All;
import com.example.maimaimai.utils.DownImage;
import com.example.maimaimai.utils.MyApp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class OrderActivity extends Activity implements OnClickListener {

	//返回控件
	ImageView iv_back;
	ImageView iv_pAddress;
	TextView tv_myaddress;
	TextView o_productname;
	TextView o_price;
	ImageView iv_picture;
	TextView tv_goodsNumber;
	TextView tv_goodsPrice;
	TextView tv_total;
	TextView tv_tc;
	String url_host = Username_All.getUrl_host();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order);
		Log.d("Tag", "onCreate");
		iv_back = (ImageView) findViewById(R.id.iv_back);
		iv_pAddress = (ImageView) findViewById(R.id.iv_pAddress);
		tv_myaddress = (TextView) findViewById(R.id.tv_myaddress);
		iv_picture = (ImageView) findViewById(R.id.iv_picture);
		o_productname = (TextView) findViewById(R.id.o_productname);
		o_price = (TextView) findViewById(R.id.o_price);
		tv_tc = (TextView) findViewById(R.id.tv_tc);
		tv_goodsNumber = (TextView) findViewById(R.id.tv_goodsNumber);
		tv_goodsPrice = (TextView) findViewById(R.id.tv_goodsPrice);
		tv_total = (TextView) findViewById(R.id.tv_total);


		/**返回的监听有空指针异常待解决,解决办法直接finish()掉*/
		iv_back.setOnClickListener(this);
		iv_pAddress.setOnClickListener(this);

		String url = getIntent().getStringExtra("url");
		Log.d("Tag", "OrderActivity url " + url);
		String productname = getIntent().getStringExtra("productname");
		String price = getIntent().getStringExtra("price");
		int count = getIntent().getIntExtra("count", -1);
		double total = count*Double.parseDouble(price);
		String ss_total = String.valueOf(total);
		String type = getIntent().getStringExtra("type");
		String color = getIntent().getStringExtra("color");
		//设置图片、名字、价格、数量
		new DownImage(this).getPicture(url, iv_picture);
		o_productname.setText(productname);
		o_price.setText("￥" + ss_total);
		tv_tc.setText("类型：" + type + "颜色：" + color);
		tv_goodsNumber.setText("共 "+ count + "商品");
		tv_goodsPrice.setText("合计：￥ "+ ss_total + "元");//合计：￥XX元
		tv_total.setText("实付款：￥ "+ ss_total + "元");
		//请求地址requestAddress
		requestAddress();
	}


	private void requestAddress() {
		// TODO Auto-generated method stub
		String add_url = url_host + "requestAddress.jsp";
		StringRequest request = new StringRequest(Method.POST, add_url, new Listener<String>() {

			@Override
			public void onResponse(String response) {
				// TODO Auto-generated method stub
				Log.d("Tag", "response111111 =" + "请求成功");
				JSONArray array;
				try {
					array = new JSONArray(response);
					for (int i = 0; i < array.length(); i++) {
						JSONObject object = array.getJSONObject(i);
						String address = object.getString("address");
						Log.d("Text", address);

					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}, new Response.ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				// TODO Auto-generated method stub
				Log.d("Tag", "response222222222 =" + "请求失败");
			}
		}){
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				// TODO Auto-generated method stub
				HashMap<String, String> map = new HashMap<String, String>();
				String onlyone = "true";
				map.put("onlyone", onlyone);
				return map;
			}
		};

		request.setTag("请求地址信息");
		MyApp.getRequestQueue().add(request);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
			case R.id.iv_back:
//			Intent intent = new Intent();
//			intent.setClass(OrderActivity.this, CommodityActivity.class);
//			startActivity(intent);
				finish();
				break;
			case R.id.iv_pAddress:
				//指向地址界面
				Intent intent1 = new Intent();
				intent1.setClass(OrderActivity.this, AddressActivity.class);
				startActivity(intent1);
				break;



		}
	}

}