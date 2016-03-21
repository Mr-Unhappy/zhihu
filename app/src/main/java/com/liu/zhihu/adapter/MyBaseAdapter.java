package com.liu.zhihu.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by Ming on 2016/3/21.
 */
public class MyBaseAdapter <T> extends BaseAdapter{

    protected List<T> mList;
    protected Context mContext;

    public MyBaseAdapter(Context context,List<T> list){
        this.mContext = context;
        this.mList = list;
    }
    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return convertView;
    }
}
