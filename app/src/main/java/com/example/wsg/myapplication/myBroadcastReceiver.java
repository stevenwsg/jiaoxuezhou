package com.example.wsg.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by wsg on 2016/12/30.
 */

public class myBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent service=new Intent();
        service.setClass(context,MyService.class);//稍后再定义
        context.startService(service);
    }
}
