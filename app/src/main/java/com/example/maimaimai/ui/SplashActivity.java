package com.example.maimaimai.ui;

import com.example.maimaimai.R;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

/**
 * 开机动画的设置：
 * 1.创建AnimationAnimation对象
 * AnimationAnimation anim = AnimationUtils.loadAnimation(this, R.anim.splash_loading);
 * 对ImageView设置动画,可以将动画放在线程中，参考(复习篇-开机动画)开启动画imageView.startAnimation(an);
 *
 * 2.给anim-动画设置监听。,将
 * anim.setAnimationListener(this);
 * iv.setAnimation(anim);
 * 3.(测试：动画放在handle里面也可以运行，只是要设置anim.xml文件)
 */
public class SplashActivity extends Activity implements AnimationListener{

	//	Handler handler = new Handler();
	ImageView iv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		iv = (ImageView) findViewById(R.id.splash_loading_item);

		//动画
		final Animation anim = AnimationUtils.loadAnimation(this, R.anim.splash_loading);
		anim.setAnimationListener(this);
		iv.setAnimation(anim);

//		handler.postDelayed(new Runnable() {
//
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//				//进度条加载动画，并设置跳转
//				Intent intent = new Intent();
//				intent.setClass(SplashActivity.this, MainActivity.class);
//				//先动画
//				iv.startAnimation(anim);
//				startActivity(intent);
//
//			}
//		}, 4000);

	}

	@Override
	public void onAnimationStart(Animation animation) {
		// TODO Auto-generated method stub

	}
	@Override
	public void onAnimationEnd(Animation animation) {
		// TODO Auto-generated method stub
		Intent intent = new Intent();
		intent.setClass(SplashActivity.this, MainActivity.class);
		startActivity(intent);
		finish();
	}
	@Override
	public void onAnimationRepeat(Animation animation) {
		// TODO Auto-generated method stub
		//先动画
		overridePendingTransition(R.anim.push_left_in,
				R.anim.push_left_out);
	}


}
