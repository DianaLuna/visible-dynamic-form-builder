package com.example.pengxiang.appproject.function;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pengxiang.appproject.R;
import com.example.pengxiang.appproject.beans.ConnectionInfo;
import com.readystatesoftware.systembartint.SystemBarTintManager;

/**
 * Created by pengxiang on 08/05 0005.
 */

public class ShowConnection extends Activity{
    TextView name,mail,phone;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.getWindow().addFlags(
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        this.getWindow().addFlags(
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        setContentView(R.layout.show_connection);

        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarTintResource(R.drawable.bg_title_bar2);

        Intent intent = getIntent();
        ConnectionInfo connectionInfo = (ConnectionInfo)intent.getExtras().getSerializable("Connection");

        name = (TextView)this.findViewById(R.id.text_name);
        mail = (TextView)this.findViewById(R.id.text_mail);
        phone = (TextView)this.findViewById(R.id.text_phone);
        back = (ImageView)this.findViewById(R.id.imageBack);

        name.setText(connectionInfo.getName());
        mail.setText(connectionInfo.getMail());
        phone.setText(connectionInfo.getPhone());

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
