package com.liu.manytypeslistview;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

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

//        for (int count = 0; count < 100; count++) {
//            MyEntity entity = new MyEntity("","这是第" + count + "条数据");
//            list.add(entity);
//        }
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

//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//            ViewHolder1 holder1;
//            if (convertView == null) {
//                holder1 = new ViewHolder1();
//                convertView = View.inflate(mContext, R.layout.item1_layout, null);
//                holder1.textView = (TextView) convertView.findViewById(R.id.tv);
//
//                convertView.setTag(holder1);
//            } else {
//                holder1 = (ViewHolder1) convertView.getTag();
//            }
//
//            holder1.textView.setText(mList.get(position).type2);
//            return convertView;
//        }
//    }


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

//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//            int type = getItemViewType(position);
//            switch (type) {
//                case 0:
//                    ViewHolder1 holder1;
//                    if (convertView == null) {
//                        convertView = View.inflate(mContext, R.layout.item1_layout, null);
//                        holder1 = new ViewHolder1();
//                        holder1.textView = (TextView) convertView.findViewById(R.id.tv);
//                        convertView.setTag(holder1);
//                    } else {
//                        holder1 = (ViewHolder1) convertView.getTag();
//                    }
//
//                    MyEntity entity1 = mList.get(position);
//                    if (entity1 != null) {
//                        if (holder1.textView != null) {
//                            holder1.textView.setText(entity1.type1);
//                        }
//                    }
//
//                    return convertView;
//                case 1:
//                    ViewHolder2 holder2;
//                    if (convertView == null) {
//                        convertView = View.inflate(mContext, R.layout.item2_layout, null);
//                        holder2 = new ViewHolder2();
//                        holder2.textView = (TextView) convertView.findViewById(R.id.text);
//                        convertView.setTag(holder2);
//                    } else {
//                        holder2 = (ViewHolder2) convertView.getTag();
//                    }
//
//                    MyEntity entity2 = mList.get(position);
//                    if (entity2 != null) {
//                        if (holder2.textView != null) {
//                            holder2.textView.setText(entity2.type2);
//                        }
//                    }
//
//                    return convertView;
//                default:
//            }
//            return convertView;
//        }
//    }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder1 holder1 = null;
            ViewHolder2 holder2 = null;

            int index = (int) getItemId(position);

            int type = getItemViewType(position);
            System.out.println("position  ===  " + position + "   type  ===  " + type);

            if (convertView == null) {
                System.out.println("contentView 是空  " + position);
                if (type == 0) {
                    System.out.println("contentView 是空的时候并且type == 0" + position);
                    convertView = View.inflate(mContext, R.layout.item_layout, null);
                    holder1 = new ViewHolder1();
                    holder1.tv1 = (TextView) convertView.findViewById(R.id.tv1);
                    holder1.tv2 = (TextView) convertView.findViewById(R.id.tv2);
                    convertView.setTag(holder1);
                } else if (type == 1) {
                    System.out.println("contentView 是空的时候并且type == 1" + position);
                    convertView = View.inflate(mContext, R.layout.item_layout, null);
                    holder2 = new ViewHolder2();
                    holder2.tv1 = (TextView) convertView.findViewById(R.id.tv1);
                    holder2.tv2 = (TextView) convertView.findViewById(R.id.tv2);
                    convertView.setTag(holder2);
                }
            } else {
                System.out.println("contentView 不不不不不不不不不不空的时候" + position);
                if (type == 0) {
                    System.out.println("contentView 不不不不不不不不不不空的时候并且type == 0" + position);
                    holder1 = (ViewHolder1) convertView.getTag();
                } else {
                    System.out.println("contentView 不不不不不不不不不不空的时候并且type == 1" + position);
                    holder2 = (ViewHolder2) convertView.getTag();
                }
            }

            if (type == 0) {
                System.out.println("这里type == 0");
                System.out.println(position + "        ==========   position ");
                System.out.println(index + "        ==========   index ");
                System.out.println(mList.get(position).type1 + "        ============   position   ++++  type1");
                holder1.tv1.setText(mList.get(index).type1);
                holder1.tv2.setText("");
            } else {
                System.out.println("这里type == 1");
                System.out.println(position + "        ==========   position     2");
                System.out.println(index + "        ==========   index          2 ");
                System.out.println(mList.get(position).type2 + "        ============   position   ++++  type2");
                holder2.tv1.setText("");
                holder2.tv2.setText(mList.get(index).type2);
            }
            return convertView;
        }


        }
    static class ViewHolder1 {
        public TextView tv1;
        public TextView tv2;
//        public TextView textView;
    }

        static class ViewHolder2 {
            public TextView tv1;
            public TextView tv2;
//            public TextView textView;
        }
    }