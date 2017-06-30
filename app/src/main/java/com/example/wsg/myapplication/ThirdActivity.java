package com.example.wsg.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import java.util.regex.Pattern;
import java.util.regex.Matcher;


public class ThirdActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.thirdlayout);
        Button bt3=(Button)findViewById(R.id.button3);
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText2=(EditText)findViewById(R.id.editText2);
                EditText editText3=(EditText)findViewById(R.id.editText3);
                EditText editText4=(EditText)findViewById(R.id.editText4);

//                判断输入是否为空
                if (TextUtils.isEmpty(editText2.getText().toString().trim())&&TextUtils.isEmpty(editText3.getText().toString().trim())&&TextUtils.isEmpty(editText4.getText().toString().trim())){

                    Toast.makeText(ThirdActivity.this,"输入不能为空，请按要求输入",Toast.LENGTH_LONG).show();
////                    判断输入是否为数字
//                    if(!isNumeric(editText2.getText().toString().trim())&&!isNumeric(editText3.getText().toString().trim())&&!isNumeric(editText4.getText().toString().trim())){
//                        Toast.makeText(ThirdActivity.this,"请按要求输入,输入必须为数字",Toast.LENGTH_LONG).show();
//                    }


                }
                else if (!isNumeric(editText2.getText().toString().trim())&&!isNumeric(editText3.getText().toString().trim())&&!isNumeric(editText4.getText().toString().trim())){
                    editText2.setText("");
                    editText3.setText("");
                    editText4.setText("");
                    Toast.makeText(ThirdActivity.this,"请按要求输入,输入必须为数字",Toast.LENGTH_LONG).show();
                }
                else{

                    int year2, month2, day2;
                    year2 = Integer.parseInt(editText2.getText().toString());
                    month2 = Integer.parseInt(editText3.getText().toString());
                    day2 = Integer.parseInt(editText4.getText().toString());
                    SharedPreferences.Editor editor = getSharedPreferences("data", MODE_PRIVATE).edit();
                    editor.putInt("year2", year2);
                    editor.putInt("month2", month2);
                    editor.putInt("day2", day2);
                    editor.commit();
                    Intent intent1 = new Intent(ThirdActivity.this, MainActivity.class);

                    startActivity(intent1);

                    finish();
                }

            }
        });

    }
    public boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
    }


}




