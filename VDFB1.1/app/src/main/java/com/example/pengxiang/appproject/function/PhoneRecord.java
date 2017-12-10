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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.pengxiang.appproject.R;
import com.example.pengxiang.appproject.beans.ConnectionInfo;
import com.example.pengxiang.appproject.tool.Connect;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by pengxiang on 08/05 0005.
 */

public class PhoneRecord extends Activity{
    ImageView back;
    private Button btn1;
    private Intent intent;
    private ListView listView=null;
    private ProgressDialog myDialog;
    List<ConnectionInfo> connectionInfoList=null;
    ArrayList<HashMap<String,Object>> arrayList=null;

    private Handler handler = new Handler(){
        public void handleMessage(Message msg){
            if (connectionInfoList.size() == 0){
                return;
            }else{
                myDialog.dismiss();
                showConnection();
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

        setContentView(R.layout.phone_record);

        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarTintResource(R.drawable.bg_title_bar2);

        listView = (ListView) findViewById(R.id.listView);
        btn1 = (Button) findViewById(R.id.btn_creat);
        back = (ImageView)findViewById(R.id.imageBack);

        myDialog = ProgressDialog.show(PhoneRecord.this,
                "wait......", "loading........", true);

        new Thread(runnable).start();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                intent = new Intent(PhoneRecord.this, ShowConnection.class);
                ConnectionInfo connectionInfo = connectionInfoList.get(i);
                Bundle bundle = new Bundle();
                bundle.putSerializable("Connection", connectionInfo);
                intent.putExtras(bundle);
                startActivity(intent);
            }


        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PhoneRecord.this, NewPhone.class);
                startActivity(intent);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PhoneRecord.this,EnterpriseService.class);
                startActivity(intent);
            }
        });

    }

    //显示所有联系人
    public void showConnection(){
        arrayList=new ArrayList<>();

        for(ConnectionInfo c:connectionInfoList){
            HashMap<String,Object>map=new HashMap<>();
            map.put("name",c.getName());
            map.put("mail",c.getMail());
            map.put("phone",c.getPhone());
            arrayList.add(map);
        }

        SimpleAdapter simpleAdapter=new SimpleAdapter(PhoneRecord.this,
                arrayList,R.layout.lianxiren,new String[] {"name"},new int[]{
                R.id.textView6});
        listView.setAdapter(simpleAdapter);
    }

    Runnable runnable=new Runnable(){
        @Override
        public void run(){
            Connect connect=new Connect();
            connectionInfoList=connect.getConnectionPopList();
            Message message=new Message();
            handler.sendMessage(message);
        }
    };
}
