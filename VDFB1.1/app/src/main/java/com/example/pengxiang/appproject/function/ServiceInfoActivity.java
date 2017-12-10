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
import com.example.pengxiang.appproject.beans.ServiceInfo;
import com.example.pengxiang.appproject.tool.Connect;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by pengxiang on 08/05 0005.
 */

public class ServiceInfoActivity extends Activity{
    private Intent intent;
    ImageView back;
    private ListView listView=null;
    private ProgressDialog myDialog;
    List<ServiceInfo>serviceInfoList=null;
    ArrayList<HashMap<String,Object>>arrayList=null;

    private Handler handler=new Handler(){
        public void handleMessage(Message msg){
            if(serviceInfoList.size()==0){
                return;
            }else{
                myDialog.dismiss();
                showService();
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

        setContentView(R.layout.service_info);

        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarTintResource(R.drawable.bg_title_bar2);

        listView = (ListView)findViewById(R.id.list);
        back = (ImageView)findViewById(R.id.imageBack);

        myDialog=ProgressDialog.show(ServiceInfoActivity.this,"wait.....",
                "loading.......",true);

        new Thread(runnable).start();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                intent = new Intent(ServiceInfoActivity.this,ShowService.class);
                ServiceInfo serviceInfo = serviceInfoList.get(i);
                Bundle bundle = new Bundle();
                bundle.putSerializable("Service",serviceInfo);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    //显示所有服务信息
    public void showService(){
        arrayList=new ArrayList<>();

        for(ServiceInfo c:serviceInfoList){
            HashMap<String,Object>map=new HashMap<>();
            map.put("title",c.getTitle());
            map.put("content",c.getContent());
            map.put("time",c.getTime());
            arrayList.add(map);
        }
        SimpleAdapter simpleAdapter=new SimpleAdapter(ServiceInfoActivity.this,
                arrayList,R.layout.service_item,
                new String[] {"title"},
                new int[] {R.id.service_title});
        listView.setAdapter(simpleAdapter);
    }

    Runnable runnable=new Runnable() {
        @Override
        public void run() {
            Connect connect=new Connect();
            serviceInfoList=connect.getServicePopList();
            Message message=new Message();
            handler.sendMessage(message);
        }
    };

}
