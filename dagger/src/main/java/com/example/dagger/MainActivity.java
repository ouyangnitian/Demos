package com.example.dagger;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    /**
     * Dagger是了为解耦而存在的
     *
     * Inject   : 实例对像要注入，对象的构造方法必然也有Inject注释
     * Module   : 要注入对象的参数集中到此处，可方便构造假数据测试等
     * Provide  : 在Module中，提供的参数
     * Component: 注入的中间
     *
     * */

    @Inject
    Presenter presenter;

    @Inject
    SPresenter sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("MainActivity","onCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DaggerMainComponent.builder()
                .pModule(new PModule(3,"haha"))
                .build()
                .inject(this);

        presenter.say();
        sp.sayHi();
    }
}
