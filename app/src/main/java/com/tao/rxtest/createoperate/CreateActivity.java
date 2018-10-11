package com.tao.rxtest.createoperate;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.tao.rxtest.BaseActivty;
import com.tao.rxtest.R;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Tao on 2018/10/10.
 */

public class CreateActivity extends BaseActivty {

    String TAG = getClass() .getSimpleName() ;
    @BindView(R.id.bt_create)
    Button btCreate; 
    @BindView(R.id.tv_log)
    TextView tvLog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_);
    }
    
    @OnClick(R.id.bt_create_Subscriber)
    public void createObervableSubscriber(){

        showLog(" create observable Subscriber" ,tvLog);
        showLog(" 本方法暂时失效 待研究 " ,tvLog);

        Observable<Integer> integerObservable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
               
                showLog( " subscribe  开始 发射事件 ",tvLog);
                for (int i=0 ; i <5 ; i++)
                    e.onNext(i);
                e.onError(new Throwable(" im error 1"));
                e.onNext(-1);
                e.onComplete();
                e.onNext(-2);
            }
        });

        showLog( "  创建 Subscriber  ",tvLog);
        Subscriber<Integer> subscriber = new Subscriber<Integer>() {
            
            
            @Override
            public void onSubscribe(Subscription s) {
                showLog( "  创建 onSubscribe  ",tvLog);

            }

            @Override
            public void onNext(Integer integer) {
                showLog( "    onNext  " + integer ,tvLog);

            }

            @Override
            public void onError(Throwable t) {
                showLog( " onError  " + t.getMessage(),tvLog);

            }

            @Override
            public void onComplete() {
                showLog( " onComplete  "  ,tvLog);

            }
        };
        
//        integerObservable.subscribe((Observer<? super Integer>) subscriber);

    }
    
    @OnClick(R.id.bt_create)
   
    public void createObervable(){

        // 使用create操作符创建 被观察者（事件发送）
        
        
        showLog(" create observable _Observer" ,tvLog);

        /**
         * onComplete之后观察者无法接收到事件 onerror会抛异常
         * onError 之后观察者也无法接收到任何事件 但本次error 会被捕获   后续onError 并不会被捕获
         */
        Observable<Integer> integerObservable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {

                showLog( " 开始 发射事件 ",tvLog);
                for (int i=0 ; i <5 ; i++)
                e.onNext(i);
                e.onError(new Throwable(" im error 1"));
                e.onNext(-1);
                e.onComplete();
                e.onNext(-2);
          

            }
        });
        // 创建观察者（事件接收者）
        showLog( "  创建 观察者 ",tvLog);

        Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                showLog( "   onSubscribe  ",tvLog);
            }
            @Override
            public void onNext(Integer integer) {
                showLog( "   onNext  " + integer,tvLog);
            }
            @Override
            public void onError(Throwable e) {
                showLog( "   onError  " + e.getMessage(),tvLog);
            }
            @Override
            public void onComplete() {
                showLog( "   onComplete  " ,tvLog);

            }
        };
        showLog( "   观察者 订阅被观察者事件  ",tvLog);
   

        // 订阅事件 被观察者事件开始发射 
        integerObservable.subscribe(observer);
        
       
    }

    Disposable dis ;
    @OnClick(R.id.bt_create_disposable)
    public void disposableObervable(){

        // 使用create操作符创建 被观察者（事件发送）


        showLog(" create observable _Observer" ,tvLog);

        /**
         * onComplete之后观察者无法接收到事件 onerror会抛异常
         * onError 之后观察者也无法接收到任何事件 但本次error 会被捕获   后续onError 并不会被捕获
         * disposable 切断观察者与被观察者的联系 但是被观察者仍然可以发送事件但是观察者无法接收
         *  后续onError 并不会被捕获
         * 
         */
        Observable<Integer> integerObservable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {

                showLog( " 开始 发射事件 ",tvLog);
                for (int i=0 ; i <5 ; i++)
                    e.onNext(i);
//                e.onError(new Throwable(" im error 1"));
                e.onNext(-1);
                e.onComplete();
                e.onNext(-2);


            }
        });
        // 创建观察者（事件接收者）
        showLog( "  创建 观察者 ",tvLog);

      
        Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                showLog( "   onSubscribe  ",tvLog);
                dis =d ;
            }
            @Override
            public void onNext(Integer integer) {
                showLog( "   onNext  " + integer,tvLog);
                if (integer==3 ){
                    dis.dispose();
                    showLogE( "    dis.dispose  "  ,tvLog);
                }
            }
            @Override
            public void onError(Throwable e) {
                showLog( "   onError  " + e.getMessage(),tvLog);
            }
            @Override
            public void onComplete() {
                showLog( "   onComplete  " ,tvLog);

            }
        };
        showLog( "   观察者 订阅被观察者事件  ",tvLog);


        // 订阅事件 被观察者事件开始发射 
        integerObservable.subscribe(observer);


    }
}
