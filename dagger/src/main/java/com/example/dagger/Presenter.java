package com.example.dagger;

import android.util.Log;

import javax.inject.Inject;

import dagger.Provides;

/**
 * Created by ouyang on 17/7/12.
 */

public class Presenter {

    String TAG = "Presenter";
    int s_count;
//    String name;

    @Inject
    public Presenter(int count,String name){
        s_count = count;
        Log.i(TAG,"name is " + name);
    }

    public void say(){
        Log.i(TAG,"hello presenter");
    }
}
