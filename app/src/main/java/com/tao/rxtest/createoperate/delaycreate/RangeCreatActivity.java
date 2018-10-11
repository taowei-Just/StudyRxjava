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

public class RangeCreatActivity extends BaseActivty {
    
    @BindView(R.id.tv_log)
    TextView tvLog ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create__range);
    }

      Integer i = 10 ;
    @OnClick(R.id. _range01)
    public void inverval__range01(){
        /**
          指定发送事件的区间  0-20   区别于 invervalRange 事件是无延迟发送
         */
        showLog("  _range01 " , tvLog);

        Observable.range(0,20)
            .observeOn(AndroidSchedulers.mainThread())    
        .subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                showLog("  onSubscribe " , tvLog);
            }

            @Override
            public void onNext(Integer integer) {
                showLog("  _range01 onNext " + integer , tvLog);
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

    @OnClick(R.id. _range02)
    public void inverval_range02(){
        showLog("  _range02 " , tvLog);

        Observable. range(0,20 )
                .observeOn(AndroidSchedulers.mainThread())
      .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
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
