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

public class ResourceOrder extends Activity{
    ImageView back;
    private Button submit,submit1;
    private String str_id="";
    private String str_kind="";
    private String str_use="";
    private String str_time="";
    boolean status=false;
    EditText id,kind,use,time;

    private Handler handler = new Handler(){
        public void handleMessage(Message msg){
            if (status == false){
                Toast.makeText(ResourceOrder.this, "提交失败", Toast.LENGTH_SHORT).show();
                return;
            }
            else {
                Toast.makeText(ResourceOrder.this, "提交成功", Toast.LENGTH_SHORT).show();
                id.setText("");
                kind.setText("");
                use.setText("");
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

        setContentView(R.layout.ziyuan);

        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarTintResource(R.drawable.bg_title_bar2);

        submit = (Button)findViewById(R.id.button);

        id = (EditText)findViewById(R.id.editText);
        kind = (EditText)findViewById(R.id.editText1);
        use = (EditText)findViewById(R.id.editText2);
        time = (EditText)findViewById(R.id.editText3);
        back = (ImageView)findViewById(R.id.imageBack);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (id.getText().toString().isEmpty() || kind.getText().toString().isEmpty()||use.getText().toString().isEmpty()||time.getText().toString().isEmpty()){
                    Toast.makeText(ResourceOrder.this,"不能为空",Toast.LENGTH_SHORT).show();
                }
                else {
                    str_id = id.getText().toString();
                    str_kind =kind.getText().toString();
                    str_use=use.getText().toString();
                    str_time=time.getText().toString();


                    new Thread(runnable).start();

                }
            }
        });

    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            Connect connect = new Connect();
            status = connect.sendResource(str_id,str_kind,str_time);
            Message message = new Message();
            handler.sendMessage(message);
        }
    };
}
