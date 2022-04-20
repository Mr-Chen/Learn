package com.imooc.map;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.amap.api.maps.MapsInitializer;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.MyLocationStyle;

import java.io.File;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private boolean isCheck;
    private static final String TAG = "LOGE";
    private boolean isAllow = true;//是否同意所有权限

    private AlertDialog alertDialog;
    private Button btnAgree;
    private Button btnDissAgree;
    private AMap aMap;
    private MapView mMapView;


    //是否需要检测后台定位权限，设置为true时，如果用户没有给予后台定位权限会弹窗提示
    private boolean needCheckBackLocation = false;
    //如果设置了target > 28，需要增加这个权限，否则不会弹出"始终允许"这个选择框


    protected String[] needPermissions = {
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.READ_PHONE_STATE,
//            Manifest.permission.ACCESS_BACKGROUND_LOCATION

    };
    private static final int PERMISSON_REQUESTCODE = 0;

    /**
     * 判断是否需要检测，防止不停的弹框
     */
    private boolean isNeedCheck = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //权限检测
        PermissionUtils.checkPermissions(MainActivity.this);

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        //调用任何接口前 调用更新隐私合规接口
//        privacyCompliance();

        mMapView = findViewById(R.id.map);
        mMapView.onCreate(savedInstanceState);
        if (aMap == null) {//显示地图
            aMap = mMapView.getMap();
        }

        showLocationBlue();


    }

    /**
     * 3D地图 SDK 5.0.0版本之后定位蓝点实现无需依赖 Android 定位 SDK
     * 显示定位蓝点
     */
    private void showLocationBlue() {
        MyLocationStyle myLocationStyle;
        myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
        myLocationStyle.interval(2000); //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
//aMap.getUiSettings().setMyLocationButtonEnabled(true);设置默认定位按钮是否显示，非必需设置。
        aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。

        BitmapDescriptor descriptor = BitmapDescriptorFactory.fromResource(R.drawable.bike);
        myLocationStyle.myLocationIcon(descriptor);
        myLocationStyle.anchor(0.0f, 1.0f);


    }

    public void search(View view) {
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }


    private void privacyCompliance() {

        //更新隐私合规状态,需要在初始化地图之前完成
        MapsInitializer.updatePrivacyShow(this, true, true);
        MapsInitializer.updatePrivacyAgree(MainActivity.this, true);


        View dialogView = LayoutInflater.from(this).inflate(R.layout.layout_privacy, null);

        btnAgree = dialogView.findViewById(R.id.agree);
        btnDissAgree = dialogView.findViewById(R.id.disagree);

        btnAgree.setOnClickListener(this);
        btnDissAgree.setOnClickListener(this);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        alertDialog =
                builder.setView(dialogView)
                        .create();

        alertDialog.show();

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.agree:
                Toast.makeText(MainActivity.this, "isfasdf", Toast.LENGTH_SHORT).show();

                Log.e(TAG, "onClick: -------------");

                alertDialog.dismiss();
                MapsInitializer.updatePrivacyAgree(MainActivity.this, true);

                showLocationBlue();
                break;
            case R.id.disagree:
                alertDialog.dismiss();
                MapsInitializer.updatePrivacyAgree(MainActivity.this, false);
                break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        List<String> denyPermissions = new ArrayList<>();
        if (requestCode == PERMISSON_REQUESTCODE) {
            for (int x = 0; x < grantResults.length; x++) {
                if (grantResults[x] == PackageManager.PERMISSION_DENIED) {
                    denyPermissions.add(permissions[x]);
                    isAllow = false;
                }
            }
            requestPermission(denyPermissions);


        }

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void requestPermission(List<String> permissions) {
        for (String permission : permissions) {
            if (shouldShowRequestPermissionRationale(permission)) {
                PermissionUtils.checkPermission(MainActivity.this, permission);
            } else {
                goAppSetting();
            }
        }

    }

    private void goAppSetting() {
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);
        startActivity(intent);
    }

    @Override
    protected void onResume() {

        super.onResume();
//        mMapView.onResume();


        Log.e(TAG, "" + sHA1(this));
        ;
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图

    }

    public static String sHA1(Context context) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), PackageManager.GET_SIGNATURES);
            byte[] cert = info.signatures[0].toByteArray();
            MessageDigest md = MessageDigest.getInstance("SHA1");
            byte[] publicKey = md.digest(cert);
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < publicKey.length; i++) {
                String appendString = Integer.toHexString(0xFF & publicKey[i])
                        .toUpperCase(Locale.US);
                if (appendString.length() == 1)
                    hexString.append("0");
                hexString.append(appendString);
                hexString.append(":");
            }
            String result = hexString.toString();
            return result.substring(0, result.length() - 1);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }


    /*************************************** 权限检查******************************************************/


}