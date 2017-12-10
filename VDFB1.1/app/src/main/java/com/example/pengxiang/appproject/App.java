package com.example.pengxiang.appproject;

import android.app.Application;
import android.content.Context;
import android.util.DisplayMetrics;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.zxy.recovery.core.Recovery;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by pengxiang on 07/26 0026.
 */

public class App extends Application{
    public static List<?> images = new ArrayList<>();
    public static List<String> titles = new ArrayList<>();
    public static int H,W;
    public static App app;

    //登陆状态，-1未登录，0普通用户，1管理员；
    public static int login_status = -1;
    public static String user_name = "";

    @Override
    public void onCreate(){
        super.onCreate();
        app = this;
        login_status = -1;
        getScreen(this);
        Fresco.initialize(this);
        Recovery.getInstance()
                .debug(true).recoverInBackground(false).recoverStack(true)
                .mainPage(MainActivity.class).init(this);
        String[] urls = getResources().getStringArray(R.array.url);

        List list = Arrays.asList(urls);
        images = new ArrayList(list);
    }

    public void getScreen(Context aty){
        DisplayMetrics dm = aty.getResources().getDisplayMetrics();
        H = dm.heightPixels;
        W = dm.widthPixels;
    }

    public int getLoginStatus(){
        return login_status;
    }
    public void setLogin_status(int flag){
        login_status = flag;
    }
}
