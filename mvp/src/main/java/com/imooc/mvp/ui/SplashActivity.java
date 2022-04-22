package com.imooc.mvp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.imooc.mvp.MainActivity;
import com.imooc.mvp.R;
import com.imooc.mvp.ui.base.BaseActivity;

public class SplashActivity extends BaseActivity {

    private Handler mHandler = new Handler();
    private Runnable startRunn;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initViews() {

        startRunn = new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                SplashActivity.this.finish();
            }
        };

        mHandler.postDelayed(startRunn, 3000);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacks(startRunn);
    }
}