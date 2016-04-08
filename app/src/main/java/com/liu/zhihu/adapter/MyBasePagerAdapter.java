package com.liu.zhihu.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;

import java.util.List;

/**
 * Created by Ming on 2016/3/30.
 */
public class MyBasePagerAdapter<T> extends PagerAdapter{
    protected Context mContext;
    protected List<T> mList;

    public MyBasePagerAdapter(Context context,List<T> list){
        this.mContext = context;
        this.mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
