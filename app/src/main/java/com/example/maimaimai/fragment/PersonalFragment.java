package com.example.maimaimai.fragment;

import com.example.maimaimai.R;
import com.example.maimaimai.cls.Username_All;
import com.example.maimaimai.ui.LoginActivity;
import com.example.maimaimai.ui.MoreActivity;
import com.example.maimaimai.utils.ExitView;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

public class PersonalFragment extends Fragment implements OnClickListener  {

	ExitView exitView;
	Button bt_username, bt_more, bt_exit;
	ImageView iv_logo;
	View v;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		v = inflater.inflate(R.layout.personal, null);
		initView();



		//服务器请求名字
		bt_username.setOnClickListener(this);
		bt_more.setOnClickListener(this);
		bt_exit.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//弹出是否退出对话框！
				//实例化SelectPicPopupWindow
				//实例化SelectPicPopupWindow
				exitView = new ExitView(PersonalFragment.this.getActivity(), listener);
				//显示窗口
				exitView.showAtLocation(PersonalFragment.this
								.getActivity().findViewById(R.id.layout_personal),
						Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0); //设置layout在PopupWindow中显示的位置

			}

			//为弹出窗口实现监听类
			OnClickListener listener = new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					if(v.getId() == R.id.bt_exit){
						//退出所有的Activity,代码待写--------------------------------------
						Username_All.setName("null");
						Username_All.setStauts(false);
						iv_logo.setImageResource(R.drawable.logo);
						bt_username.setText("登录");
						exitView.dismiss();
						//将购物车布局置为未登录状态
						CartFragment.linearlayout_product_cart_unlogin.setVisibility(View.VISIBLE);
						CartFragment.linearlayout_product_cart_nodata.setVisibility(View.GONE);
						//跳转登录界面
						Intent intent = new Intent();
						intent.setClass(PersonalFragment.this.getActivity(), LoginActivity.class);
						startActivity(intent);
					}else if(v.getId()== R.id.bt_cancel){
						exitView.dismiss();
					}
				}
			};
		});

		return v;
	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();

		if (!Username_All.getName().equals("null")) {
			Log.d("Tag", "PersonalFragment" + Username_All.getName());
			iv_logo.setImageResource(R.drawable.logo1);
			bt_username.setText(Username_All.getName());
		}
	}

	//初始化控件
	private void initView() {
		// TODO Auto-generated method stub
		bt_username = (Button) v.findViewById(R.id.bt_username);
		bt_more = (Button) v.findViewById(R.id.bt_more);
		bt_exit = (Button) v.findViewById(R.id.personal_exit);
		iv_logo = (ImageView) v.findViewById(R.id.logo);


	}

	//接收数据
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(data != null && requestCode == 0 && resultCode == 2){

			String name = data.getStringExtra("name");
			bt_username.setText(name);
			bt_username.setClickable(false);
			iv_logo.setImageResource(R.drawable.logo1);
			Username_All.setName(name);
			Username_All.setStauts(true);
			Log.d("Tag",
					Username_All.getName() + ":" + Username_All.getStauts());
			iv_logo.setImageResource(R.drawable.logo1);
			CartFragment.linearlayout_product_cart_unlogin.setVisibility(View.GONE);

		}

	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId() == R.id.bt_username){
			//跳转到登录界面
//			startActivity(new Intent(PersonalFragment.this.getActivity(), LoginActivity.class));
			Intent intent = new Intent();
			intent.setClass(PersonalFragment.this.getActivity(),LoginActivity.class);
			startActivityForResult(intent, 0);
		}else if(v.getId() == R.id.bt_more){
			//跳转到更多界面
			startActivity(new Intent(PersonalFragment.this.getActivity(), MoreActivity.class));
		}
	}

}
