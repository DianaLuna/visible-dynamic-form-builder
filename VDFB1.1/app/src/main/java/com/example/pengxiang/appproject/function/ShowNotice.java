package com.example.pengxiang.appproject.function;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pengxiang.appproject.MainActivity;
import com.example.pengxiang.appproject.R;
import com.example.pengxiang.appproject.beans.NoticeBean;
import com.readystatesoftware.systembartint.SystemBarTintManager;

/**
 * Created by pengxiang on 07/28 0028.
 */

public class ShowNotice extends Activity{
    ImageView back;
    TextView title,type,date,content;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        this.getWindow().addFlags(
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        this.getWindow().addFlags(
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        setContentView(R.layout.notice);

        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarTintResource(R.drawable.bg_title_bar2);

        Intent intent = getIntent();
        NoticeBean noticeBean = (NoticeBean)intent.getExtras().getSerializable("Notice");

        title = (TextView)this.findViewById(R.id.ann_title);
        type = (TextView)this.findViewById(R.id.ann_type);
        date = (TextView)this.findViewById(R.id.ann_date);
        content = (TextView)this.findViewById(R.id.ann_content);

        title.setText(noticeBean.getTitle());
        if (noticeBean.getType().equals("1")){
            type.setText("通知");
        }
        else {
            type.setText("公告");
        }
        date.setText(noticeBean.getDate());
        content.setText(noticeBean.getContent());

        back = (ImageView)findViewById(R.id.imageBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
