package com.liu.zhihu.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.liu.zhihu.R;
import com.liu.zhihu.activity.BaseActivity;
import com.liu.zhihu.adapter.IndexAdapter;
import com.liu.zhihu.entity.BaseEntity;
import com.liu.zhihu.entity.NewsEntity;
import com.liu.zhihu.net.NetRequest;
import com.liu.zhihu.net.RequestService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ming on 2016/3/11.
 */
public class ContentFragment extends Fragment {


    private ListView lv_index;
    private List<NewsEntity> dataList = new ArrayList<>();
    private BaseActivity mActivity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = (BaseActivity) getActivity();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       View  view = View.inflate(getActivity(), R.layout.fragment_index,null);
        lv_index = (ListView) view.findViewById(R.id.lv_index);
        queryData();
        return view;
    }


    private void queryData() {
        RequestService.getInstance().getNews(getActivity(), NewsEntity.class, new NetRequest.RequestListener() {
            @Override
            public void onSuccess(BaseEntity result) {
                if(result.isOk()){
                    NewsEntity entity = (NewsEntity) result;
                    dataList.add(entity);
                    setData();
                }
            }

            @Override
            public void onFailed(Exception exception, String msg) {
                exception.printStackTrace();
                Log.e("TAG",msg);
            }
        });
    }

    private void setData() {
        lv_index.setAdapter(new IndexAdapter(getActivity(),dataList));
        lv_index.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mActivity.showToast("点击了第" + position + "个条目");
            }
        });
    }
}
