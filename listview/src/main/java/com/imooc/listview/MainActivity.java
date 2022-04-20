package com.imooc.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lv;

    private List<ListBean> dataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = findViewById(R.id.lv);

        for (int i = 0; i < 10000; i++) {
            ListBean bean = new ListBean();
            bean.setTitle("标题"+i);
            bean.setContent("内容"+i);
            dataList.add(bean);
        }
        ListAdapter adapter = new ListAdapter(this,dataList);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this,dataList.get(i).getTitle()+"--->"
                        +dataList.get(i).getContent(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}