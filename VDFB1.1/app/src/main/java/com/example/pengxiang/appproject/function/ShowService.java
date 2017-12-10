package com.example.pengxiang.appproject.function;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pengxiang.appproject.R;
import com.example.pengxiang.appproject.beans.ServiceInfo;
import com.readystatesoftware.systembartint.SystemBarTintManager;

/**
 * Created by pengxiang on 08/05 0005.
 */

public class ShowService extends Activity{
    TextView title,content,time;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.getWindow().addFlags(
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        this.getWindow().addFlags(
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        setContentView(R.layout.show_service);

        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarTintResource(R.drawable.bg_title_bar2);

        Intent intent = getIntent();

        ServiceInfo serviceInfo=(ServiceInfo)intent.getExtras().getSerializable("Service");

        title=(TextView)findViewById(R.id.name);
        content=(TextView)findViewById(R.id.content);
        time=(TextView)findViewById(R.id.time);
        back = (ImageView)findViewById(R.id.imageBack);

        title.setText(serviceInfo.getTitle());
        content.setText(serviceInfo.getContent());
        time.setText(serviceInfo.getTime());

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
