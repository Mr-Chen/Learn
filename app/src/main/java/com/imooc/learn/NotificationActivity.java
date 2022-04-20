package com.imooc.learn;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

/**
 * Author   ： cxw
 * Date     ： 2022/4/16 23:07
 * Explain  :  请在此输入文件说明
 */
public class NotificationActivity extends Activity {

  private static final String TAG = "LOGE";

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    Log.e(TAG, "NotificationActivity--->onCreate() ");
  }
}
