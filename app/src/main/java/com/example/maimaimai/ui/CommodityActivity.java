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
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.StringRequest;
import com.example.maimaimai.R;
import com.example.maimaimai.adapter.PjAdapter;
import com.example.maimaimai.adapter.ProductCartAdapter;
import com.example.maimaimai.cls.Pjlist;
import com.example.maimaimai.cls.ProductCartInfo;
import com.example.maimaimai.cls.ProductInfo;
import com.example.maimaimai.cls.Username_All;
import com.example.maimaimai.cls.Utility;
import com.example.maimaimai.fragment.CartFragment;
import com.example.maimaimai.utils.DownImage;
import com.example.maimaimai.utils.MyApp;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 解决listView放在ScrowView里面只显示一行的问题
 */
public class CommodityActivity extends Activity implements OnClickListener,
		OnItemClickListener {

	ImageView iv_back;
	ImageView iv_refresh;
	ImageView iv_commodity;
	TextView tv_content;
	TextView tv_price;
	// 设置隐藏界面控件
	ImageView iv_iv;
	TextView tv_productname;
	TextView tv_storageprice;
	ImageView iv_del;
	TextView tv_type01, tv_type02, tv_type03, tv_type04;
	TextView tv_color01, tv_color02;
	boolean isSelect = false;// 未被选择
	TextView tv_reduce, tv_num, tv_add;
	Button bt_ok;
	int count = 1;
	boolean isBuy = false;
	// 保存颜色,类型
	String str_type, str_color;
	// 收藏
	ImageView iv_collect;
	boolean isCollect;
	// 加入购物车-立即购买
	TextView tv_put_in;
	TextView tv_buy_now;
	// 评价列表
	ListView listView;
	PjAdapter adapter;
	ArrayList<Pjlist> data;
	LinearLayout ll01;
	/** 用于设置背景暗淡 */
	LinearLayout ll02;
	boolean isClickBuy;

	// 请求服务器网址
	String url_host = Username_All.getUrl_host();
	ArrayList<ProductInfo> data1 = new ArrayList<ProductInfo>();
	// 设置上传数据库的信息
	String url;
	String productname;
	String price;
//	String

	// 购物车信息-适配器
	ProductCartAdapter productCartAdapter;
	ProductCartInfo productCartInfo;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_commodity);
		Log.d("Tag", "CommodityActivity onCreate");

		iv_back = (ImageView) findViewById(R.id.iv_back);
		iv_refresh = (ImageView) findViewById(R.id.iv_refresh);
		iv_commodity = (ImageView) findViewById(R.id.iv_commodity);
		tv_content = (TextView) findViewById(R.id.tv_content);
		iv_collect = (ImageView) findViewById(R.id.collect);
		tv_price = (TextView) findViewById(R.id.tv_price);
		tv_put_in = (TextView) findViewById(R.id.put_in);
		tv_buy_now = (TextView) findViewById(R.id.buy_now);
		ll01 = (LinearLayout) findViewById(R.id.ll01);
		ll02 = (LinearLayout) findViewById(R.id.ll02);

		iv_iv = (ImageView) findViewById(R.id.iv_iv);
		tv_productname = (TextView) findViewById(R.id.tv_productname);
		tv_storageprice = (TextView) findViewById(R.id.tv_storageprice);
		iv_del = (ImageView) findViewById(R.id.iv_del);

		tv_type01 = (TextView) findViewById(R.id.tv_type01);
		tv_type02 = (TextView) findViewById(R.id.tv_type02);
		tv_type03 = (TextView) findViewById(R.id.tv_type03);
		tv_type04 = (TextView) findViewById(R.id.tv_type04);
		tv_color01 = (TextView) findViewById(R.id.tv_color01);
		tv_color02 = (TextView) findViewById(R.id.tv_color02);

		tv_reduce = (TextView) findViewById(R.id.tv_reduce);
		tv_num = (TextView) findViewById(R.id.tv_num);
		tv_add = (TextView) findViewById(R.id.tv_add);
		bt_ok = (Button) findViewById(R.id.bt_ok);

		iv_back.setOnClickListener(this);
		iv_refresh.setOnClickListener(this);
		iv_collect.setOnClickListener(this);
		tv_put_in.setOnClickListener(this);
		tv_buy_now.setOnClickListener(this);

		iv_del.setOnClickListener(this);
		tv_type01.setOnClickListener(this);
		tv_type02.setOnClickListener(this);
		tv_type03.setOnClickListener(this);
		tv_type04.setOnClickListener(this);
		tv_color01.setOnClickListener(this);
		tv_color02.setOnClickListener(this);
		tv_reduce.setOnClickListener(this);
		tv_add.setOnClickListener(this);
		bt_ok.setOnClickListener(this);

		Intent intent = getIntent();
		url = intent.getStringExtra("url");
		productname = getIntent().getStringExtra("productname");
		price = getIntent().getStringExtra("price");

		Log.d("Tag", "Commotity  url " + url);
		Log.d("Tag", "Commotity  productname " + productname);
		Log.d("Tag", "Commotity  price " + price);
		/** 误区：不用每次都请求数据，将前面的数据通过意图传过来就好了！ */
		// 设置图片、内容、价格
		new DownImage(this).getPicture(url, iv_commodity);
		tv_content.setText(productname);
		tv_price.setText("￥" + price);
		/** 评价列表 */
		// 用户头像
		int imageIds[] = { R.drawable.a01, R.drawable.a02, R.drawable.a03,
				R.drawable.a04, R.drawable.a05 };
		String tv_pj1s[] = { "京东，你的购物首选！", "京东，你的购物首选！", "京东，你的购物首选！",
				"京东，你的购物首选！", "京东，你的购物首选！" };
		String tv_pj2s[] = {
				"你们家的商品真的不错，我也很是喜欢，给好评！不过店家记得把承诺的返回现金打到本人支付宝：XXXXX。谢谢啦。",
				"给好评啊！要返现哦", "东西用起来不错,很不错的合作！", "不好意思，东西不好用,给差评！",
				"回复晚了，给好评吧，将就着用！" };
		String tv_pj3s[] = { "2016_11_11", "2016_11_11", "2016_11_11",
				"2016_11_11", "2016_11_11" };
		String tv_pj4s[] = { "我的京东自营店！", "我的京东自营店！", "我的京东自营店！", "我的京东自营店！",
				"我的京东自营店！" };

		data = new ArrayList<Pjlist>();
		for (int i = 0; i < tv_pj4s.length; i++) {
			Pjlist pjlist = new Pjlist(imageIds[i], tv_pj1s[i], tv_pj2s[i],
					tv_pj3s[i], tv_pj4s[i]);
			Log.d("Tag", "pjlist" + pjlist);
			data.add(pjlist);
		}
		adapter = new PjAdapter(data, this);
		listView = (ListView) findViewById(R.id.listViewpj);
		listView.setAdapter(adapter);
		Utility.setListViewHeightBasedOnChildren(listView);

