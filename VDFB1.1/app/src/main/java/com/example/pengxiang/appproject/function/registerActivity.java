package com.example.pengxiang.appproject.function;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.pengxiang.appproject.MainActivity;
import com.example.pengxiang.appproject.R;
import com.example.pengxiang.appproject.tool.Connect;
import com.readystatesoftware.systembartint.SystemBarTintManager;

/**
 * Created by pengxiang on 08/04 0004.
 */

public class registerActivity extends Activity{
    EditText zc_zh,zc_mima;
    ImageView back;
    Button btn1,btn2;
    String username,password;
    Intent intent;
    boolean flag;

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            if(flag == true) {
                Toast.makeText(getApplicationContext(), "注册成功!", Toast.LENGTH_SHORT).show();

                // Intent intent=new Intent(registerActivity.this,MainActivity.class);
                // startActivity(intent);
            } else{
                Toast.makeText(getApplicationContext(), "注册失败！", Toast.LENGTH_SHORT).show();

            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().addFlags(
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        this.getWindow().addFlags(
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        setContentView(R.layout.register);

        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarTintResource(R.drawable.bg_title_bar2);

        zc_zh=(EditText)findViewById(R.id.loginAccount_id);
        zc_mima=(EditText)findViewById(R.id.password_id);
        btn1=(Button)findViewById(R.id.login);
        btn2=(Button)findViewById(R.id.button);

        btn2.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View arg1){
                intent = new Intent(registerActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
        btn1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View arg0) {
                if (zc_mima.getText().toString().isEmpty() || zc_zh.getText().toString().isEmpty()){
                    Toast.makeText(registerActivity.this,"不能为空",Toast.LENGTH_SHORT).show();
                }
                else {
                    //获取输入的用户名
                    username = zc_zh.getText().toString();
                    //获取输入的密码
                    password = zc_mima.getText().toString();
                    new Thread(runnable).start();
                }
            }
        });
        back = (ImageView)findViewById(R.id.imageBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            Connect cw=new Connect();
            flag=cw.Register(username, password);
            Message message = new Message();
            handler.sendMessage(message);
        }
    };
}
