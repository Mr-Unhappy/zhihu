package com.liu.zhihu.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.liu.zhihu.R;

/**
 * Created by Ming on 2016/3/11.
 */
public class ContentFragment extends Fragment {

    private String text;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       View  view = View.inflate(getActivity(), R.layout.fragment_content,null);
        text = getArguments().getString("text");
        TextView tv_title = (TextView) view.findViewById(R.id.tv_title);
        tv_title.setText(text);
        return view;
    }
}
