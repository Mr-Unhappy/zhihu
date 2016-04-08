package com.liu.zhihu.activity;

import android.support.v7.app.ActionBarActivity;
import android.widget.Toast;

/**
 * Created by Ming on 2016/2/24.
 */
public class BaseActivity extends ActionBarActivity{

    public void showToast(String text){
        Toast.makeText(this,text,Toast.LENGTH_SHORT).show();
    }

    public void showToast(int rId){
        CharSequence text = this.getResources().getText(rId);
        Toast.makeText(this,text,Toast.LENGTH_SHORT).show();
    }



}
