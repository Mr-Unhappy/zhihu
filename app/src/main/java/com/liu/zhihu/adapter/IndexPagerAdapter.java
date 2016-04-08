package com.liu.zhihu.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.liu.zhihu.R;
import com.liu.zhihu.entity.NewsEntity;

import org.xutils.x;

import java.util.List;

/**
 * Created by Ming on 2016/3/30.
 */
public class IndexPagerAdapter extends MyBasePagerAdapter<NewsEntity.TopStory> {

    public IndexPagerAdapter(Context context, List<NewsEntity.TopStory> list) {
        super(context, list);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        View item = View.inflate(mContext, R.layout.item_header, null);
        ImageView iv_header_img = (ImageView) item.findViewById(R.id.iv_header_img);
        TextView tv_header_title = (TextView) item.findViewById(R.id.tv_header_title);
        x.image().bind(iv_header_img, mList.get(position).getImage());
        tv_header_title.setText(mList.get(position).getTitle());
        container.addView(item);
        return item;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
