package com.example.maimaimai.fragment;

import com.example.maimaimai.R;
import com.example.maimaimai.ui.MainActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class Fragment_bg05 extends Fragment{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v = inflater.inflate(R.layout.fragment_bg05, null);
		Button button = (Button) v.findViewById(R.id.bt_in);
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//跳转
//				Intent intent = new Intent();
//				intent.setClass(Fragment_bg05.this.getActivity(), MainActivity.class);
//				startActivity(intent);
			}
		});
		return v;
	}

}
