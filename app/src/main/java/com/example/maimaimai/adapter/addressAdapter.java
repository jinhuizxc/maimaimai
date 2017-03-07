package com.example.maimaimai.adapter;

import java.util.ArrayList;
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
import com.example.maimaimai.cls.AddressInfo;
import com.example.maimaimai.cls.Username_All;
import com.example.maimaimai.utils.MyApp;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.EditText;

import android.widget.TextView;

public class addressAdapter extends BaseAdapter {

	ArrayList<AddressInfo> data;
	LayoutInflater inflater;
	/** 不管是Activity、fragment、类都可以用context来进行指向.的操作; 除此之外可以设置全局变量进行调用 */
	Context context;
	AddressInfo info;
	String name;
	String url_host = Username_All.getUrl_host();

	public addressAdapter(ArrayList<AddressInfo> data, Context context) {
		super();
		this.data = data;
		this.inflater = LayoutInflater.from(context);
		this.context = context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder = new ViewHolder();
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.item_address, null);
			holder.name = (TextView) convertView.findViewById(R.id.name);
			holder.phone = (TextView) convertView.findViewById(R.id.phone);
			holder.address = (TextView) convertView.findViewById(R.id.address);
			holder.checkBox1 = (CheckBox) convertView
					.findViewById(R.id.checkBox1);
			holder.bianji = (TextView) convertView.findViewById(R.id.bianji);
			holder.delete = (TextView) convertView.findViewById(R.id.delete);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		// 设置内容
		info = data.get(position);
		holder.name.setText(info.getName());
		holder.phone.setText(info.getPhone());
		holder.address.setText(info.getAddress());
		holder.checkBox1.setText("设置默认");
		holder.bianji.setText("编辑");
		holder.delete.setText("删除");
		holder.bianji.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Builder builder = new Builder(context);
				builder.setTitle("修改地址");
				View view = LayoutInflater.from(context).inflate(
						R.layout.item_modifyaddress, null);
				// EditText et_aName = (EditText)
				// view.findViewById(R.id.et_aName);
				// EditText et_mPhone = (EditText)
				// view.findViewById(R.id.et_mPhone);
				// EditText et_maddress = (EditText)
				// view.findViewById(R.id.et_maddress);
				builder.setView(view);
				builder.setPositiveButton("确定",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
												int which) {
								// TODO Auto-generated method stub
								// 发送广播
								Intent intent = new Intent("modify");
								intent.putExtra("modify", "modify");
								context.sendBroadcast(intent);
							}
						});
				builder.setNegativeButton("取消", null);
				builder.create();
				builder.show();

			}
		});
		holder.delete.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Builder builder = new Builder(context);
				builder.setTitle("是否删除？");
				builder.setPositiveButton("确定",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
												int which) {
								// TODO Auto-generated method stub
								name = data.get(position).getName();
								Log.d("Tag", "name" + name);
								// 移除当前item项
								data.remove(position);
								Intent intent = new Intent("delete");
								intent.putExtra("delete", "delete");
								context.sendBroadcast(intent);
								// 通过用户名删除数据库内容
								deleteAddress();
							}

						});
				builder.setNegativeButton("取消", null);
				builder.create();
				builder.show();
			}
		});
		return convertView;
	}

	private void deleteAddress() {
		// TODO Auto-generated method stub
		String url = url_host + "deleteAddress.jsp";
		StringRequest request = new StringRequest(Method.POST, url,
				new Listener<String>() {

					@Override
					public void onResponse(String response) {
						// TODO Auto-generated method stub
						Log.d("Tag", "response" + response);
					}
				}, new Response.ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				// TODO Auto-generated method stub
				Log.d("Tag", "response" + error);
			}
		}) {
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				// TODO Auto-generated method stub

				HashMap<String, String> map = new HashMap<String, String>();
				map.put("name", name);
				return map;
			}
		};

		request.setTag("删除地址");
		MyApp.getRequestQueue().add(request);
	}

	class ViewHolder {

		TextView name;
		TextView phone;
		TextView address;
		CheckBox checkBox1;
		TextView bianji;
		TextView delete;
	}
}