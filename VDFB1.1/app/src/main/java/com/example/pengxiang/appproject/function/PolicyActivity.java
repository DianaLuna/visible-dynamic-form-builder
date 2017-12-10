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
import com.example.pengxiang.appproject.beans.Policy;
import com.example.pengxiang.appproject.tool.Connect;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by pengxiang on 07/27 0027.
 */

public class PolicyActivity extends Activity{
    ImageView back;
    Intent intent;
    ListView listView = null;
    private ProgressDialog myDialog;
    List<Policy> policyList = null;
    ArrayList<HashMap<String,Object>> arrayList = null;

    private Handler handler = new Handler() {
        public void handleMessage(Message msg){
            if (policyList.size() == 0){
                return ;
            }else {
                myDialog.dismiss();
                showPolicy();
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

        //先政策和公告用同一个
        setContentView(R.layout.policy_info);

        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarTintResource(R.drawable.bg_title_bar2);

        listView = (ListView)findViewById(R.id.list);
        back = (ImageView)findViewById(R.id.imageBack);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PolicyActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        myDialog = ProgressDialog.show(PolicyActivity.this,
                "wait....","searching....",true);

        new Thread(runnable).start();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                intent = new Intent(PolicyActivity.this,ShowPolicy.class);
                Policy policy = policyList.get(i);
                Bundle bundle = new Bundle();
                bundle.putSerializable("Policy",policy);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    //显示所有的政策
    public void showPolicy(){
        arrayList = new ArrayList<>();

        for (Policy p : policyList){
            HashMap<String,Object> map = new HashMap<>();
            map.put("id",p.getId());
            map.put("title",p.getTitle());
            map.put("date",p.getDate());
            map.put("content",p.getContent());
            arrayList.add(map);
        }

        SimpleAdapter simpleAdapter = new SimpleAdapter(PolicyActivity.this,
                arrayList,R.layout.policy_item,
                new String[]{"title"},
                new int[]{R.id.policy_title});

        listView.setAdapter(simpleAdapter);
    }

    //
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            Connect connect = new Connect();
            policyList = connect.getPolicyPopList();
            Message message = new Message();
            handler.sendMessage(message);
        }
    };
}
