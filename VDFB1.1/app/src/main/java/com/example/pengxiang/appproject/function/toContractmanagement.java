package com.example.pengxiang.appproject.function;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.pengxiang.appproject.R;
import com.example.pengxiang.appproject.beans.ContractBean;
import com.example.pengxiang.appproject.tool.Connect;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by pengxiang on 08/06 0006.
 */

public class toContractmanagement extends AppCompatActivity {

    private Toolbar mToolbar;
    EditText editText1;
    ImageView back;
    private String str1 = "";
    List<ContractBean> contractbeanlist = null;
    private List<Map<String,Object>> dataList=new ArrayList<Map<String,Object>>();
    ContractBean contractBean =new ContractBean();

    private Handler handler = new Handler() {
        public void handleMessage(Message msg){
            if (contractbeanlist.size() == 0){
                return ;
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

        setContentView(R.layout.contractmanagement);


        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarTintResource(R.drawable.bg_title_bar2);

        editText1 = (EditText) findViewById(R.id.editText1);//获取合同单号
        back = (ImageView)findViewById(R.id.imageBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        new Thread(runnable).start();

    }

    public void onClick(View v) {

        str1 = String.valueOf(editText1.getText());//获取合同单号

        switch (v.getId()) {
            case R.id.button1:
                Log.v("点击按钮：","已经点击按钮");
                searchList1(str1);
                break;
            case R.id.button2:
                Log.v("点击按钮：","已经点击按钮");
                searchList1(str1);
                break;
            case R.id.button3:
                Log.v("点击按钮：","已经点击按钮");
                searchList2(str1);
                break;
            case R.id.button4:
                AlertDialog.Builder builder = new AlertDialog.Builder(toContractmanagement.this);
                builder.setTitle("合同管理");
                builder.setMessage("敬请期待！");
                builder.setPositiveButton("返回",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface builder, int i) {
                        if (AlertDialog.BUTTON_POSITIVE == i) {
                            finish();
                        }
                    }
                });
                builder.show();
                break;
            case R.id.button5:
                AlertDialog.Builder builder2 = new AlertDialog.Builder(toContractmanagement.this);
                builder2.setTitle("合同管理");
                builder2.setMessage("敬请期待！");
                builder2.setPositiveButton("返回",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface builder, int i) {
                        if (AlertDialog.BUTTON_POSITIVE == i) {
                            finish();
                        }
                    }
                });
                builder2.show();
                break;
        }
    }
    public void searchList1(String str1){
        getDataList();
        Log.v("contractbeanlist：", contractbeanlist.toString());
        Log.v("执行情况：","已经执行searchList1，但未完成");
        int i = -1;


        if (str1.equals("")){
            Toast.makeText(toContractmanagement.this,"请输入单号",Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(toContractmanagement.this, toContractmanagementGeneral.class);
        for (ContractBean n : contractbeanlist) {
            Log.v("执行情况：", "已经进入for循环，但未完成");


            Log.v("intent","yijingzhixing");
            str1 = str1.trim();
            i++;
            Log.v("str&getId",str1+n.getId());
            if ((n.getId()) == (Integer.parseInt(str1))) {
                Log.v("执行情况", "已经进入for循环内部循环，但未完成");
                contractBean = contractbeanlist.get(i);
                Bundle bundle = new Bundle();
                bundle.putSerializable("Contract", contractBean);
                intent.putExtras(bundle);
                startActivity(intent);
                Log.v("执行情况", "searchList1执行完毕");
                return;
            }
        }
        Toast.makeText(toContractmanagement.this,"合同单号不存在",Toast.LENGTH_SHORT).show();
    }

    public void searchList2(String str1){
        getDataList();
        Log.v("contractbeanlist：", contractbeanlist.toString());
        Log.v("执行情况：","已经执行searchList2，但未完成");
        int i=-1;


        str1 = String.valueOf(editText1.getText());//获取合同单号

        if (str1.equals("")){
            Toast.makeText(toContractmanagement.this,"请输入单号",Toast.LENGTH_SHORT).show();
            return;
        }

        for(ContractBean n:contractbeanlist){
            Log.v("执行情况：","已经进入for循环，但未完成");

            Intent intent = new Intent(toContractmanagement.this, toContractmanagementRefund.class);

            str1=str1.trim();
            i++;
            Log.v("合同单号",str1+"号码:"+i);
            if((n.getId())==(Integer.parseInt(str1))){
                Log.v("执行情况","已经进入for循环内部循环，但未完成");
                contractBean = contractbeanlist.get(i);
                Bundle bundle = new Bundle();
                bundle.putSerializable("Contract", contractBean);
                intent.putExtras(bundle);
                startActivity(intent);
                Log.v("执行情况","searchList2执行完毕");
                break;
            }
        }
    }
    private List<Map<String,Object>>getDataList(){
        for(ContractBean n : contractbeanlist){
            Map<String,Object>map =new HashMap<String,Object>();
            map.put("id",n.getId());
            map.put("name",n.getName());
            map.put("content",n.getContent());
            map.put("sdate",n.getsDate());
            map.put("edate",n.geteDate());
            dataList.add(map);
        }
        return dataList;
    }

    //定义子线程
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            Connect connect = new Connect();
            contractbeanlist = connect.getContractPopList();
            Log.v("执行情况","已执行子进程");
            Message message = new Message();
            handler.sendMessage(message);
        }
    };
}