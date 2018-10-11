package com.tao.rxtest.createoperate.delaycreate;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.tao.rxtest.BaseActivty;
import com.tao.rxtest.R;

import java.util.concurrent.Callable;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * Created by Tao on 2018/10/10.
 */

public class DeferCreatActivity extends BaseActivty {
    
    @BindView(R.id.tv_log)
    TextView tvLog ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_defer);
    }

      Integer i = 10 ;
    @OnClick(R.id.defer_01)
    public void defer01(){
        /**
         *   通过defer 每次订阅的时候 都通过call 创建新的被观察者
         */

        Observable<Integer> defer = Observable.defer(new Callable<ObservableSource<Integer>>() {
            @Override
            public ObservableSource<Integer> call() throws Exception {
                return Observable.just(i);
            }
        });
        
        i+=20 ;
        defer .subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                showLog("  onSubscribe " , tvLog);

            }

            @Override
            public void onNext(Integer integer) {
                showLog("  onNext " + integer , tvLog);

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

    @OnClick(R.id.defer_02)
    public void defer02(){

        Observable<Integer> defer = Observable.defer(new Callable<ObservableSource<Integer>>() {
            @Override
            public ObservableSource<Integer> call() throws Exception {
                return Observable.just(i);
            }
        });

        i+=20 ;
 
        defer .subscribe(new Consumer<Integer>() {
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
