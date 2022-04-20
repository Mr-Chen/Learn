package com.imooc.map;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;

import java.security.Permission;
import java.util.ArrayList;
import java.util.List;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

/**
 * Author   ： cxw
 * Date     ： 2022/4/19 03:41
 * Explain  :  请在此输入文件说明
 */
public class PermissionUtils {

    public static final int PERMISSION_REQUESTCODE = 100;


    protected static String[] permissions = {
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.READ_PHONE_STATE,
//            Manifest.permission.ACCESS_BACKGROUND_LOCATION

    };

    private static List<String> mList = new ArrayList<>();

    public static void checkPermissions(Activity context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            for (int x = 0; x < permissions.length; x++) {
                if (ContextCompat.checkSelfPermission(context, permissions[x]) != PackageManager.PERMISSION_GRANTED) {
                    mList.add(permissions[x]);
                }

            }

            if (mList.size() > 0) {
                requestPermissions(context, null);
            }

        }

    }

    public static void checkPermission(Activity context, String permission) {
        if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(context, permission);
        }
    }

    private static void requestPermissions(Activity context, String permission) {
        if (permission != null) {
            ActivityCompat.requestPermissions(context, new String[]{permission}, PERMISSION_REQUESTCODE);
        } else {
            ActivityCompat.requestPermissions(context, mList.toArray(new String[mList.size()]), PERMISSION_REQUESTCODE);
        }
    }

}
