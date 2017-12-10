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
import com.example.pengxiang.appproject.beans.RecruitInfo;
import com.readystatesoftware.systembartint.SystemBarTintManager;

/**
 * Created by pengxiang on 07/31 0031.
 */

public class ShowRecruit extends Activity{
    ImageView back;
    TextView name,money,require,job,date,place;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        this.getWindow().addFlags(
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        this.getWindow().addFlags(
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        setContentView(R.layout.show_recruit);

        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarTintResource(R.drawable.bg_title_bar2);

        Intent intent = getIntent();
        RecruitInfo recruitInfo = (RecruitInfo)intent.getExtras().getSerializable("Recruit");

        name = (TextView)this.findViewById(R.id.comRName);
        money = (TextView)this.findViewById(R.id.comRMon);
        job = (TextView)this.findViewById(R.id.comRJob);
        require = (TextView)this.findViewById(R.id.comRReq);
        date = (TextView)this.findViewById(R.id.comRDate);
        place = (TextView)this.findViewById(R.id.comRPlace);

        name.setText(recruitInfo.getCom_name());
        money.setText(recruitInfo.getMoney());
        job.setText(recruitInfo.getJob());
        require.setText(recruitInfo.getRequire());
        date.setText(recruitInfo.getDate());
        place.setText(recruitInfo.getPlace());

        back = (ImageView)findViewById(R.id.imageBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
