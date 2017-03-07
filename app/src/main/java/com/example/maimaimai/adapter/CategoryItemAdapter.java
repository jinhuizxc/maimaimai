package com.example.maimaimai.adapter;

import java.util.ArrayList;


import com.example.maimaimai.R;
import com.example.maimaimai.cls.IconShopProductItem;
import com.example.maimaimai.cls.ProductInfo;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CategoryItemAdapter extends BaseAdapter {

	ArrayList<ProductInfo> data;
	LayoutInflater inflater;
	IconShopProductItem item;

	public CategoryItemAdapter(ArrayList<ProductInfo> data, Context context) {
		// TODO Auto-generated constructor stub
		this.data = data;
		this.inflater = LayoutInflater.from(context);
		this.item = new IconShopProductItem(context);
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
		Log.d("Tag", "getView");
		ViewHolder holder;
		ProductInfo info = data.get(position);
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.item, null);
			holder.iv = (ImageView) convertView
					.findViewById(R.id.imageView_categoryinfo);
			holder.tv_name = (TextView) convertView
					.findViewById(R.id.textView01_categoryinfo);
			holder.tv_price = (TextView) convertView
					.findViewById(R.id.textView02_categoryinfo);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		item.getBitmap(info.getUrl(), holder.iv);
		holder.tv_name.setText(info.getProductname());
		holder.tv_price.setText(info.getPrice() + " Ԫ");
		return convertView;
	}

	class ViewHolder {
		ImageView iv;
		TextView tv_name;
		TextView tv_price;
	}

}
