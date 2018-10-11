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
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * Created by Tao on 2018/10/10.
 */

public class InvervalRangeCreatActivity extends BaseActivty {
    
    @BindView(R.id.tv_log)
    TextView tvLog ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_inverval_range);
    }

      Integer i = 10 ;
    @OnClick(R.id.inverval__range01)
    public void inverval__range01(){
        /**
         *   指定第一次执行延时  1 指定执行间隔  2
         *    指定执行开始指示 0 指定执行次数  20
         */
        showLog("  inverval__range01 " , tvLog);

        Observable.intervalRange(0,20,1,2, TimeUnit.SECONDS)
            .observeOn(AndroidSchedulers.mainThread())    
        .subscribe(new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {
                showLog("  onSubscribe " , tvLog);

            }

            @Override
            public void onNext(Long integer) {
                showLog("  inverval__range01 onNext " + integer , tvLog);

            }

            @Override
            public void onError(Throwable e) {
                showLog("  onError " + e.getMessage() , tvLog);

            }

            @Override
            public void onComplete() {
                showLog("  onComplete " , tvLog);

            }
        });
        
        
    }

    @OnClick(R.id.inverval_range02)
    public void inverval_range02(){
        showLog("  inverval_range02 " , tvLog);

        Observable.intervalRange(0,20,1,2, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
      .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long integer) throws Exception {
                        showLog("Consumer accept " + integer , tvLog);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        showLog("Consumer Throwable" , tvLog);

                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        showLog("Action  run" , tvLog);

                    }
                }, new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        showLog("Consumer  accept "  + disposable.isDisposed(), tvLog);

                    }
                });


    }
}
