package com.imooc.git;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        git();
        gitDev();
    }

    /**
     * master分支
     */
    private void git() {
        String master = "master";
    }

    /*
    dev 分支
     */
    private void gitDev() {

        String dev = "dev";
    }

    public void masterConflict() {
        String conflict = "主分支";
    }


    public void master() {
        String master = "master分支";
    }

    public void dev() {
        String dev = "开发分支";
    }

    public void devNewData() {
        String dev = "开发分支新数据";
    }
}