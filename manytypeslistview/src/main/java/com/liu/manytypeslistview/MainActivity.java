package com.liu.manytypeslistview;

import android.content.Context;
import android.content.Entity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView lv = (ListView) findViewById(R.id.lv);

        List<MyEntity> list = new ArrayList<>();

        setData(list);

        lv.setAdapter(new MyAdapter(this, list));

    }

    private void setData(List<MyEntity> list) {
        for (int count = 0; count < 100; count++) {
            if (count % 10 == 0) {
                MyEntity entity = new MyEntity("这是类型A的第" + count / 10 + "条数据", "");
                list.add(entity);
            } else {
                MyEntity entity = new MyEntity("", "这是类型B的第" + count + "条数据");
                list.add(entity);
            }
        }
    }


    public class MyAdapter extends BaseAdapter {
        private Context mContext;
        private List<MyEntity> mList;

        public MyAdapter(Context context, List<MyEntity> list) {
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
        public int getViewTypeCount() {
            return 2;
        }

        @Override
        public int getItemViewType(int position) {
            MyEntity entity = mList.get(position);
            if (TextUtils.isEmpty(entity.type2)) {
                return 0;
            } else {
                return 1;
            }
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder ;

            int type = getItemViewType(position);
            if (type == 0 && convertView == null) {
                convertView = View.inflate(mContext, R.layout.item_layout, null);
                holder = new ViewHolder();
                holder.tv1 = (TextView) convertView.findViewById(R.id.tv_type1);
                holder.tv2 = (TextView) convertView.findViewById(R.id.tv_type2);
                convertView.setTag(holder);
            } else if (type == 1 && convertView == null) {
                convertView = View.inflate(mContext, R.layout.item_layout, null);
                holder = new ViewHolder();
                holder.tv1 = (TextView) convertView.findViewById(R.id.tv_type1);
                holder.tv2 = (TextView) convertView.findViewById(R.id.tv_type2);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

           if(type == 0){
               holder.tv1.setText(mList.get(position).type1);
           } else {
               holder.tv2.setText(mList.get(position).type2);
           }

            return convertView;
        }

    }

    public static class ViewHolder {
        public static TextView tv1;
        public static TextView tv2;
    }
}
