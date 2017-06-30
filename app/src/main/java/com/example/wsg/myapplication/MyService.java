package com.example.wsg.myapplication;
import android.app.KeyguardManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;


/**
 * Created by wsg on 2016/12/30.
 */
public class MyService extends Service {
    private KeyguardManager km;
    private KeyguardManager.KeyguardLock kk;
    private BroadcastReceiver broadcastReceiver=new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent arg1) {
//            Log.e("-----可以跳到锁屏界面--------", "---------");
            Intent intent = new Intent(context, FourActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

        }
    };

    public IBinder onBind(Intent intent) {
        return null;
    }
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);

        //服务里面收到关闭屏幕的动作就发送广播
        IntentFilter iFilter=new IntentFilter(Intent.ACTION_SCREEN_OFF);
//        服务里面收到开户的动作就发送广播
        IntentFilter ifilter=new IntentFilter(Intent.ACTION_BOOT_COMPLETED);
        this.registerReceiver(broadcastReceiver,ifilter);
        this.registerReceiver(broadcastReceiver, iFilter);

    }



    @Override
    public void onCreate() {
        super.onCreate();

        //屏蔽掉系统的锁屏
        km=(KeyguardManager) getSystemService(Context.KEYGUARD_SERVICE);
        kk=km.newKeyguardLock("");
        kk.disableKeyguard();
    }

    public void onDestroy() {
        super.onDestroy();
    }
}
