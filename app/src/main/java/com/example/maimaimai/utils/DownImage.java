package com.example.maimaimai.utils;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.example.maimaimai.R;
import com.example.maimaimai.cls.Username_All;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

public class DownImage {

	ImageLoader loader;

	public DownImage(Context context) {
		super();
		MyImageCache cache = new MyImageCache(context);
		loader = new ImageLoader(MyApp.getRequestQueue(), cache);
	}

	// 网络获取图片
	public void getPicture(String url, ImageView iv) {

		ImageListener listener = ImageLoader.getImageListener(iv,
				R.drawable.ic_launcher, R.drawable.icon_shop_search_empty);
		Log.d("Tag", "DownImage url" + url);
		loader.get(url, listener);
	}

}
