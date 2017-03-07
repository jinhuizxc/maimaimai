package com.example.maimaimai.utils;

import com.example.maimaimai.R;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.PopupWindow;

/**
 * ExitView这个类主要是解析布局
 * @author
 *
 */
public class ExitView extends PopupWindow {

	private Button bt_exit,bt_cancel;
	private View mMenuView;

	public ExitView(Context context,OnClickListener listener){
		/**
		 * Use with getSystemService to retrieve a android.view.LayoutInflater for inflating layout resources in this context.
		 * See Also:getSystemService android.view.LayoutInflater
		 */
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mMenuView = inflater.inflate(R.layout.exit_dialog_from_settings, null);
		bt_exit = (Button) mMenuView.findViewById(R.id.bt_exit);
		bt_cancel = (Button) mMenuView.findViewById(R.id.bt_cancel);

		bt_cancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				/**
				 * Dispose of the popup window.
				 * This method can be invoked only after showAsDropDown(android.view.View) has been executed.
				 * Failing that, calling this method will have no effect.
				 */
				dismiss();
			}
		});

		bt_exit.setOnClickListener(listener);
		//this指PopupWindow
		this.setContentView(mMenuView);
		this.setWidth(LayoutParams.FILL_PARENT);
		this.setHeight(LayoutParams.WRAP_CONTENT);
		this.setFocusable(true);
		this.setBackgroundDrawable(new ColorDrawable(0xb0000000));
		mMenuView.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				int height = mMenuView.findViewById(R.id.pop_layout).getTop();
				int y=(int) event.getY();
				if(event.getAction()==MotionEvent.ACTION_UP){
					if(y<height){
						dismiss();
					}
				}
				return true;
			}
		});



	}
}
