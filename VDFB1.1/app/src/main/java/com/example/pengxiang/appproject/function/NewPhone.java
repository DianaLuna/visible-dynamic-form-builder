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

public class NewPhone extends Activity{
    ImageView back;
    Intent intent;
    private Button submit;
    String tv_name="";
    String tv_mail="";
    String tv_phone="";
    boolean status=false;
    EditText name,mail,phone;

    private Handler handler=new Handler(){
        public void handleMessage(Message msg){
            if(status == false){
                Toast.makeText(NewPhone.this, "提交失败", Toast.LENGTH_SHORT).show();
                name.setText("");
                mail.setText("");
                phone.setText("");
            }
            else {
                Toast.makeText(NewPhone.this,"提交成功",Toast.LENGTH_SHORT).show();
                intent = new Intent(NewPhone.this,PhoneRecord.class);
                startActivity(intent);
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

        setContentView(R.layout.record_phone);

        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarTintResource(R.drawable.bg_title_bar2);

        submit = (Button)findViewById(R.id.button);
        name = (EditText)findViewById(R.id.editText);
        mail = (EditText)findViewById(R.id.editText1);
        phone = (EditText)findViewById(R.id.editText2);
        back = (ImageView)findViewById(R.id.imageBack);

        submit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if (name.getText().toString().isEmpty() || mail.getText().toString().isEmpty()||phone.getText().toString().isEmpty()){
                    Toast.makeText(NewPhone.this,"不能为空",Toast.LENGTH_SHORT).show();
                }
                else {
                    tv_name = name.getText().toString();
                    tv_mail = mail.getText().toString();
                    tv_phone=phone.getText().toString();

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
            Connect connect = new Connect();
            status = connect.sendContacts(tv_name,tv_mail,tv_phone);
            Message message = new Message();
            handler.sendMessage(message);
        }
    };
}
