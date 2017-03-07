package com.example.maimaimai.ui;

import java.util.HashMap;
import java.util.Map;

import javax.xml.transform.ErrorListener;

import com.android.volley.Request.Method;
import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.maimaimai.R;
import com.example.maimaimai.cls.Username_All;
import com.example.maimaimai.utils.MyApp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class addNewAddress extends Activity implements OnClickListener {

	ImageView iv_aBack;
	TextView tv_aStorage;

	EditText et_aName;
	EditText et_aPhone;
	EditText et_address;
	CheckBox checkBox1;
	String url_host = Username_All.getUrl_host();
	//标签
	String isChoice;
	SharedPreferences share;
	/**凭空创造出一个字符串, 在没有前面intent传值的情况下,通过这个字符串进行数据库的增加,在通过其他activity进行获取数据库的内容！
	 * 这个设置很关键，很重要！
	 */
	String onlyOne = "true";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_addnewaddress);

//		if(Username_All.getName().equals("null")){
//			Intent intent = new Intent(addNewAddress.this,
//					LoginActivity.class);
//			startActivityForResult(intent, 4);
//		}
		share = this.getSharedPreferences("default", Context.MODE_PRIVATE);

		iv_aBack = (ImageView) findViewById(R.id.iv_aBack);
		tv_aStorage = (TextView) findViewById(R.id.tv_aStorage);
		et_aName = (EditText) findViewById(R.id.et_aName);
		et_aPhone = (EditText) findViewById(R.id.et_aPhone);
		et_address = (EditText) findViewById(R.id.et_address);
		checkBox1 = (CheckBox) findViewById(R.id.checkBox1);

		tv_aStorage.setOnClickListener(this);
		iv_aBack.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
			case R.id.iv_aBack:
				finish();
				break;
			case R.id.tv_aStorage:
				// 将地址保存到服务器
				final String name = et_aName.getText().toString();
				final String phone = et_aPhone.getText().toString();
				final String address = et_address.getText().toString();

				String url = url_host + "addNewAddress.jsp";// http://localhost:8080/project_myapp/addNewAddress.jsp
				StringRequest request = new StringRequest(Method.POST, url,
						new Listener<String>() {

							@Override
							public void onResponse(String response) {
								// TODO Auto-generated method stub
								Log.d("Tag", "response" + "请求成功");
								Toast.makeText(addNewAddress.this, "保存成功",
										Toast.LENGTH_SHORT).show();
								Intent intent = new Intent();
								intent.setClass(addNewAddress.this, AddressActivity.class);
								startActivity(intent);
							}
						}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						// TODO Auto-generated method stub
						Log.d("Tag", "response" + "请求失败");
					}
				}) {
					@Override
					protected Map<String, String> getParams()
							throws AuthFailureError {
						// TODO Auto-generated method stub
						if (checkBox1.isChecked()) {
							isChoice = "1";
							//用SharedPreferences保存默认地址
							String name = et_aName.getText().toString();
							Editor editor = share.edit();
							editor.putString("name", name);
							editor.putString("isDefault", isChoice);
							editor.commit();
							HashMap<String, String> map = new HashMap<String, String>();
							map.put("name", name);
							map.put("phone", phone);
							map.put("address", address);
							map.put("choice", isChoice);
							map.put("onlyone", onlyOne);
							map.put("username", Username_All.getName());
							return map;
						} else {
							isChoice = "0";
							HashMap<String, String> map = new HashMap<String, String>();
							map.put("name", name);
							map.put("phone", phone);
							map.put("address", address);
							map.put("choice", isChoice);
							map.put("onlyone", onlyOne);
							map.put("username", Username_All.getName());
							return map;
						}
					}
				};

				request.setTag("添加新的地址");
				MyApp.getRequestQueue().add(request);

		}

	}


}

