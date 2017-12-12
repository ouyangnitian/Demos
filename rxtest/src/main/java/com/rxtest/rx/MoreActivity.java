package com.rxtest.rx;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.rxtest.R;

import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.schedulers.NewThreadScheduler;
import rx.schedulers.Schedulers;

/**
 * Created by ouyang on 2017/11/14.
 */

public class MoreActivity extends Activity {

    @Bind(R.id.hello) TextView mTvText;
    private String TAG = this.getClass().getSimpleName();
    final String[] mManyWords = {"Hello", "I", "am", "your", "friend", "Spike"};
    final List<String> mManyWordList = Arrays.asList(mManyWords);

    @Override
    public void onCreate(Bundle bundle ){
        super.onCreate(bundle);
        setContentView(R.layout.activity_simple);
        ButterKnife.bind(this);
        init();

    }

    // 设置映射函数
    private Func1<List<String>, Observable<String>> mOneLetterFunc = new Func1<List<String>, Observable<String>>() {
        @Override public Observable<String> call(List<String> strings) {
            return Observable.from(strings); // 映射字符串
        }
    };

    // 设置大写字母
    private Func1<String, String> mUpperLetterFunc = new Func1<String, String>() {
        @Override public String call(String s) {
            return s.toUpperCase(); // 大小字母
        }
    };

    // 连接字符串
    private Func2<String, String, String> mMergeStringFunc = new Func2<String, String, String>() {
        @Override public String call(String s, String s2) {
            return String.format("%s %s", s, s2); // 空格连接字符串
        }
    };

    public String[] getList(){
        Log.i(TAG,"getList()");
        return mManyWords;
    }


    public void init(){
        Log.i(TAG,"init()");
//        Observable<String> observable1 = Observable.just("hello Jack!");
//        observable1.observeOn(AndroidSchedulers.mainThread())
//                .map(x->x.toUpperCase() + "..")
//                .subscribe(new Action1<String>() {
//                    @Override
//                    public void call(String s) {
//                        mTvText.setText(s);
//                    }
//                });

//        Observable<String> observable2 = Observable.from(getList());
//        observable2
//                .observeOn(Schedulers.newThread())
////                .subscribeOn(Schedulers.newThread())
//                .map(x->x.toUpperCase() + "_")
//                .subscribe(new Action1<String>() {
//                    @Override
//                    public void call(String s) {
//                        Log.i(TAG,"hah: " + s);
//                    }
//                });

        Observable.just(mManyWordList)
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(l->Observable.from(l))
                .map(x->x.toUpperCase())
                .reduce((x,y)->x + "-" + y)
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Log.i(TAG,"call3: " + s);
                    }
                });

        Observable.create(new Observable.OnSubscribe<String>(){
            @Override
            public void call(Subscriber<? super String> subscriber) {

                Log.i(TAG,"call ....");
                while(true){
                    int randommath = (int) (Math.random() * 10);
                    if(randommath * 10 > 3){
                        subscriber.onNext(randommath + "");
                    }else if(randommath * 10 > 1){
                        subscriber.onNext(randommath + "");
                    }else{
                        subscriber.onError(new Throwable("<1 error"));
                    }
                }

            }
        })
                .observeOn(Schedulers.newThread())
//                .subscribeOn(Schedulers.newThread())
                .subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                Log.i(TAG,"onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG,"onError" + e);
            }

            @Override
            public void onNext(String s) {
                Log.i(TAG,"onNext : " + s);
            }
        });

    }



}
