package com.tao.rxtest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import butterknife.ButterKnife;

/**
 * Created by Tao on 2018/10/10.
 */

public class BaseActivty extends AppCompatActivity {
    
    String TAG =getClass().getSimpleName() ;
    
    StringBuilder sb = new StringBuilder( );
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        ButterKnife.bind(this);
    } 
    @Override
    public void setContentView(int id) {
        super.setContentView(id);
        ButterKnife.bind(this);
    }
    
    public void showLog(String msg , TextView tv){
        Log.d(TAG , msg);
        sb.append(msg+"\n ");
        tv.setText(sb.toString());
    }
     public void showLogE(String msg , TextView tv){
        Log.e(TAG , msg);
        sb.append(msg+"\n ");
        tv.setText(sb.toString());
    }
}
