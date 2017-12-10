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

import com.example.pengxiang.appproject.MainActivity;
import com.example.pengxiang.appproject.R;
import com.example.pengxiang.appproject.tool.Connect;
import com.readystatesoftware.systembartint.SystemBarTintManager;

/**
 * Created by pengxiang on 07/27 0027.
 */

public class ComplainActivity extends Activity{
    ImageView back;
    Button submit;
    String str_title = "";
    String str_content = "";
    boolean status = false;
    EditText title,content;

    private Handler handler = new Handler(){
        public void handleMessage(Message msg){
            if (status == false){
                Toast.makeText(ComplainActivity.this, "提交失败", Toast.LENGTH_SHORT).show();
                return;
            }
            else {
                Toast.makeText(ComplainActivity.this, "提交成功", Toast.LENGTH_SHORT).show();
                title.setText("");
                content.setText("");

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

        setContentView(R.layout.complain_layout);

        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarTintResource(R.drawable.bg_title_bar2);

        back = (ImageView)findViewById(R.id.imageBack);
        submit = (Button)findViewById(R.id.submit);
        title = (EditText)findViewById(R.id.new_title);
        content = (EditText)findViewById(R.id.new_content);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ComplainActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (title.getText().toString().isEmpty() || content.getText().toString().isEmpty()){
                    Toast.makeText(ComplainActivity.this,"不能为空",Toast.LENGTH_SHORT).show();
                }
                else {
                    str_title = title.getText().toString();
                    str_content = content.getText().toString();

//                    Connect connect = new Connect();
                    new Thread(runnable).start();

                }
            }
        });
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            Connect connect = new Connect();
            status = connect.sendComplain(str_title,str_content);
            Message message = new Message();
            handler.sendMessage(message);
        }
    };
}
