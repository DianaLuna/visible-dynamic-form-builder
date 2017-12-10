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

import com.example.pengxiang.appproject.R;
import com.example.pengxiang.appproject.beans.ResourceInfo;
import com.example.pengxiang.appproject.tool.Connect;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by pengxiang on 08/05 0005.
 */

public class ResourceInfoActivity extends Activity{
    ImageView back;
    Intent intent;
    ListView listView = null;
    private ProgressDialog myDialog ;
    List<ResourceInfo> ResourceInfoList = null;
    ArrayList<HashMap<String,Object>> arrayList = null;

    private Handler handler = new Handler(){
        public void handleMessage(Message msg){
            if (ResourceInfoList.size() == 0){
                return;
            }else{
                myDialog.dismiss();
                showResource();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.getWindow().addFlags(
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        this.getWindow().addFlags(
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        setContentView(R.layout.resource_info);

        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarTintResource(R.drawable.bg_title_bar2);

        listView = (ListView)findViewById(R.id.list);

        myDialog = ProgressDialog.show(ResourceInfoActivity.this,
                "wait...","loading.....",true);

        new Thread(runnable).start();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                intent = new Intent(ResourceInfoActivity.this,ShowResource.class);
                ResourceInfo ResourceInfo = ResourceInfoList.get(i);
                Bundle bundle = new Bundle();
                bundle.putSerializable("Resource",ResourceInfo);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }

    //显示所有预定信息
    public void showResource(){
        arrayList = new ArrayList<>();

        for (ResourceInfo c : ResourceInfoList){
            HashMap<String,Object> map = new HashMap<>();
            map.put("id",c.getId());
            map.put("kind",c.getKind());
            map.put("use",c.getUse());
            map.put("time",c.getTime());
            arrayList.add(map);
        }

        SimpleAdapter simpleAdapter = new SimpleAdapter(ResourceInfoActivity.this,
                arrayList,R.layout.resource_item,
                new String[] {"id"},
                new int[] {R.id.r_id});

        listView.setAdapter(simpleAdapter);
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            Connect connect = new Connect();
            ResourceInfoList = connect.getResourcePopList();
            Message message = new Message();
            handler.sendMessage(message);
        }
    };
}
