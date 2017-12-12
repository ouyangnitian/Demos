package com.rxtest.rx;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.rxtest.R;

import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by ouyang on 2017/11/14.
 */

public class SimpleActivity extends Activity {

    TextView textView;
    private String TAG = this.getClass().getSimpleName();

    @Override
    public void onCreate(Bundle bundle ){
        super.onCreate(bundle);
        setContentView(R.layout.activity_simple);
        textView = (TextView)findViewById(R.id.hello);
        init();

    }

    public void init(){

        Observable.OnSubscribe mObservableAction = new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("hello haha "); // 发送事件
                subscriber.onCompleted(); // 完成事件
            }
        };

        Subscriber<String> subscriber1 = new Subscriber<String>() {
            @Override
            public void onCompleted() {
                Log.i(TAG,"onCompleted()");
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG,"onError()");
            }

            @Override
            public void onNext(String s) {
                Log.i(TAG,"onNext()");
                textView.setText(s);
            }
        };

        ButterKnife.bind(this);

        // 注册观察活动
        @SuppressWarnings("unchecked")
        Observable<String> observable = Observable.create(mObservableAction);

        // 分发订阅信息
        observable.observeOn(AndroidSchedulers.mainThread());
        observable.subscribe(subscriber1);
    }



}
