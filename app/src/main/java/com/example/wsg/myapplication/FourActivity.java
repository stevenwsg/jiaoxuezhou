package com.example.wsg.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by wsg on 2016/12/30.
 */

public class FourActivity extends Activity {
    private  static final int FLAG_HOMEKEY_DISPATCHED = 0x80000000;


    private TextView textView,textView1,textView2;
    private String time,time1,time3;
//    private String DEFAULT_TIME_FORMAT = "yyyy-MM-dd";
    private String DEFAULT_TIME_FORMAT1 = "HH:mm";

    private Button btn_open;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(FLAG_HOMEKEY_DISPATCHED, FLAG_HOMEKEY_DISPATCHED);//关键代码
        setContentView(R.layout.fourlayout);
//        设计界面
        handler.post(updateThread);

        textView = (TextView) findViewById(R.id.textView8);
        textView1=(TextView)findViewById(R.id.textView7);
        textView2=(TextView)findViewById(R.id.textView10);















        //解锁的按钮
        btn_open=(Button) findViewById(R.id.button4);

        btn_open.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                finish();
            }
        });
    }
    @SuppressWarnings("static-access")
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if(keyCode==event.KEYCODE_BACK){
            return true;
        }
        if(keyCode==event. KEYCODE_HOME){
            return true;
        }
        if(keyCode == KeyEvent.KEYCODE_MENU) {//MENU键
            //监控/拦截菜单键
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    Handler handler = new Handler();//创建Handler
    Runnable updateThread = new Runnable() {
        public void run() {
            handler.postDelayed(updateThread, 1000);
            SimpleDateFormat dateFormatter = new SimpleDateFormat(
                    DEFAULT_TIME_FORMAT1);
            time = dateFormatter.format(Calendar.getInstance().getTime());//获取当前日期和星期



            final Calendar b = Calendar.getInstance();
             String mWay = String.valueOf(b.get(Calendar.DAY_OF_WEEK));
//            String mWay = String.valueOf(Calendar.DAY_OF_WEEK);
                  if("1".equals(mWay)){
                                mWay ="天";
                           }else if("2".equals(mWay)){
                           mWay ="一";
                            }else if("3".equals(mWay)){
                           mWay ="二";
                            }else if("4".equals(mWay)){
                               mWay ="三";
                           }else if("5".equals(mWay)){
                               mWay ="四";
                          }else if("6".equals(mWay)){
                               mWay ="五";
                           }else if("7".equals(mWay)){
                               mWay ="六";
                           }




//           SimpleDateFormat dateFormatter1 = new SimpleDateFormat(
//                    DEFAULT_TIME_FORMAT1);
//            time1 = dateFormatter.format(Calendar.getInstance().getTime());//获取当前时间
//            time1=time1+"  星期"+mWay;
//            重新计算教学周
            int year,month,day,year2,month2,day2,i1,i2,week;
            Calendar c = Calendar.getInstance();
            year = c.get(Calendar.YEAR);
            month = c.get(Calendar.MONTH)+1;
            day = c.get(Calendar.DAY_OF_MONTH);
            time1=month+"月"+day+"日";
            time1=time1+"  星期"+mWay;
            i1=dayofyear(year,month,day);
            Intent intent1=getIntent();
            SharedPreferences pref=getSharedPreferences("data",MODE_PRIVATE);
            year2=pref.getInt("year2",year);
            month2=pref.getInt("month2",1);
            day2=pref.getInt("day2",1);
            i2=dayofyear(year2,month2,day2);
            if(year>year2){
                if((year2%4==0)&&(year2%100!=0)||(year2%400==0)){
                    i1=i1+366;
                }
                else
                { i1=i1+365;}
            }
            week=(i1-i2)/7+1;



            time3="教学周:"+week;

            textView.setText(time1);
            textView1.setText(time);
            textView2.setText(time3);
        }
    };
    public int dayofyear(int year,int month,int day){
        int dateSum = 0;
        for(int i = 1; i < month; i++){
            switch(i){
                case 1:
                case 3:
                case 5: case 7: case 8: case 10: case 12:dateSum += 31; break;
                case 4: case 6: case 9: case 11:dateSum += 30; break;
                case 2:
                    if(((year % 4 == 0) &&(year % 100 != 0)) || (year % 400 == 0))
                        dateSum += 29;
                    else dateSum += 28;
                default:
            }
        }
        return dateSum = (dateSum + day);
    }


}

