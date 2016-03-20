package com.liu.zhihu.activity;

import android.app.FragmentManager;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ListView;

import com.liu.zhihu.R;
import com.liu.zhihu.entity.BaseEntity;
import com.liu.zhihu.fragment.ContentFragment;
import com.liu.zhihu.net.NetRequest;
import com.liu.zhihu.net.RequestService;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private ListView menu;
    private FrameLayout content;
    private DrawerLayout drawerLayout;
    private DrawerLayout.DrawerListener drawerListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

        queryData();


        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        content = (FrameLayout) findViewById(R.id.fl_content);
        menu = (ListView) findViewById(R.id.gv_menu);

        final List<String> list = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            list.add("菜单" + i);
        }

        menu.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list));

        menu.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Fragment contentFragment = new ContentFragment();
                Bundle args = new Bundle();
                args.putString("text",list.get(position));
                contentFragment.setArguments(args);

                FragmentManager fragmentManager = getFragmentManager();

                fragmentManager.beginTransaction().replace(R.id.fl_content,contentFragment).commit();

                drawerLayout.closeDrawer(menu);
            }
        });

        drawerListener = new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                System.out.println("滑动   +++++++  ");
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                showToast("打开");
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                showToast("关闭");
            }

            @Override
            public void onDrawerStateChanged(int newState) {
                System.out.println("状态变化");
            }
        };

        drawerLayout.setDrawerListener(drawerListener);

    }

    private void queryData() {
        RequestService.getInstance().getNews(this, BaseEntity.class, new NetRequest.RequestListener() {
            @Override
            public void onSuccess(BaseEntity result) {

            }

            @Override
            public void onFailed(Exception exception, String msg) {

            }
        });
    }
}
