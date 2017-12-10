package com.example.pengxiang.appproject;

import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.pengxiang.appproject.function.CompanyInfoActivity;
import com.example.pengxiang.appproject.function.ComplainActivity;
import com.example.pengxiang.appproject.function.EnterpriseService;
import com.example.pengxiang.appproject.function.FAQActivity;
import com.example.pengxiang.appproject.function.IntroduceActivity;
import com.example.pengxiang.appproject.function.LoginActivity;
import com.example.pengxiang.appproject.function.LoginSuccess;
import com.example.pengxiang.appproject.function.PolicyActivity;
import com.example.pengxiang.appproject.function.RecruitInfoActivity;
import com.example.pengxiang.appproject.loader.GlideImageLoader;
import com.example.pengxiang.appproject.function.NoticeActivity;
import com.githang.statusbar.StatusBarCompat;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import static com.example.pengxiang.appproject.App.app;
import static com.example.pengxiang.appproject.App.login_status;

public class MainActivity extends AppCompatActivity implements OnBannerListener{
    Banner banner;
    Intent intent;
    ImageView login;
    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.getWindow().addFlags(
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        this.getWindow().addFlags(
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//        this.getWindow().setFlags(
//                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
//                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        setContentView(R.layout.activity_main);

        // create our manager instance after the content view is set
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        // enable status bar tint
        tintManager.setStatusBarTintEnabled(true);
        // enable navigation bar tint
        //tintManager.setNavigationBarTintEnabled(true);

        tintManager.setStatusBarTintResource(R.drawable.bg_title_bar2);

        //获取功能操作对象
        login = (ImageView)findViewById(R.id.image_person);
        imageView1 = (ImageView)findViewById(R.id.fp_img_icon1);
        imageView2 = (ImageView)findViewById(R.id.fp_img_icon2);
        imageView3 = (ImageView)findViewById(R.id.fp_img_icon3);
        imageView4 = (ImageView)findViewById(R.id.fp_img_icon4);
        imageView5 = (ImageView)findViewById(R.id.fp_img_icon5);
        imageView6 = (ImageView)findViewById(R.id.fp_img_icon6);

        //分别添加点击事件监听
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(MainActivity.this,NoticeActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(MainActivity.this, CompanyInfoActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });
        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(MainActivity.this, RecruitInfoActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });
        imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(MainActivity.this, PolicyActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });
        imageView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(MainActivity.this, FAQActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });
        imageView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(MainActivity.this, ComplainActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (app.getLoginStatus()){
                    case -1:{
                        intent = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(intent);
                        break;
                    }
                    case 0:{
                        intent = new Intent(MainActivity.this, LoginSuccess.class);
                        startActivity(intent);
                        break;
                    }
                    case 1:{
                        intent = new Intent(MainActivity.this,LoginSuccess.class);
                        startActivity(intent);
                        break;
                    }
                }
            }
        });



        //实现轮播图功能
        initView();
    }

    //添加本地图片用作轮播图
    private void initView(){
        banner = (Banner)findViewById(R.id.banner);
        List<Integer> list = new ArrayList<>();
        list.add(R.drawable.image1);
        list.add(R.drawable.image2);
        list.add(R.drawable.image3);
        list.add(R.drawable.image4);

        banner.setImages(list)
                .setImageLoader(new GlideImageLoader())
                .setOnBannerListener(this)
                .start();
    }

    //监听点击轮播图事件
    @Override
    public void OnBannerClick(int position){
        intent = new Intent(MainActivity.this, IntroduceActivity.class);
        intent.putExtra("position",position);
        startActivity(intent);
    }


}

