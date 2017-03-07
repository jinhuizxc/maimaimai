package com.example.maimaimai.utils;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import android.app.Application;
import android.view.Menu;

public class MyApp extends Application {


	private static RequestQueue queue;
	@Override
	public void onCreate() {
		super.onCreate();
		//得到Volley的请求队列
		queue = Volley.newRequestQueue(getApplicationContext());
	}
	/**
	 * 得到Volley请求队列
	 * @return
	 */
	public static RequestQueue getRequestQueue() {
		return queue;
	}



}
