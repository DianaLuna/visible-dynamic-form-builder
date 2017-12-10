package com.example.pengxiang.appproject.function;

import android.app.Activity;
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

public class Recruit extends Activity{
    ImageView back;
    private Button submit;
    private String str_position="";
    private String str_requir="";
    private String str_slary="";
    private String str_time="";
    private String str_companny="";
    private String str_place="";
    private String str_connection="";
    private boolean status=false;
    EditText position,requir,slary,time,company,place,connection;

    private Handler handler = new Handler(){
        public void handleMessage(Message msg){
            if (status == false){
                Toast.makeText(Recruit.this, "提交失败", Toast.LENGTH_SHORT).show();
                return;
            }
            else {
                Toast.makeText(Recruit.this, "提交成功", Toast.LENGTH_SHORT).show();
                position.setText("");
                requir.setText("");
                slary.setText("");
                time.setText("");
                company.setText("");
                place.setText("");
                connection.setText("");
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

        setContentView(R.layout.recruit);

        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarTintResource(R.drawable.bg_title_bar2);

        submit = (Button)findViewById(R.id.button);
        position = (EditText)findViewById(R.id.editText);
        requir = (EditText)findViewById(R.id.editText1);
        slary = (EditText)findViewById(R.id.editText2);
        time = (EditText)findViewById(R.id.editText3);
        company = (EditText)findViewById(R.id.editText4);
        place = (EditText)findViewById(R.id.editText5);
        connection = (EditText)findViewById(R.id.editText6);
        back = (ImageView)findViewById(R.id.imageBack);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (position.getText().toString().isEmpty() || requir.getText().toString().isEmpty()||slary.getText().toString().isEmpty()||time.getText().toString().isEmpty()
                        ||company.getText().toString().isEmpty()||place.getText().toString().isEmpty()||connection.getText().toString().isEmpty()){
                    Toast.makeText(Recruit.this,"不能为空",Toast.LENGTH_SHORT).show();
                }
                else {
                    str_position = position.getText().toString();
                    str_requir = requir.getText().toString();
                    str_slary=slary.getText().toString();
                    str_time=time.getText().toString();
                    str_companny=company.getText().toString();
                    str_place=place.getText().toString();
                    str_connection=connection.getText().toString();

//                    Connect connect = new Connect();
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
            status = connect.sendRecuit(str_companny, str_requir,str_position, str_slary,str_connection,str_time,str_place);
            Message message = new Message();
            handler.sendMessage(message);
        }
    };
}
