package com.example.pengxiang.appproject.function;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.pengxiang.appproject.R;
import com.example.pengxiang.appproject.beans.InformBean;
import com.example.pengxiang.appproject.tool.Connect;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by pengxiang on 08/06 0006.
 */

public class toInformActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private ListView listview;
    private SimpleAdapter simp_adapter;
    private List<Map<String,Object>> dataList;
    List<InformBean> informbeanlist = null;
    ImageView back;
    Intent intent;

    private Handler handler = new Handler() {
        public void handleMessage(Message msg){
            if (informbeanlist.size() == 0){
                return ;
            }else {
                showList();
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

        setContentView(R.layout.inform);

        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarTintResource(R.drawable.bg_title_bar2);

        new Thread(runnable).start();
        //跳转
        back = (ImageView)findViewById(R.id.imageBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        listview=(ListView)findViewById(R.id.listview);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> AdapterView, View view, int i, long l) {
                InformBean informBean = informbeanlist.get(i);
                Log.v("传递内容：", informBean.toString());
                Bundle bundle = new Bundle();
                bundle.putSerializable("Inform", informBean);
                Intent intent = new Intent(toInformActivity.this, toInformPage.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    //显示通知列表
    public void showList(){
        listview=(ListView)findViewById(R.id.listview);

        dataList=new ArrayList<Map<String,Object>>();
        //SimpleAdapter(上下文，数据源，布局文件ID，map中的键名，绑定数据视图中的Id，与from形成对应关系)
        simp_adapter=new SimpleAdapter(toInformActivity.this,getDataList(),R.layout.informitem,new String[]{"title"},new int[]{R.id.textView1});
        listview.setAdapter(simp_adapter);
    }
    private List<Map<String,Object>>getDataList(){
        for(InformBean n : informbeanlist){
            Map<String,Object>map =new HashMap<String,Object>();
            map.put("title",n.getTitle());
            map.put("date",n.getDate());
            map.put("id",n.getId());
            map.put("content",n.getContent());
            dataList.add(map);
        }
        return dataList;
    }

    //定义子线程
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            Connect connect = new Connect();
            informbeanlist = connect.getInformPopList();
            Message message = new Message();
            handler.sendMessage(message);
        }
    };
}