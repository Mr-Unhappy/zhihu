package com.liu.zhihu.activity;

import android.app.FragmentManager;
import android.graphics.Color;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
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
    private Toolbar toolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        toolBar = (Toolbar) findViewById(R.id.toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        content = (FrameLayout) findViewById(R.id.fl_content);
        menu = (ListView) findViewById(R.id.gv_menu);

        final List<String> list = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            list.add("菜单" + i);
        }

        toolBar.setTitleTextColor(Color.parseColor("#00ff00"));
        setSupportActionBar(toolBar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolBar,R.string.open,R.string.close){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };

        drawerToggle.syncState();
        drawerLayout.setDrawerListener(drawerToggle);
        ArrayAdapter menuAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        menu.setAdapter(menuAdapter);

        menu.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Fragment contentFragment = new ContentFragment();
                Bundle args = new Bundle();
                args.putString("text", list.get(position));
                toolBar.setTitle(list.get(position));
                contentFragment.setArguments(args);

                FragmentManager fragmentManager = getFragmentManager();

                fragmentManager.beginTransaction().replace(R.id.fl_content, contentFragment).commit();

                drawerLayout.closeDrawer(menu);
            }
        });
        drawerListener = new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(View drawerView) {

            }

            @Override
            public void onDrawerClosed(View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        };

        menu.setSelection(0);
    }

}
