package com.example.pengxiang.appproject.function;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;


import com.example.pengxiang.appproject.R;
import com.readystatesoftware.systembartint.SystemBarTintManager;

/**
 * Created by pengxiang on 08/06 0006.
 */

public class PropertyActivity extends AppCompatActivity {
    public ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.getWindow().addFlags(
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        this.getWindow().addFlags(
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        setContentView(R.layout.propertyservice);

        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarTintResource(R.drawable.bg_title_bar2);

        back = (ImageView)findViewById(R.id.imageBack) ;
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                Intent intent = new Intent(PropertyActivity.this, toInformActivity.class);//通知
                startActivity(intent);
                break;
            case R.id.button2:
                Intent intent2 = new Intent(PropertyActivity.this, toContractmanagement.class);//合同
                startActivity(intent2);
                break;
            case R.id.button3:
                Intent intent3 = new Intent(PropertyActivity.this, toWaterandelectricity.class);//水电
                startActivity(intent3);
                break;
            case R.id.button4:
                Intent intent4 = new Intent(PropertyActivity.this, toPayment.class);//缴费
                startActivity(intent4);
                break;
            case R.id.button5:
                Intent intent5 = new Intent(PropertyActivity.this, toMaintenanceservice.class);//报修
                startActivity(intent5);
                break;
        }
    }
}
