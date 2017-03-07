package com.example.maimaimai.adapter;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.maimaimai.R;
import com.example.maimaimai.cls.Category;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CategoryAdapter extends BaseAdapter {

	/**
	 * LayoutInflater类是代码实现中获取布局文件的主要形式： LayoutInflater layoutInflater =
	 * LayoutInflater.from(context); View convertView =
	 * layoutInflater.inflate();
	 * LayoutInflater的使用,在实际开发中LayoutInflater这个类还是非常有用的,它的作用类似于 findViewById(),
	 * 不同点是LayoutInflater是用来找layout下xml布局文件，并且实例化！ 而findViewById()是找具体xml下的具体
	 * widget控件(如:Button,TextView等)。
	 */
	LayoutInflater inflater;
	ArrayList<Category> data;

	// 构造方法参数有：1.Context,2.链表数据
	public CategoryAdapter(Context context, ArrayList<Category> data) {
		super();
		this.data = data;
		this.inflater = LayoutInflater.from(context); //可以在构造方法里面附加初始化语句
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
		/**以后记得不要漏写：holder + 类对象引用！*/
		ViewHolder holder = new ViewHolder();
		//类对象
		Category category = data.get(position);
		//View的加载
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.category_item,
					null);
			holder.image = (ImageView) convertView
					.findViewById(R.id.catergory_image);
			holder.title = (TextView) convertView
					.findViewById(R.id.catergoryitem_title);
			holder.content = (TextView) convertView
					.findViewById(R.id.catergoryitem_content);
			// 使用tag存储数据
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		//设置数据
		holder.image.setImageResource(category.getImageSource());
		holder.title.setText(category.getTitle());
		holder.content.setText(category.getContent());


		return convertView;
	}


	public class ViewHolder {

		ImageView image;
		TextView title;
		TextView content;

	}

}
