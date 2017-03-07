package com.example.maimaimai.fragment;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import com.example.maimaimai.R;
import com.example.maimaimai.ui.MainActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;

import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import android.widget.LinearLayout;

public class MainFragment extends Fragment implements OnClickListener {

	ImageButton searchButton;
	LinearLayout mTopLayout;
	EditText etSearch;
	// 跳转碎片对象
	SearchFragment searchFragment = new SearchFragment();

	ViewPager viewPager;
	//5个圆点
	ImageView iv_point1, iv_point2,iv_point3, iv_point4, iv_point5;
	ImageView ivs[] = {iv_point1,iv_point2,iv_point3,iv_point4,iv_point5};
	int ivIds[] = {R.id.iv_point1, R.id.iv_point2, R.id.iv_point3, R.id.iv_point4, R.id.iv_point5};
	//图片资源
	int ivSelected = R.drawable.icon_point_pre;
	int ivNotSelected = R.drawable.icon_point;
	//建立链表管理5个本地碎片
	ArrayList<Fragment> list = new ArrayList<Fragment>();

	boolean isRun = true;
	int current = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		list.add(new Fragment01());
		list.add(new Fragment02());
		list.add(new Fragment03());
		list.add(new Fragment04());
		list.add(new Fragment05());
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v = inflater.inflate(R.layout.main, null);
		searchButton = (ImageButton) v.findViewById(R.id.index_search_button);
		mTopLayout = (LinearLayout) v.findViewById(R.id.index_top_layout);
		etSearch = (EditText) v.findViewById(R.id.index_search_edit);
		viewPager = (ViewPager) v.findViewById(R.id.viewpager);

		etSearch.setOnClickListener(this);
		for (int i = 0; i < ivIds.length; i++) {
			ivs[i] = (ImageView) v.findViewById(ivIds[i]);
		}


		FragmentPagerAdapter adapter = new FragmentPagerAdapter(
				MainFragment.this.getActivity().getSupportFragmentManager()) {

			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return list.size();
			}

			@Override
			public Fragment getItem(int arg0) {
				// TODO Auto-generated method stub
				return list.get(arg0);
			}
		};

		viewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
				isRun = true;
				for (int i = 0; i < list.size() ; i++) {
					if (arg0 == i) {
						viewPager.setCurrentItem(i);
						ivs[i].setImageResource(R.drawable.icon_point_pre);
					}else{
						ivs[i].setImageResource(R.drawable.icon_point);
					}
				}
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}
		});

		final Handler handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				viewPager.setCurrentItem(msg.arg1);
			}
		};

		Timer timer = new Timer();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				if (isRun) {
					Message msg = handler.obtainMessage();
					msg.arg1 = current;
					handler.sendMessage(msg);
					current = (current + 1) % list.size();
				}
			}
		}, 0, 3000);   //0表示无延迟，3000为3s

		viewPager.setAdapter(adapter);
		viewPager.setCurrentItem(0);

		return v;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		MainActivity.vp.setCurrentItem(1);
	}

}

