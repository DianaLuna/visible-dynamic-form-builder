package com.example.pengxiang.appproject.function;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pengxiang.appproject.App;
import com.example.pengxiang.appproject.MainActivity;
import com.example.pengxiang.appproject.R;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import static com.example.pengxiang.appproject.App.app;
import static com.example.pengxiang.appproject.App.login_status;

/**
 * Created by pengxiang on 08/06 0006.
 */

public class LoginSuccess extends Activity{
    ImageView back;
    TextView text;
    Button btn1,btn2,btn3,btn4,exit;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.getWindow().addFlags(
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        this.getWindow().addFlags(
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        setContentView(R.layout.user);

        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarTintResource(R.drawable.bg_title_bar2);

        back = (ImageView)findViewById(R.id.imageBack);
        btn1 = (Button)findViewById(R.id.button1);
        btn2 = (Button)findViewById(R.id.button2);
        text = (TextView)findViewById(R.id.textView3);
        exit = (Button)findViewById(R.id.btn_exit) ;


        text.setText(app.user_name);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (app.getLoginStatus() != -1){
                    intent = new Intent(LoginSuccess.this,MainActivity.class);
                    startActivity(intent);
                }else {
                    finish();
                }

            }
        });
        //企业服务
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(LoginSuccess.this,EnterpriseService.class);
                startActivity(intent);
            }
        });

        //物业服务
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(LoginSuccess.this,PropertyActivity.class);
                startActivity(intent);
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                app.setLogin_status(-1);
                app.user_name = "";
                Intent intent = new Intent(LoginSuccess.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
