package com.example.pengxiang.appproject.function;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pengxiang.appproject.R;
import com.example.pengxiang.appproject.beans.ResourceInfo;
import com.readystatesoftware.systembartint.SystemBarTintManager;

/**
 * Created by pengxiang on 08/05 0005.
 */

public class ShowResource extends Activity{
    ImageView back;
    private TextView id,kind,use,time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.getWindow().addFlags(
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        this.getWindow().addFlags(
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        setContentView(R.layout.resource_show);

        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarTintResource(R.drawable.bg_title_bar2);

        Intent intent = getIntent();
        ResourceInfo resourceInfo = (ResourceInfo)intent.getExtras().getSerializable("Resource");

        id = (TextView)findViewById(R.id.id);
        kind = (TextView)findViewById(R.id.kind);
        use = (TextView)findViewById(R.id.use);
        time = (TextView)findViewById(R.id.time);
        back = (ImageView)findViewById(R.id.imageBack);

        id.setText(resourceInfo.getId());
        kind.setText(resourceInfo.getKind());
        use.setText(resourceInfo.getUse());
        time.setText(resourceInfo.getTime());
    }
}
