package com.tao.rxtest.createoperate.delaycreate;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.tao.rxtest.BaseActivty;
import com.tao.rxtest.R;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Tao on 2018/10/10.
 */

public class InvervalCreatActivity extends BaseActivty {
    @BindView(R.id.tv_log)
    TextView tvLog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_inverval);
    }
    
    @OnClick(R.id.inverval_01)
    public void inverval_01() {
        /**
         *    指定延时发送一个数 3s
         */
        showLog("  inverval_01 ", tvLog);
        Observable.interval(3, 1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        showLog("  onSubscribe ", tvLog);
                    }

                    @Override
                    public void onNext(Long integer) {
                        showLog("  inverval_01 onNext " + integer, tvLog);
                    }

                    @Override
                    public void onError(Throwable e) {
                        showLog("  onError " + e.getMessage(), tvLog);
                    }

                    @Override
                    public void onComplete() {
                        showLog("  onComplete ", tvLog);
                    }
                });
    }

    @OnClick(R.id.inverval02)
    public void inverval02() {
        showLog("  inverval02 ", tvLog);

        Observable.interval(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long integer) throws Exception {
                        showLog("Consumer accept " + integer, tvLog);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        showLog("Consumer Throwable", tvLog);

                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        showLog("Action  run", tvLog);

                    }
                }, new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        showLog("Consumer  accept " + disposable.isDisposed(), tvLog);

                    }
                });


    }
}
