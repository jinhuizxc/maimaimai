package com.example.maimaimai.ui;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

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
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends Activity implements OnClickListener {

	EditText etName;
	EditText etEmail;
	EditText etPass;
	EditText etPassOk;

	String url_host = Username_All.getUrl_host();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);

		etName = (EditText) findViewById(R.id.etName);
		etEmail = (EditText) findViewById(R.id.etEmail);
		etPass = (EditText) findViewById(R.id.etPass);
		etPassOk = (EditText) findViewById(R.id.etPassOk);
		// 注册
		findViewById(R.id.zhuce).setOnClickListener(this);

		// http://localhost:8080/project_myapp/register.jsp
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		final String name = etName.getText().toString();
		final String pass = etPass.getText().toString();
		final String email = etEmail.getText().toString();
		String PassOk = etPassOk.getText().toString();

		/** 点击注册按钮进行Volley请求 */
		String url = url_host + "register.jsp";
		StringRequest request = new StringRequest(Method.POST, url,
				new Listener<String>() {
					// 请求成功
					@Override
					public void onResponse(String response) {
						// TODO Auto-generated method stub
						Log.d("Tag", "response = " + response);

						Toast.makeText(RegisterActivity.this, "注册成功",
								Toast.LENGTH_SHORT).show();
						Intent intent = new Intent(RegisterActivity.this,
								LoginActivity.class);
						// intent.putExtra("register_username", name);
						// intent.putExtra("register_password", pass);
						startActivity(intent);
						RegisterActivity.this.finish();

					}
				}, new Response.ErrorListener() {
			// 请求失败
			@Override
			public void onErrorResponse(VolleyError error) {
				// TODO Auto-generated method stub
				Log.d("Tag", "请求失败");
				Toast.makeText(RegisterActivity.this, "注册失败",
						Toast.LENGTH_SHORT).show();
			}
		}) {
			// 重写一个方法:输入getParams快速提示出来方法
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				// TODO Auto-generated method stub
				HashMap<String, String> map = new HashMap<String, String>();

				map.put("name", name);
				map.put("email", email);
				map.put("pass", pass);

				return map;
			}
		};
		request.setTag("register");
		// 将请求对象添加到队列中，发起请求
		MyApp.getRequestQueue().add(request);
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		// 取消请求
		MyApp.getRequestQueue().cancelAll("register");
	}
}
