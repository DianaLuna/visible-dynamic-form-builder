package com.example.pengxiang.appproject.function;

import android.app.Activity;
import android.app.Notification;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.pengxiang.appproject.MainActivity;
import com.example.pengxiang.appproject.R;
import com.example.pengxiang.appproject.beans.CompanyInfo;
import com.example.pengxiang.appproject.tool.Connect;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by pengxiang on 07/26 0026.
 */

public class CompanyInfoActivity extends Activity{
    ImageView back;
    Intent intent;
    ListView listView = null;
    private ProgressDialog myDialog ;
    List<CompanyInfo> companyInfoList = null;
    ArrayList<HashMap<String,Object>> arrayList = null;

    private Handler handler = new Handler(){
        public void handleMessage(Message msg){
            if (companyInfoList.size() == 0){
                return;
            }else{
                myDialog.dismiss();
                showRecruit();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        this.getWindow().addFlags(
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        this.getWindow().addFlags(
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        setContentView(R.layout.company_info);

        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarTintResource(R.drawable.bg_title_bar2);

        listView = (ListView)findViewById(R.id.list);
        back = (ImageView)findViewById(R.id.imageBack);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CompanyInfoActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        myDialog = ProgressDialog.show(CompanyInfoActivity.this,
                "wait...","searching.....",true);

        new Thread(runnable).start();


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                intent = new Intent(CompanyInfoActivity.this,ShowCompany.class);
                CompanyInfo companyInfo = companyInfoList.get(i);
                Bundle bundle = new Bundle();
                bundle.putSerializable("Company",companyInfo);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    //显示所有企业信息
    public void showRecruit(){
        arrayList = new ArrayList<>();

        for (CompanyInfo c : companyInfoList){
            HashMap<String,Object> map = new HashMap<>();
            map.put("id",c.getId());
            map.put("name",c.getName());
            map.put("introduce",c.getIntrodoce());
            arrayList.add(map);
        }

        SimpleAdapter simpleAdapter = new SimpleAdapter(CompanyInfoActivity.this,
                arrayList,R.layout.company_item,
                new String[] {"name"},
                new int[] {R.id.company_name});

        listView.setAdapter(simpleAdapter);
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            Connect connect = new Connect();
            companyInfoList = connect.getCompanyPopList();
            Message message = new Message();
            handler.sendMessage(message);
        }
    };
}
