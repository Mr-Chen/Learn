package com.imooc.git;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        git();
    }

    /**
     * master分支
     */
    private void git() {
        String master = "master";
    }

    public void masterConflict() {
        String conflict = "主分支";
    }
}