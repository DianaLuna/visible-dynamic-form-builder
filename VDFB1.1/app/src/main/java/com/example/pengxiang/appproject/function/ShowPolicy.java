package com.example.pengxiang.appproject.function;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pengxiang.appproject.R;
import com.example.pengxiang.appproject.beans.CompanyInfo;
import com.example.pengxiang.appproject.beans.Policy;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.util.ArrayList;

/**
 * Created by pengxiang on 07/31 0031.
 */

public class ShowPolicy extends Activity{
    ImageView back;
    TextView title,date,content;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        this.getWindow().addFlags(
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        this.getWindow().addFlags(
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        setContentView(R.layout.show_policy);

        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarTintResource(R.drawable.bg_title_bar2);

        Intent intent = getIntent();
        Policy policy = (Policy)intent.getExtras().getSerializable("Policy");

        title = (TextView)this.findViewById(R.id.po_title);
        date = (TextView)this.findViewById(R.id.po_date);
        content = (TextView)this.findViewById(R.id.po_con);

        title.setText(policy.getTitle());
        date.setText(policy.getDate());
        content.setText(policy.getContent());

        back = (ImageView)findViewById(R.id.imageBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
