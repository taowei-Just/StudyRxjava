package com.tao.rxtest.transform;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.tao.rxtest.BaseActivty;
import com.tao.rxtest.R;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;

/**
 * Created by Tao on 2018/10/11.
 */

public class MapActivity extends BaseActivty {
    @BindView(R.id.tv_log)
    TextView tvLog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
    }


    @OnClick(R.id.bt_map1)
    public void map1() {
        showLog(" map1 ", tvLog);
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                showLog(" subscribe  ", tvLog);
                for (int i =0 ; i <10 ; i++)
                        e.onNext(i);
                e.onComplete();
            }
        }).map(new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) throws Exception {
                showLog(" Function apply  ", tvLog);

                return integer+" _toString ";
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                showLog(" onSubscribe    ", tvLog);

            }

            @Override
            public void onNext(String s) {
                showLog(" onNext    " + s, tvLog);

            }

            @Override
            public void onError(Throwable e) {
                showLog(" onError  " + e.toString(), tvLog);

            }

            @Override
            public void onComplete() {
                showLog(" onComplete  "  , tvLog);

            }
        });


    }
}
