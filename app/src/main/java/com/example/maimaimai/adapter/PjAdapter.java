package com.example.maimaimai.adapter;



import java.util.ArrayList;

import com.example.maimaimai.R;
import com.example.maimaimai.cls.Pjlist;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class PjAdapter extends BaseAdapter {

	ArrayList<Pjlist> data;
	LayoutInflater inflater;

	public PjAdapter(ArrayList<Pjlist> data, Context context) {
		super();
		this.data = data;
		this.inflater = LayoutInflater.from(context);
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
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder = new ViewHolder();
		if(convertView == null){

			convertView = inflater.inflate(R.layout.item_pj, null);
			holder.iv_pj = (ImageView) convertView.findViewById(R.id.iv_pj);
			holder.tv_pj1 = (TextView) convertView.findViewById(R.id.tv_pj1);
			holder.tv_pj2 = (TextView) convertView.findViewById(R.id.tv_pj2);
			holder.tv_pj3 = (TextView) convertView.findViewById(R.id.tv_pj3);
			holder.tv_pj4 = (TextView) convertView.findViewById(R.id.tv_pj4);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		//设置数据
		Pjlist pjlist = data.get(position);
		holder.iv_pj.setImageResource(pjlist.getImage());
		holder.tv_pj1.setText(pjlist.getTv01());
		holder.tv_pj2.setText(pjlist.getTv02());
		holder.tv_pj3.setText(pjlist.getTv03());
		holder.tv_pj4.setText(pjlist.getTv04());

		return convertView;
	}

	class ViewHolder{
		ImageView iv_pj;
		TextView tv_pj1;
		TextView tv_pj2;
		TextView tv_pj3;
		TextView tv_pj4;

	}
}
