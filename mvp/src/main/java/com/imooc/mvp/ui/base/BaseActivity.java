package com.imooc.mvp.ui.base;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.IdRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Author   ： cxw
 * Date     ： 2022/4/22 22:28
 * Explain  :  请在此输入文件说明
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getLayoutId());
        initViews();
    }

    protected abstract void initViews();

    protected abstract int getLayoutId();

    protected <T extends View> T find(@IdRes int id) {
        return findViewById(id);
    }
}