//		//购物车信息
//		productCartInfo = new ProductCartInfo(url, name, num, color, type, price2)


	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
			case R.id.iv_back:
				// 销毁当前activity,回到上一个activity
				CommodityActivity.this.finish();
				break;
			case R.id.iv_refresh:
				Toast.makeText(this, "此功能未开放", Toast.LENGTH_SHORT).show();
				break;
			case R.id.collect:
				// 收藏，1.加载到数据库，2.弹出温馨提示
				if (isCollect == false) {
					if (Username_All.getName().equals("null")) {
						Intent intent = new Intent(CommodityActivity.this,
								LoginActivity.class);
						startActivityForResult(intent, 3);
					} else {
						// 添加收藏
						AddCollect();
					}

				} else {
					// 弹出对话框
					Builder builder = new Builder(this);
					builder.setTitle("是否删除此收藏？");
					builder.setPositiveButton("确定",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
													int which) {
									// TODO Auto-generated method stub
									// 删除数据库资源、更新视图
									// http://localhost:8080/project_myapp/deleteCollect.jsp
									String Surl = url_host + "deleteCollect.jsp";
									StringRequest request = new StringRequest(
											Method.POST, Surl,
											new Listener<String>() {
												@Override
												public void onResponse(
														String response) {
													// TODO Auto-generated method
													// stub
													Log.d("Tag", "response"
															+ response);
													iv_collect
															.setImageResource(R.drawable.collect_normal);
													isCollect = false;
												}
											}, new ErrorListener() {

										@Override
										public void onErrorResponse(
												VolleyError error) {
											// TODO Auto-generated method
											// stub

										}
									}) {
										@Override
										protected Map<String, String> getParams()
												throws AuthFailureError {
											// TODO Auto-generated method stub
											HashMap<String, String> map = new HashMap<String, String>();
											// 截取字符串
											String aa[] = url.split("/");
											String p_url = aa[4];
											map.put("url", p_url);
											return map;
										}
									};

									request.setTag("取消收藏");
									MyApp.getRequestQueue().add(request);
								}
							});

					builder.setNegativeButton("取消", null);
					builder.create();
					builder.show();
				}

				break;
			case R.id.put_in:

				if (Username_All.getName().equals("null")) {
					Intent intent = new Intent(CommodityActivity.this,
							LoginActivity.class);
					startActivityForResult(intent, 3);
				} else {

					// 弹出加入购物车activity,设置图片、设置类型、颜色
					setBackgroundBlack(ll02, 0);
					isBuy = false;
					ll02.setFocusable(true);
					ll01.setFocusable(false);
					requestTC();
				}

				break;
			case R.id.buy_now:
				if (Username_All.getName().equals("null")) {
					Intent intent = new Intent(CommodityActivity.this,
							LoginActivity.class);
					startActivityForResult(intent, 3);
				} else {

					// 弹出一个activity选择类型，通过点击按钮进入结算界面
					setBackgroundBlack(ll02, 0);
					isBuy = true;
					// setBackgroundBlack(ll01, 1); // 1 不可见;0 可见
					ll02.setFocusable(true);
					ll01.setFocusable(false);
					requestTC();
				}

				break;
			case R.id.iv_del:
				// 隐藏布局
				setBackgroundBlack(ll02, 1);
				ll02.setFocusable(false);
				ll01.setFocusable(true);
				// 如何将count置为1？？
				count = 1;

				tv_num.setText("1");
				break;
			case R.id.tv_type01:
				isSelect = true;
				tv_type01.setBackgroundResource(R.drawable.yuanjiao_choice);
				tv_type02.setBackgroundResource(R.drawable.yuanjiao);
				tv_type03.setBackgroundResource(R.drawable.yuanjiao);
				tv_type04.setBackgroundResource(R.drawable.yuanjiao);
				str_type = tv_type01.getText().toString();
				break;
			case R.id.tv_type02:
				isSelect = true;
				tv_type02.setBackgroundResource(R.drawable.yuanjiao_choice);
				tv_type01.setBackgroundResource(R.drawable.yuanjiao);
				tv_type03.setBackgroundResource(R.drawable.yuanjiao);
				tv_type04.setBackgroundResource(R.drawable.yuanjiao);
				str_type = tv_type02.getText().toString();
				break;
			case R.id.tv_type03:
				isSelect = true;
				tv_type03.setBackgroundResource(R.drawable.yuanjiao_choice);
				tv_type01.setBackgroundResource(R.drawable.yuanjiao);
				tv_type02.setBackgroundResource(R.drawable.yuanjiao);
				tv_type04.setBackgroundResource(R.drawable.yuanjiao);
				str_type = tv_type03.getText().toString();
				break;
			case R.id.tv_type04:
				isSelect = true;
				tv_type04.setBackgroundResource(R.drawable.yuanjiao_choice);
				tv_type01.setBackgroundResource(R.drawable.yuanjiao);
				tv_type02.setBackgroundResource(R.drawable.yuanjiao);
				tv_type03.setBackgroundResource(R.drawable.yuanjiao);
				str_type = tv_type04.getText().toString();
				break;
			case R.id.tv_color01:
				tv_color01.setBackgroundResource(R.drawable.yuanjiao_choice);
				tv_color02.setBackgroundResource(R.drawable.yuanjiao);
				str_color = tv_color01.getText().toString();
				break;
			case R.id.tv_color02:
				tv_color02.setBackgroundResource(R.drawable.yuanjiao_choice);
				tv_color01.setBackgroundResource(R.drawable.yuanjiao);
				str_color = tv_color02.getText().toString();
				break;

			case R.id.tv_reduce:
				count--;
				if (count < 1) {
					count = 1;
				}
				tv_num.setText(String.valueOf(count));
				break;

			case R.id.tv_add:

				count++;
				tv_num.setText(String.valueOf(count));
				break;
			case R.id.bt_ok:
				if (isBuy == false) {
//				if (Username_All.getName().equals("null")) {
//					Intent intent = new Intent(CommodityActivity.this,
//							LoginActivity.class);
//					startActivityForResult(intent, 3);
//				}else
					// 添加购物车
					addProductCart();
				} else {
					// 界面跳转到订单界面
					buyProduct();
				}

				break;

		}
	}

	private void buyProduct() {
		// TODO Auto-generated method stub
		Toast.makeText(this, "进入结算界面", Toast.LENGTH_SHORT).show();
		Intent intent = new Intent();
		intent.putExtra("url", url);
		intent.putExtra("productname", productname);
		intent.putExtra("price", price);
		intent.putExtra("count", count);
		intent.putExtra("type", str_type);// str_type,str_color;
		intent.putExtra("color", str_color);
		intent.setClass(CommodityActivity.this, OrderActivity.class);
		startActivity(intent);
	}

	private void AddCollect() {
		// TODO Auto-generated method stub
		// 将图片、内容、价格 上传到服务器
		String Surl = url_host + "Collect.jsp";
		// http://localhost:8080/project_myapp/Collect.jsp
		StringRequest request = new StringRequest(Method.POST, Surl,
				new Listener<String>() {

					@Override
					public void onResponse(String response) {
						// TODO Auto-generated method stub
						Log.d("Tag", "response = " + "请求成功");
						iv_collect
								.setImageResource(R.drawable.collect_selected);
						Toast.makeText(CommodityActivity.this, "收藏成功",
								Toast.LENGTH_SHORT).show();
						isCollect = true;
					}
				}, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				// TODO Auto-generated method stub
				Log.d("Tag", "response = " + "请求失败");
			}
		}) {
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				// TODO Auto-generated method stub
				HashMap<String, String> map = new HashMap<String, String>();
				// 截取字符串
				String aa[] = url.split("/");
				String p_url = aa[4];
				map.put("url", p_url);
				map.put("productname", productname);
				map.put("price", price);
				map.put("username", Username_All.getName());
//				Log.d("name", Username_All.getName());
				return map;
			}
		};
		request.setTag("收藏");
		MyApp.getRequestQueue().add(request);
	}

	private void addProductCart() {
		// TODO Auto-generated method stub
		String c_url = url_host + "addproductcart.jsp"; // http://localhost:8080/project_myapp/shoppingCar.jsp
		StringRequest request = new StringRequest(Method.POST, c_url,
				new Listener<String>() {

					@Override
					public void onResponse(String response) {
						// TODO Auto-generated method stub
						Log.d("Tag", "response" + "请求成功");
						try {
							JSONObject object = new JSONObject(response);
							String data = object.getString("data");
							if (data.equals("success")) {
								Toast.makeText(CommodityActivity.this, "购物车添加成功",
										Toast.LENGTH_SHORT).show();
								//将有购物信息的布局data加载进来
								showProductCart();

							}
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}


				}, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				// TODO Auto-generated method stub

			}
		}) {
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				// TODO Auto-generated method stub
				HashMap<String, String> map = new HashMap<String, String>();
				// 截取字符串
				String aa[] = url.split("/");
				String sc_url = aa[4];
				String number = String.valueOf(count);

				map.put("url", sc_url);
				map.put("name", productname);
				map.put("num", number);
				map.put("price", price);
				// 类型、颜色
				map.put("type", str_type);
				map.put("color", str_color);
				map.put("username", Username_All.getName());

				return map;
			}
		};

		request.setTag("添加购物车");
		MyApp.getRequestQueue().add(request);
	}

	private void showProductCart() {
		// TODO Auto-generated method stub
		CartFragment.linearlayout_product_cart_data.setVisibility(View.VISIBLE);
		CartFragment.linearlayout_product_cart_nodata.setVisibility(View.GONE);
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
						JSONArray array = obj.getJSONArray("array");
						for (int i = 0; i < array.length(); i++) {
							JSONObject object = array.getJSONObject(i);
							String url = object.getString("url");
							String name = object.getString("name");
							int num = object.getInt("num");
							String color = object.getString("color");
							String type = object.getString("type");
							String price = object.getString("price");
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
	// 设置图片请求类型，颜色
	private void requestTC() {
		// TODO Auto-generated method stub
		// 设置图片、内容、价格
		new DownImage(this).getPicture(url, iv_iv);
		tv_productname.setText(productname);
		tv_storageprice.setText("￥" + price + " " + "库存200件");
		// 网上请求类型、颜色
		final ProgressDialog dialog = ProgressDialog.show(this, "连接服务器中",
				"请稍等...");
		dialog.setCanceledOnTouchOutside(true);// 设置点击进度对话框外的区域对话框消失
		String tc_url = url_host + "requestTypeColor.jsp"; // http://localhost:8080/project_myapp/requestTypeColor.jsp
		StringRequest request = new StringRequest(Method.POST, tc_url,
				new Listener<String>() {

					@Override
					public void onResponse(String response) {
						// TODO Auto-generated method stub
						Log.d("Tag", "response =" + "请求成功");
						dialog.dismiss();
						try {
							JSONArray array = new JSONArray(response.trim());
							for (int i = 0; i < array.length(); i++) {
								JSONObject object = array.getJSONObject(i);
								String color01 = object.getString("color01");
								Log.d("Tag", "color01 =" + color01);
								String color02 = object.getString("color02");
								String type01 = object.getString("type01");
								String type02 = object.getString("type02");
								String type03 = object.getString("type03");
								String type04 = object.getString("type04");
								Log.d("Tag", color01 + " " + type01);
								tv_type01.setText(type01);
								tv_type02.setText(type02);
								tv_type03.setText(type03);
								tv_type04.setText(type04);
								tv_color01.setText(color01);
								tv_color02.setText(color02);
								dialog.dismiss();
							}
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
				}, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				// TODO Auto-generated method stub

			}
		}) {
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				// TODO Auto-generated method stub
				HashMap<String, String> map = new HashMap<String, String>();
				// 截取字符串
				String aa[] = url.split("/");
				String tc_url = aa[4];
				map.put("url", tc_url);
				return map;
			}
		};
		request.setTag("请求类型与颜色");
		MyApp.getRequestQueue().add(request);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		/**带返回值跳转是为了保证用户未登录，在登录的时候他的购物车是有数据的。*/
		if (requestCode == 3 && resultCode == 2) {
			String name = data.getStringExtra("name");
			Username_All.setName(name);
			Username_All.setStauts(true);
			CartFragment.linearlayout_product_cart_unlogin
					.setVisibility(View.GONE);
			CartFragment.data.clear();

			//请求购物车数据
			StringRequest request = new StringRequest(Method.POST, url_host
					+ "productcart.jsp", new Listener<String>() {

				@Override
				public void onResponse(String response) {
					// TODO Auto-generated method stub
					Log.d("Tag", "response = " + response);
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
							JSONArray array = obj.getJSONArray("array");
							for (int i = 0; i < array.length(); i++) {
								JSONObject object = array.getJSONObject(i);
								String url = object.getString("url");
								String name = object.getString("name");
								int num = object.getInt("num");
								String color = object.getString("color");
								String type = object.getString("size");
								String price = object.getString("price");
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

			productCartAdapter = new ProductCartAdapter(CartFragment.data, this);
			CartFragment.listview_product_cart_data.setAdapter(productCartAdapter);
			CartFragment.listview_product_cart_data.setOnItemClickListener(this);

		}
	}

	/** 控制背景变暗 0变暗 1变亮 */
	public void setBackgroundBlack(View view, int what) {
		switch (what) {
			case 0:
				view.setVisibility(View.VISIBLE);
				break;
			case 1:
				view.setVisibility(View.GONE);
				break;
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
							long id) {
		// TODO Auto-generated method stub

	}

}