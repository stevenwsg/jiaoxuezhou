package com.example.wsg.myapplication;
import android.app.Activity;
import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.tencent.bugly.crashreport.CrashReport;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends Activity {
    MyBroadcast receiveBroadCast;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        CrashReport.initCrashReport(getApplicationContext(),"c6c458e7bd",false);




        receiveBroadCast = new MyBroadcast();
        IntentFilter filter = new IntentFilter();
        filter.addAction("action"); // 只有持有相同的action的接受者才能接收此广播
        registerReceiver(receiveBroadCast, filter);
        //发送广播
        Intent intent = new Intent();
        intent.setAction("action");
        this.sendBroadcast(intent);
        Button button1 = (Button) findViewById(R.id.button);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ScendActivity.class);
                startActivity(intent);
            }
        });
        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(MainActivity.this, ThirdActivity.class);
                startActivity(intent);
            }

        });
        int year, month, day, year2, month2, day2, i1, i2, week;
        Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH) + 1;
        day = c.get(Calendar.DAY_OF_MONTH);
        i1 = dayofyear(year, month, day);

        Intent intent1 = getIntent();
        SharedPreferences pref = getSharedPreferences("data", MODE_PRIVATE);
        year2 = pref.getInt("year2", year);
        month2 = pref.getInt("month2", 1);
        day2 = pref.getInt("day2", 1);
        i2 = dayofyear(year2, month2, day2);
        if (year > year2) {
            if ((year2 % 4 == 0) && (year2 % 100 != 0) || (year2 % 400 == 0)) {
                i1 = i1 + 366;
            } else {
                i1 = i1 + 365;
            }
        }
        week = (i1 - i2) / 7 + 1;
        final TextView text = (TextView) findViewById(R.id.textView2);
        text.setText("教学周" + week);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public int dayofyear(int year, int month, int day) {
        int dateSum = 0;
        for (int i = 1; i < month; i++) {
            switch (i) {
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12:
                    dateSum += 31;
                    break;
                case 4:
                case 6:
                case 9:
                case 11:
                    dateSum += 30;
                    break;
                case 2:
                    if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0))
                        dateSum += 29;
                    else dateSum += 28;
                default:
            }
        }
        return dateSum = (dateSum + day);
    }


    private boolean quit = false;

    public void onBackPressed() {
        if (quit == false) {
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            new Timer(true).schedule(new TimerTask() {
                public void run() {
                    quit = false;
                }
            }, 2000);
            quit = true;
        } else {
            super.onBackPressed();
            finish();
        }
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }


    public static class MyBroadcast extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {

            KeyguardManager km = (KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE);

            KeyguardManager.KeyguardLock kk = km.newKeyguardLock("");
            kk.disableKeyguard();

            //开始服务
            Intent service = new Intent();
            service.setClass(context, MyService.class);//稍后再定义
            context.startService(service);
        }

    }

    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();


        unregisterReceiver(receiveBroadCast);
    }


}

