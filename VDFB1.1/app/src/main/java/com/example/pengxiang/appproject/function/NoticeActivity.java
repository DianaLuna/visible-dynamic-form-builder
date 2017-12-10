package com.example.pengxiang.appproject.function;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.NotificationCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.pengxiang.appproject.MainActivity;
import com.example.pengxiang.appproject.R;
import com.example.pengxiang.appproject.beans.NoticeBean;
import com.example.pengxiang.appproject.tool.Connect;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by pengxiang on 07/26 0026.
 */

public class NoticeActivity extends Activity{
    ImageView back;
    Intent intent;
    ListView listView = null;
    private ProgressDialog myDialog;
    List<NoticeBean> noticeBeanList = null;
    ArrayList<HashMap<String,Object>> arrayList = null;

    private Handler handler = new Handler() {
        public void handleMessage(Message msg){
            if (noticeBeanList.size() == 0){
                return ;
            }else {
                myDialog.dismiss();
                showNotice();
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

        setContentView(R.layout.notice_layout);

        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarTintResource(R.drawable.bg_title_bar2);

        listView = (ListView)findViewById(R.id.list);
        back = (ImageView)findViewById(R.id.imageBack);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(NoticeActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        myDialog = ProgressDialog.show(NoticeActivity.this,
                "wait....","searching....",true);

        new Thread(runnable).start();


        //跳转
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                intent = new Intent(NoticeActivity.this,ShowNotice.class);
                NoticeBean noticeBean = noticeBeanList.get(i);
                Bundle bundle = new Bundle();
                bundle.putSerializable("Notice",noticeBean);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    //显示所有通知
    public void showNotice(){
        arrayList = new ArrayList<>();

        for (NoticeBean n : noticeBeanList){
            HashMap<String,Object> map = new HashMap<>();
            map.put("title",n.getTitle());
            if (n.getType().equals("1")){
                map.put("type","通知");
            }
            else {
                map.put("type","公告");
            }
            map.put("date",n.getDate());
            map.put("id",n.getId());
            map.put("content",n.getContent());
            arrayList.add(map);
        }

        SimpleAdapter simpleAdapter = new SimpleAdapter(NoticeActivity.this,
                arrayList,R.layout.notice_content,
                new String[]{"title","type","date"},
                new int[]{R.id.notice_title,R.id.notice_type,R.id.notice_time});

        listView.setAdapter(simpleAdapter);

    }

    //定义子线程
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            Connect connect = new Connect();
            noticeBeanList = connect.getNoticePopList();
            Message message = new Message();
            handler.sendMessage(message);
        }
    };


}
