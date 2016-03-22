package com.liu.zhihu.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.liu.zhihu.R;
import com.liu.zhihu.entity.NewsEntity;
import com.squareup.picasso.Picasso;

import org.xutils.x;

import java.util.List;

/**
 * Created by Ming on 2016/3/21.
 */
public class IndexAdapter extends MyBaseAdapter<NewsEntity> {

    private int i;

    public IndexAdapter(Context context, List list) {
        super(context, list);
    }

    @Override
    public int getCount() {
        int count = 0;
        for (NewsEntity entity : mList) {
            count = count + entity.getStories().size();
        }
        return count + mList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
//        i = 0;
//        while (position > 0) {
//            position = position - mList.get(i).getStories().size() - 1;
//            i++;
//        }
//        if (position == 0) {
//            return mList.get(i).getDate();
//        } else {
//            return mList.get(i).getStories().get(position + mList.get(i).getStories().size());
//        }

        return null;
    }

    @Override
    public int getItemViewType(int position) {
        i = 0;
        int mPosition = position;
        while (mPosition > 0) {
            mPosition = mPosition - mList.get(i).getStories().size() - 1;
            i++;
        }
        if (mPosition == 0) {
            return 0;
        }
        return 1;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        int type = getItemViewType(position);
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(mContext, R.layout.item_index, null);
            holder.textView = (TextView) convertView.findViewById(R.id.tv_des);
            holder.imageView = (ImageView) convertView.findViewById(R.id.iv_item_icon);
            if(type == 0){
                holder.imageView.setVisibility(View.GONE);
            } else {
                holder.imageView.setVisibility(View.VISIBLE);
            }
            convertView.setTag(holder);
        } else {
            if(type == 0){

            } else {

            }
            holder = (ViewHolder) convertView.getTag();
        }
        System.out.println(position);

        if(position == 0){
            holder.textView.setText(mList.get(0).getDate());
        } else {
            System.out.println(mList.get(0).getStories().get(position - 1).getTitle());
            holder.textView.setText("");
            holder.textView.setText(mList.get(0).getStories().get(position - 1).getTitle());
            Picasso.with(mContext).load(mList.get(0).getStories().get(position-1).getImages().get(0)).into(holder.imageView);
        }



//        Log.e("size", mList.get(0).getStories().size() + "");
//
//        int frontCount = 0;
//        if (i - 1 > 0) {
//            for (int j = 0 ;j < i; j++){
//                frontCount = frontCount + mList.get(j).getStories().size();
//            }
//        }
//        frontCount += i;
//
//        System.out.println("position    =====  " + position);
//
//        if(type == 1){
//            holder.textView.setText(mList.get(i - 1).getStories().get(position - frontCount).getTitle());
//        }
//
//        if (type == 0) {
//
//            holder.textView.setText(mList.get(i).getDate());
//        } else {
//
//            System.out.println("i                   ==============    " + i);
//
//
//            holder.textView.setText(mList.get(i - 1).getStories().get(position - frontCount).getTitle());
//            x.image().bind(holder.imageView,mList.get(i - 1).getStories().get(position - frontCount).getImages().get(0));
//        }
        return convertView;
    }

    public static class ViewHolder {
        public static TextView textView;
        public static ImageView imageView;
    }
}
