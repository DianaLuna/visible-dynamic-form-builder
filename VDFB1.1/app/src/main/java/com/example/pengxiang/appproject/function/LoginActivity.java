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
import android.widget.TextView;
import android.widget.Toast;

import com.example.pengxiang.appproject.App;
import com.example.pengxiang.appproject.MainActivity;
import com.example.pengxiang.appproject.R;
import com.example.pengxiang.appproject.tool.Connect;
import com.readystatesoftware.systembartint.SystemBarTintManager;


/**
 * Created by pengxiang on 08/04 0004.
 */

public class LoginActivity extends Activity{
    ImageView back;
    TextView register;
    EditText et_zh,et_mima;
    Button btn;
    String username,password;
    Intent intent;
    int flag;
    App app;

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            if(flag == 0) {
                Toast.makeText(getApplicationContext(), "企业账号", Toast.LENGTH_SHORT).show();

                intent=new Intent(LoginActivity.this,LoginSuccess.class);
                app = new App();
                app.setLogin_status(flag);
                app.user_name = username;
                startActivity(intent);
            }
            else if(flag == 1){
                Toast.makeText(getApplicationContext(), "物业账号", Toast.LENGTH_SHORT).show();

                intent = new Intent(LoginActivity.this,LoginSuccess.class);
                app = new App();
                app.setLogin_status(flag);
                startActivity(intent);
            }
            else{
                Toast.makeText(getApplicationContext(), "账号错误！", Toast.LENGTH_SHORT).show();
                et_mima.setText("");
                et_zh.setText("");
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        this.getWindow().addFlags(
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        this.getWindow().addFlags(
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        setContentView(R.layout.login);

        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarTintResource(R.drawable.bg_title_bar2);

        register = (TextView)findViewById(R.id.register);
        et_zh = (EditText)findViewById(R.id.loginAccount_id);
        et_mima = (EditText)findViewById(R.id.password_id);
        btn = (Button)findViewById(R.id.login);
        back = (ImageView)findViewById(R.id.imageBack);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,registerActivity.class);
                startActivity(intent);
            }
        });

        btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View arg0) {
                if (et_zh.getText().toString().isEmpty() || et_mima.getText().toString().isEmpty()){
                    Toast.makeText(LoginActivity.this,"不能为空",Toast.LENGTH_SHORT).show();
                }
                else {
                    //获取输入的用户名
                    username = et_zh.getText().toString();
                    //获取输入的密码
                    password = et_mima.getText().toString();
                    new Thread(runnable).start();
                }
            }
        });


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
            flag = cw.Logincheck(username, password);
            Message message = new Message();

            handler.sendMessage(message);
        }

    };

}
