package com.example.maimaimai.cls;


/**
 * 主要是显示设置有问题。
 *
 * 可以在设置完ListView的Adapter后，根据ListView的子项目重新计算ListView的高度，
 * 然后把高度再作为LayoutParams设置给ListView，这样它的高度就正确了.
 */
import com.example.maimaimai.adapter.PjAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class Utility {

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        // 获取ListView对应的Adapter
        PjAdapter adapter = (PjAdapter) listView.getAdapter();
        if(adapter == null) {
            return;
        }
        int totalHeight = 0;
        for(int i = 0, len = adapter.getCount(); i < len; i++) { // listAdapter.getCount()返回数据项的数目
            View listItem = adapter.getView(i, null, listView);
            listItem.measure(0, 0); // 计算子项View 的宽高
            totalHeight += listItem.getMeasuredHeight(); // 统计所有子项的总高度
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight
                + (listView.getDividerHeight() * (adapter.getCount() - 1));
        // listView.getDividerHeight()获取子项间分隔符占用的高度
        // params.height最后得到整个ListView完整显示需要的高度
        listView.setLayoutParams(params);
    }
}