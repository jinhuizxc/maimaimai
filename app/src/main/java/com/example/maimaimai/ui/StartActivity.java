package com.example.maimaimai.ui;

import java.util.ArrayList;

import com.example.maimaimai.R;
import com.example.maimaimai.fragment.Fragment_bg01;
import com.example.maimaimai.fragment.Fragment_bg02;
import com.example.maimaimai.fragment.Fragment_bg03;
import com.example.maimaimai.fragment.Fragment_bg04;
import com.example.maimaimai.fragment.Fragment_bg05;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

public class StartActivity extends Activity {

	ImageView iv_point1, iv_point2, iv_point3, iv_point4, iv_point5;
	ImageView ivs[] = { iv_point1, iv_point2, iv_point3, iv_point4, iv_point5 };
	int ivIds[] = { R.id.iv_point1, R.id.iv_point2, R.id.iv_point3,
			R.id.iv_point4, R.id.iv_point5 };
	// 图片资源
	int ivSelected = R.drawable.icon_point_pre;
	int ivNotSelected = R.drawable.icon_point;
	// 建立链表管理5个本地碎片
	ArrayList<Fragment> list = new ArrayList<Fragment>();

	ViewPager viewPager;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.start);

		viewPager = (ViewPager)findViewById(R.id.vp_s);

		for (int i = 0; i < ivIds.length; i++) {
			ivs[i] = (ImageView) findViewById(ivIds[i]);
		}

		list.add(new Fragment_bg01());
		list.add(new Fragment_bg02());
		list.add(new Fragment_bg03());
		list.add(new Fragment_bg04());
		list.add(new Fragment_bg05());


		//引导界面以后再加！


	}



}
