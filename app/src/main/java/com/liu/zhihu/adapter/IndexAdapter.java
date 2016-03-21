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

import java.util.List;

/**
 * Created by Ming on 2016/3/21.
 */
public class IndexAdapter extends MyBaseAdapter<NewsEntity> {
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
        int i = 0;
        while (position > 0) {
            position = position - mList.get(i).getStories().size() - 1;
            i++;
        }
        if (position == 0) {
            return mList.get(i).getDate();
        } else {
            return mList.get(i).getStories().get(position + mList.get(i).getStories().size());
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(mContext, R.layout.item_index, null);
            holder.textView = (TextView) convertView.findViewById(R.id.tv_des);
            holder.imageView = (ImageView) convertView.findViewById(R.id.iv_item_icon);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Log.e("size", mList.get(0).getStories().size() + "");

        int i = 0;

        if(position == 0){
            holder.imageView.setVisibility(View.GONE);
            holder.textView.setText(mList.get(i).getDate());
        } else {
            while (position > 0){

            }
        }

        while (position > 0) {
            position = position - mList.get(i).getStories().size() - 1;
            i++;
        }
        if (position + mList.get(i).getStories().size() + i + 1 == 0) {
            holder.imageView.setVisibility(View.GONE);
            holder.textView.setText(mList.get(i).getDate());
        } else {
            holder.textView.setText(mList.get(i).getStories().get().getTitle());
        }


        holder.textView.setText(mList.get(0).getStories().get(position).getTitle());
        Picasso.with(mContext).load(mList.get(0).getStories().get(position).getImages().get(0)).into(holder.imageView);
        return convertView;
    }

    public static class ViewHolder {
        public static TextView textView;
        public static ImageView imageView;
    }
}
