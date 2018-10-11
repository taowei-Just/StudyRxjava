package com.tao.rxtest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import com.tao.rxtest.createoperate.CreateOperateActivity;
import com.tao.rxtest.createoperate.TrtansformOperateActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivty {
    String TAG = getClass() .getSimpleName() ;

    @BindView(R.id.bt_create_operate)
    Button createButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
 
    }
 
    @OnClick(R.id.bt_create_operate)
    public void create(){
        Log.e(TAG , "create");
        startActivity(new Intent(this , CreateOperateActivity.class));
    }
    
    @OnClick(R.id.bt_convetor_operate)
    public void bt_convetor_operate(){
        Log.e(TAG , "transform");
        startActivity(new Intent(this , TrtansformOperateActivity.class));
    }
}
