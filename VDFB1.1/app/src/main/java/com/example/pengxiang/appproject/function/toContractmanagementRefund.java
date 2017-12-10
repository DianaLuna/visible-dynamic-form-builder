package com.example.pengxiang.appproject.function;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.pengxiang.appproject.R;
import com.example.pengxiang.appproject.beans.ContractBean;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by pengxiang on 08/06 0006.
 */

public class toContractmanagementRefund extends AppCompatActivity {

    private ImageView back;
    private ListView listview;
    private SimpleAdapter simp_adapter;
    private List<Map<String,Object>> dataList;
    List<ContractBean> contractbeanlist = null;
    private int ContractId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.getWindow().addFlags(
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        this.getWindow().addFlags(
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);


        setContentView(R.layout.contractmanagement_refund);

        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarTintResource(R.drawable.bg_title_bar2);

        listview=(ListView)findViewById(R.id.listview);
        back = (ImageView)findViewById(R.id.imageBack);
//        String[]arr_date={"1","2","3","4"};
//        //ArrayAdapter(上下文，列表项对应的布局文件，数据源)
//        arr_adapter=new ArrayAdapter<String>(this,R.layout.contractitem,arr_date);
//        listview.setAdapter(arr_adapter);
        dataList=new ArrayList<Map<String,Object>>();
        simp_adapter = new SimpleAdapter(toContractmanagementRefund.this, getDataList(), R.layout.contractitem, new String[]{"item","text"}, new int[]{R.id.textView1, R.id.textView2});
        listview.setAdapter(simp_adapter);

        TextView textView2=(TextView)findViewById(R.id.textView2);
        textView2.setText(Integer.toString(ContractId));

        Button btn=(Button)findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                AlertDialog.Builder builder = new AlertDialog.Builder(toContractmanagementRefund.this);
                builder.setTitle("退租");
                builder.setMessage("退租成功！");
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
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    String[]arr_item={"  合同名称：","  合同内容：","  生效时间：","  终止时间："};
    private List<Map<String, Object>> getDataList() {

        Intent intent = getIntent();
        ContractBean contractbean = (ContractBean)intent.getExtras().getSerializable("Contract");

        ContractId=contractbean.getId();

        Map<String, Object> map1 = new HashMap<String, Object>();
        map1.put("item",arr_item[0]);
        map1.put("text",contractbean.getName());
        dataList.add(map1);
        Map<String, Object> map2 = new HashMap<String, Object>();
        map2.put("item",arr_item[1]);
        map2.put("text",contractbean.getContent());
        dataList.add(map2);
        Map<String, Object> map3 = new HashMap<String, Object>();
        map3.put("item",arr_item[2]);
        map3.put("text",contractbean.getsDate());
        dataList.add(map3);
        Map<String, Object> map4 = new HashMap<String, Object>();
        map4.put("item",arr_item[3]);
        map4.put("text",contractbean.geteDate());
        dataList.add(map4);

        return dataList;
    }

}