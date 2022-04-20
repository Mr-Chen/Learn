package com.imooc.media;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.List;

public class MainActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks {

    private String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.RECORD_AUDIO, Manifest.permission.CAMERA};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (EasyPermissions.hasPermissions(this,permissions)) {

        }else{//申请权限
            EasyPermissions.requestPermissions(this,"录像需要读取手机、录音和相机权限，否则无法使用",
                    122,permissions);
        }
    }


    public void record(View view) {
        Intent intent = new Intent(this, RecordVideoActivity.class);
        startActivity(intent);
    }

    public void player(View view) {
        Intent intent = new Intent(this, PlayerActivity.class);
        startActivity(intent);
    }
    public void sound(View view) {
        Intent intent = new Intent(this, SoundPoolActivity.class);
        startActivity(intent);
    }


    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        if(EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            new AppSettingsDialog.Builder(this).build().show();
        }
    }
}