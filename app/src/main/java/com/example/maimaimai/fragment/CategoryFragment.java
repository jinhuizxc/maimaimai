package com.example.maimaimai.fragment;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.maimaimai.R;
import com.example.maimaimai.adapter.CategoryAdapter;
import com.example.maimaimai.cls.Category;
import com.example.maimaimai.ui.HouseActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class CategoryFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater,
							 ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v = inflater.inflate(R.layout.category, null);

		// 适配显示的图片数组
		int Image[] = { R.drawable.catergory_appliance,
				R.drawable.catergory_book, R.drawable.catergory_cloth,
				R.drawable.catergory_deskbook, R.drawable.catergory_digtcamer,
				R.drawable.catergory_furnitrue, R.drawable.catergory_mobile,
				R.drawable.catergory_skincare };
		// 给照片添加文字显示(Title)
		final String titles[] = { "家电", "图书", "衣服", "笔记本", "数码", "家具", "手机",
				"护肤" };
		String contents[] = { "家电/生活电器/厨房电器", "电子书/图书/小说", "男装/女装/童装",
				"笔记本/笔记本配件/产品外设", "摄影摄像/数码配件", "家具/灯具/生活用品", "手机通讯/运营商/手机配件",
				"面部护理/口腔护理/..." };

		// listview + 适配器 + 数据源

		ArrayList<Category> data = new ArrayList<Category>();

		for (int i = 0; i < Image.length; i++) {

			Category category = new Category(Image[i], titles[i], contents[i]);
			data.add(category);
		}
		CategoryAdapter adapter = new CategoryAdapter(getActivity(), data);
		ListView listView = (ListView) v.findViewById(R.id.catergory_listview);
		// 解析出footer
		View view = inflater.inflate(R.layout.footer, null);
		listView.addFooterView(view);
		listView.setAdapter(adapter);

		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
									int position, long id) {
				// TODO Auto-generated method stub
				/**
				 * 点击每一个item项跳转到activity中,问题是点击每一个item项想要进入对应的activity就必须要区分
				 * 创建8个activity太多，考虑用服务器将图片加载到服务器。
				 * 1个activity,1个布局将所有内容加载，最简单！
				 */
				String title = titles[position];
				Log.d("Tag", "title = " + title);

				Intent intent = new Intent(getActivity(),
						HouseActivity.class);

				intent.putExtra("title", title);
				startActivity(intent);

			}
		});
		return v;
	}

}

