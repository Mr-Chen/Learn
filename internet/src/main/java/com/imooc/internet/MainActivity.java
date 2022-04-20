package com.imooc.internet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.imooc.internet.retrofit.Retrofitctivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void glide(View view) {
        Intent intent = new Intent(this, GlideActivity.class);
        startActivity(intent);

    }

    public void okhttp(View view) {
        Intent intent = new Intent(this, OkHttpActivity.class);
        startActivity(intent);

    }

    public void retrofit(View view) {
        Intent intent = new Intent(this, Retrofitctivity.class);
        startActivity(intent);

    }
}