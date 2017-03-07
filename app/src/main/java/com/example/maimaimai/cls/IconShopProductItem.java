package com.example.maimaimai.cls;

import android.content.Context;
import android.widget.ImageView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.example.maimaimai.R;
import com.example.maimaimai.utils.MyApp;
import com.example.maimaimai.utils.MyImageCache;


public class IconShopProductItem {

	ImageLoader loader;
	ImageListener listener;

	public IconShopProductItem(Context context) {
		// TODO Auto-generated constructor stub
		loader = new ImageLoader(MyApp.getRequestQueue(), new MyImageCache(
				context));
	}

	public void getBitmap(String url,ImageView iv) {
		// TODO Auto-generated method stub
		listener = ImageLoader.getImageListener(iv,
				R.drawable.icon_shop_search_empty,
				R.drawable.icon_shop_search_empty);
		loader.get(url, listener);
	}

}
