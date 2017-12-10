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

import com.example.pengxiang.appproject.R;
import com.example.pengxiang.appproject.tool.Connect;
import com.readystatesoftware.systembartint.SystemBarTintManager;

/**
 * Created by pengxiang on 08/05 0005.
 */

public class Service extends Activity{
    private Button submit;
    private Button submit2;
    private String str_title="";
    private String str_time="";
    private String str_content="";
    private boolean status=false;
    Intent intent;
    ImageView back;
    EditText title,content,time;

    private Handler handler = new Handler(){
        public void handleMessage(Message msg){
            if (status == false){
                Toast.makeText(Service.this, "提交失败", Toast.LENGTH_SHORT).show();

            }
            else {
                Toast.makeText(Service.this, "提交成功", Toast.LENGTH_SHORT).show();
                title.setText("");
                content.setText("");
                time.setText("");

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

        setContentView(R.layout.service_record);

        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarTintResource(R.drawable.bg_title_bar2);

        submit = (Button)findViewById(R.id.button);
        submit2 = (Button)findViewById(R.id.button2);
        title = (EditText)findViewById(R.id.editText);
        content = (EditText)findViewById(R.id.editText1);
        time = (EditText)findViewById(R.id.editText2);
        back = (ImageView)findViewById(R.id.imageBack);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        submit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(Service.this,ServiceInfoActivity.class);
                startActivity(intent);
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (title.getText().toString().isEmpty() || content.getText().toString().isEmpty()||time.getText().toString().isEmpty()){
                    Toast.makeText(Service.this,"不能为空",Toast.LENGTH_SHORT).show();
                }
                else {
                    str_title = title.getText().toString();
                    str_content = content.getText().toString();
                    str_time=time.getText().toString();

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
            status = connect.sendService(str_title,str_content,str_time);
            Message message = new Message();
            handler.sendMessage(message);
        }
    };
}
