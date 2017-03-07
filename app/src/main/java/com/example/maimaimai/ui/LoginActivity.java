package com.example.maimaimai.ui;

import java.util.HashMap;
import java.util.Map;

import com.android.volley.Request.Method;
import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import com.example.maimaimai.R;
import com.example.maimaimai.cls.Username_All;
import com.example.maimaimai.fragment.PersonalFragment;
import com.example.maimaimai.utils.MyApp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity implements OnClickListener {

	EditText etName;
	EditText etPass;
	String url_host = Username_All.getUrl_host();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		etName = (EditText) findViewById(R.id.etLoginName);
		etPass = (EditText) findViewById(R.id.etLoginPass);

		findViewById(R.id.register).setOnClickListener(this);
		findViewById(R.id.login).setOnClickListener(this);

	}

	// http://192.168.0.103:8080/project_myapp/login.jsp
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v.getId() == R.id.login) {
			// 登录界面，弹出toast显示
			// Volley请求
			final String name = etName.getText().toString();
			final String pass = etPass.getText().toString();
			String url = url_host + "login.jsp";
			StringRequest request = new StringRequest(Method.POST, url,
					new Listener<String>() {

						@Override
						public void onResponse(String response) {
							// TODO Auto-generated method stub
							Log.d("Tag", "请求成功 =" + response);
//							Toast.makeText(LoginActivity.this, "请求成功",
//									Toast.LENGTH_SHORT).show();
							/**
							 * 1.在登录的时候，服务器端那边需要删除<html>，<head>，<body>等相应标签里的内容只留下登录的代码。
							 * 2.注意：服务器那边输出为out.print,不要加system。不然请求失败。
							 * 3.并且这边response的响应这里，trim（）去除前后空格
							 * */
							if (response.trim().equals("success")) {
								Toast.makeText(LoginActivity.this, "登录成功",
										Toast.LENGTH_SHORT).show();
								//界面跳转到PersonalFragment
								Intent intent = new Intent();
								intent.putExtra("name", name);
								Username_All.setName(name);   //设置全局变量。
								setResult(2, intent);
								LoginActivity.this.finish();
							} else {
								Toast.makeText(LoginActivity.this, "登录失败",
										Toast.LENGTH_SHORT).show();
							}

						}
					}, new Response.ErrorListener() {

				@Override
				public void onErrorResponse(VolleyError error) {
					// TODO Auto-generated method stub
					Log.d("Tag", "请求失败");
					Toast.makeText(LoginActivity.this, "请求失败",
							Toast.LENGTH_SHORT).show();
				}
			}) {
				// 重写方法
				@Override
				protected Map<String, String> getParams()
						throws AuthFailureError {
					// TODO Auto-generated method stub
					HashMap<String, String> map = new HashMap<String, String>();

					map.put("name", name);
					map.put("pass", pass);
					return map;
				}
			};

			request.setTag("login");
			MyApp.getRequestQueue().add(request);

		} else if (v.getId() == R.id.register) {
			// 跳转到免费注册界面
			Intent intent = new Intent();
			intent.setClass(this, RegisterActivity.class);
			startActivity(intent);
			LoginActivity.this.finish();
		}
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		// 取消请求
		MyApp.getRequestQueue().cancelAll("login");
	}
}