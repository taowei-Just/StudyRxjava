package com.tao.rxtest.createoperate.fastcreate;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.tao.rxtest.BaseActivty;
import com.tao.rxtest.R;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * Created by Tao on 2018/10/10.
 */

public class FromArrayCreatActivity extends BaseActivty {
    
    @BindView(R.id.tv_log)
    TextView tvLog ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_fromarray);
    }
    
  
    @OnClick(R.id.fromarray_01)
    public void fromarray01(){
        /**
         * 10个以上数据顺序发送
         */
Integer [] items = new Integer[]{0,1,2,3,4,5,6,7,8,9 ,10 ,11 ,12};
        showLog("fromarray01" , tvLog);
        Observable.fromArray(items)
        .subscribe(new Observer<Integer>() {
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

    @OnClick(R.id.fromarray_02)
    public void fromarray02(){

        showLog("fromarray02" , tvLog);
        Integer [] items = new Integer[]{0,1,2,3,4,5,6,7,8,9 ,10 ,11 ,12};
        showLog("fromarray01" , tvLog);
        Observable.fromArray(items)
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
