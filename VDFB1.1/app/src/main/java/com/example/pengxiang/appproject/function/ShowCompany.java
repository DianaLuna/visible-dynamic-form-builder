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
import com.example.pengxiang.appproject.beans.NoticeBean;
import com.readystatesoftware.systembartint.SystemBarTintManager;

/**
 * Created by pengxiang on 07/31 0031.
 */

public class ShowCompany extends Activity{
    ImageView back;
    TextView name,content;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        this.getWindow().addFlags(
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        this.getWindow().addFlags(
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        setContentView(R.layout.show_company);

        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarTintResource(R.drawable.bg_title_bar2);

        Intent intent = getIntent();
        CompanyInfo companyInfo = (CompanyInfo)intent.getExtras().getSerializable("Company");

        name = (TextView)this.findViewById(R.id.com_name);
        content = (TextView)this.findViewById(R.id.com_content);

        name.setText(companyInfo.getName());
        content.setText(companyInfo.getIntrodoce());

        back = (ImageView)findViewById(R.id.imageBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
