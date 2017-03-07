package com.example.maimaimai.ui;

import java.util.ArrayList;

import com.example.maimaimai.R;
import com.example.maimaimai.fragment.CartFragment;
import com.example.maimaimai.fragment.CategoryFragment;
import com.example.maimaimai.fragment.MainFragment;
import com.example.maimaimai.fragment.PersonalFragment;
import com.example.maimaimai.fragment.SearchFragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends FragmentActivity implements OnClickListener {

	ImageView home_tab_main, home_tab_search, home_tab_category, home_tab_cart,
			home_tab_personal;
	ImageView ivs[] = { home_tab_main, home_tab_search, home_tab_category,
			home_tab_cart, home_tab_personal };
	TextView tv_shouye, tv_sousuo, tv_fenye, tv_gouwuche, tv_wode;
	TextView tvs[] = { tv_shouye, tv_sousuo, tv_fenye, tv_gouwuche, tv_wode };

	int ivIds[] = { R.id.home_tab_main, R.id.home_tab_search,
			R.id.home_tab_category, R.id.home_tab_cart, R.id.home_tab_personal };
	int tvIds[] = { R.id.tv_shouye, R.id.tv_sousuo, R.id.tv_fenye,
			R.id.tv_gouwuche, R.id.tv_wode };
	// 加载图片资源
	int imageSelectIds[] = { R.drawable.main_bottom_tab_home_focus,
			R.drawable.main_bottom_tab_search_focus,
			R.drawable.main_bottom_tab_category_focus,
			R.drawable.main_bottom_tab_cart_focus,
			R.drawable.main_bottom_tab_personal_focus };
	int imageNotSelectIds[] = { R.drawable.main_bottom_tab_home_normal,
			R.drawable.main_bottom_tab_search_normal,
			R.drawable.main_bottom_tab_category_normal,
			R.drawable.main_bottom_tab_cart_normal,
			R.drawable.main_bottom_tab_personal_normal };
	Fragment fragment[] = { new MainFragment(), new SearchFragment(), new CategoryFragment(),
			new CartFragment(), new PersonalFragment() };

	ArrayList<Fragment> data = new ArrayList<Fragment>();
	/**定义为public static 能被所有应用程序所有的的类所访问！*/
	public static ViewPager vp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		for (int i = 0; i < ivIds.length; i++) {
			ivs[i] = (ImageView) findViewById(ivIds[i]);
			tvs[i] = (TextView) findViewById(tvIds[i]);
			ivs[i].setOnClickListener(this);
			tvs[i].setOnClickListener(this);
			data.add(fragment[i]);
		}
		vp = (ViewPager) findViewById(R.id.vp);
		// 碎片分页适配器
		FragmentPagerAdapter adapter = new FragmentPagerAdapter(this.getSupportFragmentManager()) {

			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return data.size();
			}

			@Override
			public Fragment getItem(int index) {
				// TODO Auto-generated method stub
				return data.get(index);
			}
		};

		vp.setAdapter(adapter);
		vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			// 当用户成功切换到一个新的页面时，系统调用该方法
			@Override
			public void onPageSelected(int index) {
				// TODO Auto-generated method stub
				ivs[index].setImageResource(imageSelectIds[index]);
				tvs[index].setTextColor(Color.argb(0xff, 0x31, 0x91, 0xe8));
				// tvs[index].setTextColor(Color.BLUE);
				for (int i = 0; i < ivs.length; i++) {
					if (i != index) {
						ivs[i].setImageResource(imageNotSelectIds[i]);
						tvs[i].setTextColor(Color.BLACK);
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

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		for (int i = 0; i < ivIds.length; i++) {
			if (v.getId() == ivIds[i] || v.getId() == tvIds[i]) {
				// 当前碎片滑动
				vp.setCurrentItem(i);
				ivs[i].setImageResource(imageSelectIds[i]);
				tvs[i].setTextColor(Color.WHITE);
			} else {
				ivs[i].setImageResource(imageNotSelectIds[i]);
				tvs[i].setTextColor(Color.GRAY);
			}
		}
	}

}