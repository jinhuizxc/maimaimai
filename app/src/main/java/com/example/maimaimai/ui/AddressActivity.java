package com.example.maimaimai.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Request.Method;
import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.maimaimai.R;
import com.example.maimaimai.adapter.addressAdapter;
import com.example.maimaimai.cls.AddressInfo;
import com.example.maimaimai.cls.ProductInfo;
import com.example.maimaimai.cls.Username_All;
import com.example.maimaimai.utils.MyApp;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

public class AddressActivity extends Activity implements OnClickListener, OnItemClickListener {

	Button bt_addNewAddress;
	ImageView iv_aBack;
	ListView listView;
	addressAdapter adapter;
	ArrayList<AddressInfo> data;
	String url_host = Username_All.getUrl_host();
	Context context;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_address);

		bt_addNewAddress = (Button) findViewById(R.id.bt_addNewAddress);
		iv_aBack = (ImageView) findViewById(R.id.iv_aBack);
		//从数据库请求地址信息
		requestAddress();
		//适配器绑定数据
		data = new ArrayList<AddressInfo>();
//		AddressInfo info = new AddressInfo(name, phone, address, isCheck, bianji, delete);
//		data.add(info);
		adapter = new addressAdapter(data, this);
		listView = (ListView) findViewById(R.id.listView1);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(this);

		bt_addNewAddress.setOnClickListener(this);
		iv_aBack.setOnClickListener(this);
		//意图过滤器加载对应字符串，动态注册广播,
		IntentFilter filter = new IntentFilter("delete");
		registerReceiver(new Delete(), filter);
		IntentFilter filter1 = new IntentFilter("modify");
		registerReceiver(new Modify(), filter1);

	}

	//内部类继承BroadcastReceiver实现适配器的刷新
	class Delete extends BroadcastReceiver{

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			adapter.notifyDataSetChanged();
		}

	}

	class Modify extends BroadcastReceiver{

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub

			intent.setClass(AddressActivity.this, addNewAddress.class);
			startActivity(intent);
		}

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
						String name = object.getString("name");
						String phone = String.valueOf(object.getInt("phone"));
						String address = object.getString("address");
//						String choice = object.getString("choice");
						boolean isCheck = false;
						String bianji = "编辑";
						String delete = "删除";
						Log.d("Text", name + " " + phone + " " + address);
						AddressInfo info = new AddressInfo(name, phone, address, isCheck, bianji, delete);
						data.add(info);
						adapter.notifyDataSetChanged();
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

			case R.id.bt_addNewAddress:
				Intent intent = new Intent();
				intent.setClass(AddressActivity.this, addNewAddress.class);
				startActivity(intent);
				break;
			case R.id.iv_aBack:
//			Intent intent1 = new Intent();
//			intent1.setClass(AddressActivity.this, OrderActivity.class);
//			startActivity(intent1);
				finish();
				break;

			default:
				break;
		}

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
							long id) {
		// TODO Auto-generated method stub
		Log.d("Tag", "position01 = " + position);
	}
}
