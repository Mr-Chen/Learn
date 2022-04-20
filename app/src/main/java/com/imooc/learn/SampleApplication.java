package com.imooc.learn;

import android.app.Application;

import com.umeng.commonsdk.UMConfigure;

/**
 * Author   ： cxw
 * Date     ： 2022/4/18 06:47
 * Explain  :  请在此输入文件说明
 */
public class SampleApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initUMeng();
    }

    private void initUMeng() {
        UMConfigure.preInit(this, "625c7df8d024421570bdba0d", null);
        UMConfigure.setLogEnabled(true);
        UMConfigure.init(this, null, null, UMConfigure.DEVICE_TYPE_PHONE, "");

    }


}
