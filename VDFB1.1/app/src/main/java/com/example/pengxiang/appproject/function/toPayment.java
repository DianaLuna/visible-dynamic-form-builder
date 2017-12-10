package com.example.pengxiang.appproject.function;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.pengxiang.appproject.R;
import com.readystatesoftware.systembartint.SystemBarTintManager;

/**
 * Created by pengxiang on 08/06 0006.
 */

public class toPayment extends AppCompatActivity {

    private ImageView back;
    private Button btn;
    private String str1="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.getWindow().addFlags(
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        this.getWindow().addFlags(
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        setContentView(R.layout.payment);

        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarTintResource(R.drawable.bg_title_bar2);

        back = (ImageView)findViewById(R.id.imageBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Button btn=(Button)findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                EditText editText1 =(EditText)findViewById(R.id.editText3);//获取缴费金额
                str1=editText1.getText().toString();//获取缴费金额

                AlertDialog.Builder builder = new AlertDialog.Builder(toPayment.this);
                builder.setTitle("缴费");
                builder.setMessage("成功缴费"+str1+"元！");
                builder.setPositiveButton("确认",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface builder, int i) {
                        if (AlertDialog.BUTTON_POSITIVE == i) {
                            finish();
                        }
                    }
                });
                builder.show();
            }
        });

    }
}