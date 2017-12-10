package com.example.pengxiang.appproject.function;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pengxiang.appproject.R;
import com.readystatesoftware.systembartint.SystemBarTintManager;

/**
 * Created by pengxiang on 08/01 0001.
 */

public class IntroduceActivity extends Activity{
    ImageView back;
    ImageView picture;
    TextView introduction;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        this.getWindow().addFlags(
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        this.getWindow().addFlags(
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        setContentView(R.layout.introduction);

        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarTintResource(R.drawable.bg_title_bar2);

        Intent intent = getIntent();
        int position = intent.getIntExtra("position",0);

        back = (ImageView)findViewById(R.id.imageBack);
        picture = (ImageView)findViewById(R.id.intro_pic);
        introduction = (TextView)findViewById(R.id.intro_text);

        switch (position){
            case 0:{
                picture.setImageDrawable(getResources().getDrawable(R.drawable.image1));
                introduction.setText(this.getString(R.string.intro_1));
                break;
            }
            case 1:{
                picture.setImageDrawable(getResources().getDrawable(R.drawable.image2));
                introduction.setText(this.getString(R.string.intro_2));
                break;
            }
            case 2:{

                picture.setBackgroundResource(R.drawable.image3);
                //picture.setImageDrawable(getResources().getDrawable(R.drawable.image3));
                introduction.setText(this.getString(R.string.intro_3));
                break;
            }
            case 3:{
                picture.setImageDrawable(getResources().getDrawable(R.drawable.image4));
                introduction.setText(this.getString(R.string.intro_4));
                break;
            }
        }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
