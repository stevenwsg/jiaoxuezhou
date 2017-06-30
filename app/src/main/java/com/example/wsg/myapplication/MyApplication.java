package com.example.wsg.myapplication;

import android.app.Application;
import android.content.Context;


/**
 * Created by wsg on 2017/2/11.
 */

public class MyApplication extends Application {

    private static Context context;


    public void onCreate() {
        context=getApplicationContext();
    }
    public static Context getContext(){
        return context;
    }
}
