package com.example.pengxiang.appproject.function;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.example.pengxiang.appproject.R;
import com.readystatesoftware.systembartint.SystemBarTintManager;

/**
 * Created by pengxiang on 08/06 0006.
 */

public class toMaintenanceservice extends AppCompatActivity {

    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.getWindow().addFlags(
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        this.getWindow().addFlags(
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        setContentView(R.layout.maintenanceservice);

        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarTintResource(R.drawable.bg_title_bar2);

        back = (ImageView)findViewById(R.id.imageBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Button btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(toMaintenanceservice.this);
                builder.setTitle("报修");
                builder.setMessage("提交成功！");
                builder.setPositiveButton("返回",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface builder, int i) {
                        if (AlertDialog.BUTTON_POSITIVE == i) {
                            finish();
                        }
                    }
                });
                builder.show();
            }
        });
    }
}