package com.imooc.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rv;

    private List<DataBean> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv = findViewById(R.id.rv);

        for (int x = 9990; x < 20000; x++) {
            DataBean data = new DataBean();
            data.setTitle("标题" + x);
            data.setContent("内容" + x);
            list.add(data);
        }

//        LinearLayoutManager manager = new LinearLayoutManager(this);

//        GridLayoutManager manager = new GridLayoutManager(this,3);
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(3, LinearLayout.VERTICAL);

        rv.setLayoutManager(manager);

        RVAdapter adapter = new RVAdapter(this, list);
        rv.setAdapter(adapter);

        adapter.setOnItemClickListener(new RVAdapter.OnItemClickListener() {
            @Override
            public void itemClickListener(int position) {
                Toast.makeText(MainActivity.this, list.get(position).getTitle() + "--->"
                        + list.get(position).getContent(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}