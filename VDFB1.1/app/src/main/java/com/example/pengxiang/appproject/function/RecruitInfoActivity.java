package com.example.pengxiang.appproject.function;

import android.app.Activity;
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
import com.example.pengxiang.appproject.beans.RecruitInfo;
import com.example.pengxiang.appproject.tool.Connect;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by pengxiang on 07/26 0026.
 */

public class RecruitInfoActivity extends Activity{
    ImageView back;
    Intent intent;
    ListView listView = null;
    private ProgressDialog myDialog;
    List<RecruitInfo> recruitInfoList = null;
    ArrayList<HashMap<String,Object>> arrayList = null;

    private Handler handler = new Handler(){
        public void handleMessage(Message msg){
            if (recruitInfoList.size() == 0)
                return;
            else {
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

        setContentView(R.layout.recruit_info);

        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarTintResource(R.drawable.bg_title_bar2);

        listView = (ListView)findViewById(R.id.list);
        back = (ImageView)findViewById(R.id.imageBack);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RecruitInfoActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        myDialog = ProgressDialog.show(RecruitInfoActivity.this,
                "wait...","searching....",true);

        new Thread(runnable).start();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                intent = new Intent(RecruitInfoActivity.this,ShowRecruit.class);
                RecruitInfo recruitInfo = recruitInfoList.get(i);
                Bundle bundle = new Bundle();
                bundle.putSerializable("Recruit",recruitInfo);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    //显示所有招聘信息
    public void showRecruit(){
        arrayList = new ArrayList<>();

        for (RecruitInfo r : recruitInfoList){
            HashMap<String,Object> map = new HashMap<>();
            map.put("id",r.getId());
            map.put("com_name",r.getCom_name());
            map.put("require",r.getRequire());
            map.put("contact",r.getContact());
            map.put("money",r.getMoney());
            map.put("date",r.getDate());
            map.put("place",r.getPlace());
            map.put("job",r.getJob());
            arrayList.add(map);
        }

        SimpleAdapter simpleAdapter = new SimpleAdapter(RecruitInfoActivity.this,
                arrayList,R.layout.recruit_item,
                new String[]{"com_name","money","job","date"},
                new int[]{R.id.re_title,R.id.re_money,R.id.re_request,R.id.re_date});

        listView.setAdapter(simpleAdapter);
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            Connect connect = new Connect();
            recruitInfoList = connect.getRecruitPopList();
            Message message = new Message();
            handler.sendMessage(message);
        }
    };
}
