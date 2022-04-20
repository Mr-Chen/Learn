package com.imooc.viewpager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager vp;
    private List<View> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vp = findViewById(R.id.vp);
        LayoutInflater inflater = LayoutInflater.from(this);
        View layout1 = inflater.inflate(R.layout.layout1, null);
        View layout2 = inflater.inflate(R.layout.layout2, null);
        View layout3 = inflater.inflate(R.layout.layout3, null);
        list.add(layout1);
        list.add(layout2);
        list.add(layout3);
        VPAdapter adapter = new VPAdapter(list);
        vp.setAdapter(adapter);
    }
}