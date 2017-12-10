package com.example.pengxiang.appproject.loader;


import android.content.Context;
import android.media.Image;
import android.support.v4.widget.DrawerLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import com.facebook.drawee.view.SimpleDraweeView;
import com.youth.banner.loader.ImageLoader;

/**
 * Created by pengxiang on 07/26 0026.
 */

public class GlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView){
        Glide.with(context.getApplicationContext())
                .load(path)
                .into(imageView);
    }

}
