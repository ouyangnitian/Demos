package com.example.dagger;

import android.util.Log;

import javax.inject.Inject;

/**
 * Created by ouyang on 17/7/13.
 */

public class SPresenter {

    String TAG = "SP";

    @Inject
    public SPresenter(String name){
        Log.i(TAG,"SP: " + name);
    }

    public void sayHi(){
        Log.i(TAG,"Spresenter hi");
    }
}
