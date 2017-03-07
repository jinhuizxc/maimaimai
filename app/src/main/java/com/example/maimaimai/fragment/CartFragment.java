package com.example.maimaimai.fragment;

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
import com.example.maimaimai.adapter.ProductCartAdapter;
import com.example.maimaimai.cls.ProductCartInfo;
import com.example.maimaimai.cls.Username_All;
import com.example.maimaimai.ui.LoginActivity;
import com.example.maimaimai.ui.MainActivity;
import com.example.maimaimai.ui.OrderActivity;
import com.example.maimaimai.utils.MyApp;



import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

public class CartFragment extends Fragment implements OnClickListener, OnItemClickListener {

	Button bt_denglu, cart_market,bt_getcount;
	View v;
	String url_host = Username_All.getUrl_host();
	public static ArrayList<ProductCartInfo> data = new ArrayList<ProductCartInfo>();
	public static LinearLayout linearlayout_product_cart_unlogin,
			linearlayout_product_cart_nodata, linearlayout_product_cart_data;
	public static ListView listview_product_cart_data;
	ProductCartAdapter productCartAdapter;
	//要传过去的数据
	String url, name, color, type, price;
	int num;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		Log.d("Tag", "CartFragment onCreateView");
		v = inflater.inflate(R.layout.cart, null);
		initView();
		setListener();
		productCartAdapter = new ProductCartAdapter(data, CartFragment.this.getActivity());
		listview_product_cart_data.setAdapter(productCartAdapter);
		listview_product_cart_data.setOnItemClickListener(this);

		return v;

	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		if (!Username_All.getName().equals("null")) {
			Log.d("Tag", "CartFragment" + Username_All.getName());
			linearlayout_product_cart_unlogin.setVisibility(View.GONE);
			/**如果用户已经登录,进行判断，如果数据库有关于用户的商品信息将其请求下来显示，如果没有则显示nodata*/
			//请求购物车数据
			StringRequest request = new StringRequest(Method.POST, url_host
					+ "productcart.jsp", new Listener<String>() {

				@Override
				public void onResponse(String response) {
					// TODO Auto-generated method stub
					Log.d("Tag", "responseYYYY = " + response);
					JSONObject obj;
					try {
						obj = new JSONObject(response);
						String data = obj.getString("data");
						Log.d("Tag", "data = " + data);
						if (data.equals("nodata")) {
							Log.d("Tag", "linearlayout_product_cart_nodata");
							CartFragment.linearlayout_product_cart_nodata
									.setVisibility(View.VISIBLE);
						} else if (data.equals("havedata")) {
							Log.d("Tag", "linearlayout_product_cart_data");
							CartFragment.linearlayout_product_cart_data
									.setVisibility(View.VISIBLE);
							linearlayout_product_cart_data.setFocusable(true);
							JSONArray array = obj.getJSONArray("array");
							for (int i = 0; i < array.length(); i++) {
								JSONObject object = array.getJSONObject(i);
								url = object.getString("url");
								name = object.getString("name");
								num = object.getInt("num");
								color = object.getString("color");
								type = object.getString("type");
								price = object.getString("price");
								CartFragment.data.add(new ProductCartInfo(url,
										name, num, color, type, price));
							}
							productCartAdapter.notifyDataSetChanged();
						}
					} catch (JSONException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
			}, new Response.ErrorListener() {

				@Override
				public void onErrorResponse(VolleyError error) {
					// TODO Auto-generated method stub
					Log.d("Tag", "responseNNNN = " + error);
				}
			}) {
				@Override
				protected Map<String, String> getParams()
						throws AuthFailureError {
					// TODO Auto-generated method stub
					HashMap<String, String> map = new HashMap<String, String>();
					map.put("username", Username_All.getName());
					return map;
				}
			};

			request.setTag("product_cart_info");
			MyApp.getRequestQueue().add(request);

		}


	}
	//设置监听
	private void setListener() {
		// TODO Auto-generated method stub
		bt_denglu.setOnClickListener(this);
		cart_market.setOnClickListener(this);
		bt_getcount.setOnClickListener(this);
	}
	private void initView() {
		// TODO Auto-generated method stub
		bt_denglu = (Button) v.findViewById(R.id.cart_login);
		cart_market = (Button) v.findViewById(R.id.cart_market);
		bt_getcount = (Button) v.findViewById(R.id.button_product_cart_getcount);
		linearlayout_product_cart_unlogin = (LinearLayout) v.findViewById(R.id.linearlayout_product_cart_unlogin);
		linearlayout_product_cart_nodata = (LinearLayout) v.findViewById(R.id.linearlayout_product_cart_nodata);
		linearlayout_product_cart_data = (LinearLayout) v.findViewById(R.id.linearlayout_product_cart_data);
		listview_product_cart_data = (ListView) v.findViewById(R.id.listview_product_cart_data);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		//跳转登录
		if (v.getId() == R.id.cart_login) {
			Intent intent = new Intent(CartFragment.this.getActivity(), LoginActivity.class);
//			intent.setPackage(getActivity().getPackageName());
			startActivityForResult(intent, 1);
		} else if (v.getId() == R.id.cart_market) {
//			MainActivity.viewPager.setCurrentItem(0);
		}else if(v.getId() == R.id.button_product_cart_getcount){
			//进入订单界面
			Intent intent = new Intent();
			intent.setClass(CartFragment.this.getActivity(), OrderActivity.class);

			intent.putExtra("url", url);
			Log.d("Tag", "CartFragment url " + url);
			intent.putExtra("productname", name);
			intent.putExtra("price", price);
			intent.putExtra("count", num);
			intent.putExtra("type", type);// str_type,str_color;
			intent.putExtra("color", color);
			startActivity(intent);
		}
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		Log.d("Tag", "onActivityResult");
		if (data != null && requestCode == 1 && resultCode == 2) {

			Username_All.setName(data.getStringExtra("name"));
			Username_All.setStauts(true);
//			Mine.iv_head.setImageResource(R.drawable.loginsuccess_header);
//			Mine.bt_username.setText(data.getStringExtra("log_username"));
			linearlayout_product_cart_unlogin.setVisibility(View.GONE);
			linearlayout_product_cart_nodata.setVisibility(View.VISIBLE);

		}

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
							long id) {
		// TODO Auto-generated method stub
		Log.d("Tag", "positionYYY" + position);

	}


}
