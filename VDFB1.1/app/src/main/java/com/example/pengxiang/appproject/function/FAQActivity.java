package com.example.pengxiang.appproject.function;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.pengxiang.appproject.MainActivity;
import com.example.pengxiang.appproject.R;
import com.example.pengxiang.appproject.beans.Faq;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by pengxiang on 07/27 0027.
 */

public class FAQActivity extends Activity{
    ImageView back;
    ListView listView;
    List<Faq> faqList = null;
    ArrayList<HashMap<String,Object>> arrayList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        this.getWindow().addFlags(
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        this.getWindow().addFlags(
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        setContentView(R.layout.faq_list);

        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarTintResource(R.drawable.bg_title_bar2);

        listView = (ListView)findViewById(R.id.list);
        back = (ImageView)findViewById(R.id.imageBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FAQActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        initFaq();
        showFaq();
    }

    private void showFaq(){
        arrayList = new ArrayList<>();

        for (Faq f : faqList){
            HashMap<String ,Object> map = new HashMap<>();
            map.put("que",f.getQuestion());
            map.put("ans",f.getAnswer());
            arrayList.add(map);
        }

        SimpleAdapter simpleAdapter = new SimpleAdapter(FAQActivity.this,
                arrayList,R.layout.faq_item,
                new String[]{"que","ans"},
                new int[]{R.id.question,R.id.answer});

        listView.setAdapter(simpleAdapter);
    }

    private void initFaq(){
        faqList = new ArrayList<>();

        Faq faq1 = new Faq(this.getString(R.string.que_1), this.getString(R.string.ans_1));
        Faq faq2 = new Faq(this.getString(R.string.que_2), this.getString(R.string.ans_2));
        Faq faq3 = new Faq(this.getString(R.string.que_3), this.getString(R.string.ans_3));
        Faq faq4 = new Faq(this.getString(R.string.que_4), this.getString(R.string.ans_4));
        Faq faq5 = new Faq(this.getString(R.string.que_5), this.getString(R.string.ans_5));
        Faq faq6 = new Faq(this.getString(R.string.que_6), this.getString(R.string.ans_6));
        Faq faq7 = new Faq(this.getString(R.string.que_7), this.getString(R.string.ans_7));
        Faq faq8 = new Faq(this.getString(R.string.que_8), this.getString(R.string.ans_8));

        faqList.add(faq1);
        faqList.add(faq2);
        faqList.add(faq3);
        faqList.add(faq4);
        faqList.add(faq5);
        faqList.add(faq6);
        faqList.add(faq7);
        faqList.add(faq8);

    }
}
