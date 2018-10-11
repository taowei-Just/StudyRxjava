package com.tao.rxtest.createoperate;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.tao.rxtest.BaseActivty;
import com.tao.rxtest.R;
import com.tao.rxtest.createoperate.delaycreate.DeferCreatActivity;
import com.tao.rxtest.createoperate.delaycreate.InvervalCreatActivity;
import com.tao.rxtest.createoperate.delaycreate.InvervalRangeCreatActivity;
import com.tao.rxtest.createoperate.delaycreate.RangeCreatActivity;
import com.tao.rxtest.createoperate.delaycreate.TimerCreatActivity;
import com.tao.rxtest.createoperate.fastcreate.FromArrayCreatActivity;
import com.tao.rxtest.createoperate.fastcreate.JustCreatActivity;
import com.tao.rxtest.transform.MapActivity;

import butterknife.OnClick;

/**
 * Created by Tao on 2018/10/10.
 */

public class TrtansformOperateActivity extends BaseActivty {
    String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transform);

    }

    @OnClick(R.id.map)
    public void map() {
        startActivity(new Intent(this, MapActivity.class));
    }

    @OnClick(R.id.bt_just)
    public void just() {
        startActivity(new Intent(this, JustCreatActivity.class));
    }

    @OnClick(R.id.bt_assembly_operate)
    public void fromarray() {
        startActivity(new Intent(this, FromArrayCreatActivity.class));
    }

    @OnClick(R.id.bt_defer)
    public void bt_defer() {
        startActivity(new Intent(this, DeferCreatActivity.class));
    }
 @OnClick(R.id.bt_timer)
    public void bt_timer() {
        startActivity(new Intent(this, TimerCreatActivity.class));
    }
 @OnClick(R.id.bt_inverval)
    public void bt_inverval() {
        startActivity(new Intent(this, InvervalCreatActivity.class));
    }
 @OnClick(R.id.bt_inverval_range)
    public void bt_inverval_range() {
        startActivity(new Intent(this, InvervalRangeCreatActivity.class));
    }
 @OnClick(R.id.bt_range)
    public void bt_range() {
        startActivity(new Intent(this, RangeCreatActivity.class));
    }


}
