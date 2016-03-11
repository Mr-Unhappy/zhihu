package com.liu.zhihu.application;

import android.app.Application;
import android.content.Context;
import android.hardware.display.DisplayManager;

/**
 * Created by Ming on 2016/2/24.
 */
public class MyApplication extends Application {

    private static Context applicationContext;
    private float density;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationContext = this;
        density = getResources().getDisplayMetrics().density;
    }

    public int px2dp(int px) {
        return (int) (px / density + 0.5f);
    }

    public int dp2px(int dp) {
        return (int) (dp * density + 0.5f);
    }

    public static Context getContext(){
        return applicationContext;
    }

}
