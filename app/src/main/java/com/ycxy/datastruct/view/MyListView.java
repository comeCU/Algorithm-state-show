package com.ycxy.datastruct.view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.ycxy.datastruct.R;

/**
 * 此代码不需要看！！！
 * 看ShowActivity.java中的第78行怎么调用就行了。
 */

public class MyListView {
    Context mContext;
    ListView mListView;
    ArrayAdapter<String> mAdapter;
    String[] mData;

    public MyListView(Context context,ListView listView,String[] data){
        mContext=context;
        mListView=listView;
        mData=data;
        mAdapter = new ArrayAdapter<String>(context,
                R.layout.list_item,R.id.tv_code, data);
        mListView.setAdapter(mAdapter);
        setListViewHeight();
    }

    public void setListViewHeight() {
        int totalHeight = 0;
        for (int i = 0, len = mAdapter.getCount(); i < len; i++) {
            // listAdapter.getCount()返回数据项的数目
            View listItem = mAdapter.getView(i, null, mListView);
            // 计算子项View 的宽高
            listItem.measure(0, 0);
            // 统计所有子项的总高度
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = mListView.getLayoutParams();
        params.height = 20 + totalHeight+ (mListView.getDividerHeight() * (mListView.getCount() - 1));
        // listView.getDividerHeight()获取子项间分隔符占用的高度
        // params.height最后得到整个ListView完整显示需要的高度
        mListView.setLayoutParams(params);
    }

    public void highlight(int line){
        //mListView.setSelection(line);
        mListView.setItemChecked(line,true);
    }
}
