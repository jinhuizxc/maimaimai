package com.example.maimaimai.adapter;

import java.util.ArrayList;

import com.example.maimaimai.R;
import com.example.maimaimai.cls.IconShopProductItem;
import com.example.maimaimai.cls.ProductCartInfo;
import com.example.maimaimai.cls.Username_All;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ProductCartAdapter extends BaseAdapter {

	ArrayList<ProductCartInfo> data;
	LayoutInflater inflater;
	Context context;
	//加载图片的类
	IconShopProductItem item;
	String url_host = Username_All.getUrl_host();
	public ProductCartAdapter(ArrayList<ProductCartInfo> productData,
							  Context context) {
		// TODO Auto-generated constructor stub
		this.data = productData;
		this.context = context;
		this.item = new IconShopProductItem(context);
		this.inflater = LayoutInflater.from(context);

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		Log.d("Tag", "productDatasize = " + data.size());
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
		Log.d("Tag", "product_cart_getView");

		ProductCartInfo info = data.get(position);
		Log.d("Tag", "info = " + info);
		ViewHolder holder = new ViewHolder();
		if (convertView == null) {

			convertView = inflater.inflate(R.layout.productcartinfo, null);
			holder.iv = (ImageView) convertView
					.findViewById(R.id.imageView_product_bitmap);
			holder.tv_name = (TextView) convertView
					.findViewById(R.id.textView_product_title);
			holder.tv_price = (TextView) convertView
					.findViewById(R.id.textView_product_price);
			holder.tv_color = (TextView) convertView.findViewById(R.id.textView_product_color);
			holder.tv_num = (TextView) convertView.findViewById(R.id.textView_product_number);
			holder.cb_isbuy = (CheckBox) convertView.findViewById(R.id.checkBox_product_isbuy);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		item.getBitmap(url_host + info.getUrl(), holder.iv);
		holder.tv_name.setText(info.getName());
		holder.tv_price.setText(info.getprice() + "");
		holder.tv_color.setText(info.getColor());
		holder.tv_num.setText(info.getNum() + "");
		holder.cb_isbuy.setChecked(false);
		//如果cb被点击表示商品被选中可以进入订单界面,or不能弹出温馨提示
//		if(holder.cb_isbuy.isChecked()){
//
//		}else{
//			Toast.makeText(context, "没有选中商品哦", Toast.LENGTH_SHORT).show();
//		}
		return convertView;
	}

	class ViewHolder {
		CheckBox cb_isbuy;
		ImageView iv;
		TextView tv_name;
		TextView tv_price;
		TextView tv_color;
		TextView tv_num;
	}

}


