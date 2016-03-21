package com.liu.zhihu.mannger;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Ming on 2016/3/11.
 */
public class SharedPreferencesManager {

    private static final String DATA_NAME = "zhihu_data";
    private static final String SPLITCHAR = ",";

    public enum Field {
        USER_NAME;
    }

    public int getInt(Context context, Field field, int defValue) {
        return context.getSharedPreferences(DATA_NAME, Context.MODE_PRIVATE).getInt(field.name(), defValue);
    }

    public void setInt(Context context, Field field, int value) {
        SharedPreferences.Editor editor = context.getSharedPreferences(DATA_NAME, Context.MODE_PRIVATE).edit();
        editor.putInt(field.name(), value);
    }

    public boolean getBoolean(Context context, Field field, boolean defValue) {
        return context.getSharedPreferences(DATA_NAME, Context.MODE_PRIVATE).getBoolean(field.name(), defValue);
    }

    public void setBoolean(Context context, Field field, boolean value) {
        SharedPreferences.Editor editor = context.getSharedPreferences(DATA_NAME, Context.MODE_PRIVATE).edit();
        editor.putBoolean(field.name(), value);
        editor.commit();
    }

    public String getString(Context context, Field field, String defValue) {
        return context.getSharedPreferences(DATA_NAME, Context.MODE_PRIVATE).getString(field.name(), defValue);
    }

    public void setString(Context context, Field field, String value) {
        SharedPreferences.Editor editor = context.getSharedPreferences(DATA_NAME, Context.MODE_PRIVATE).edit();
        editor.putString(field.name(), value);
        editor.commit();
    }

    public long getLong(Context context, Field field, long defValue) {
        return context.getSharedPreferences(DATA_NAME, Context.MODE_PRIVATE).getLong(field.name(), defValue);
    }

    public void setLong(Context context, Field field, long value) {
        SharedPreferences.Editor editor = context.getSharedPreferences(DATA_NAME, Context.MODE_PRIVATE).edit();
        editor.putLong(field.name(), value);
        editor.commit();
    }

    public void setLongArray(Context context, Field field, ArrayList<Long> list) {
        if (list != null && !list.isEmpty()) {
            String str = "";
            for (Long itme : list) {
                str = str + String.valueOf(itme) + SPLITCHAR;
            }
            SharedPreferences.Editor editor = context.getSharedPreferences(DATA_NAME, Context.MODE_PRIVATE).edit();
            editor.putString(field.name(), str);
            editor.commit();
        }
    }

    public List<Long> getLongArray(Context context, Field field) {
        String str = context.getSharedPreferences(DATA_NAME, Context.MODE_PRIVATE).getString(field.name(), "");
        List<Long> list = new ArrayList<>();
        if (!TextUtils.isEmpty(str)) {
            String[] splits = str.split(SPLITCHAR);
            for (int i = 0; i < splits.length; i++) {
                list.add(Long.valueOf(splits[i]));
            }
        }
        return list;
    }

    public void setStringArray(Context context, Field field, ArrayList<String> list) {
        if (list != null && !list.isEmpty()) {
            String str = "";
            for (String itme : list) {
                str = str + String.valueOf(itme) + SPLITCHAR;
            }
            SharedPreferences.Editor editor = context.getSharedPreferences(DATA_NAME, Context.MODE_PRIVATE).edit();
            editor.putString(field.name(), str);
            editor.commit();
        }
    }

    public List<String> getStringArray(Context context, Field field) {
        String str = context.getSharedPreferences(DATA_NAME, Context.MODE_PRIVATE).getString(field.name(), "");
        List<String> list = new ArrayList<>();
        if (!TextUtils.isEmpty(str)) {
            String[] splits = str.split(SPLITCHAR);
            list = Arrays.asList(splits);
        }
        return list;
    }

    public void removeKey(Context context, Field field) {
        SharedPreferences.Editor editor = context.getSharedPreferences(DATA_NAME, Context.MODE_PRIVATE).edit();
        editor.remove(field.name());
        editor.commit();
    }

}
