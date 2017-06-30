package com.example.wsg.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;

import com.tencent.bugly.crashreport.CrashReport;


/**
 * Created by wsg on 2016/12/18.
 */

public class ScendActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.scendlayout);


    }
}
