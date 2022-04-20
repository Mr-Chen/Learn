package com.imooc.media;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;


import android.hardware.Camera;
import android.media.MediaRecorder;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;

public class RecordVideoActivity extends AppCompatActivity {

    private TextureView mTextureView;
    private Button mButton;
    private MediaRecorder recorder;
    private Camera camera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_video);

        mTextureView = findViewById(R.id.texture);
        mButton = findViewById(R.id.btn);

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void record(View view) {

        if (TextUtils.equals(mButton.getText().toString(), "开始")) {
            mButton.setText("结束");

            camera = Camera.open();
            camera.setDisplayOrientation(90);
            camera.unlock();

            recorder = new MediaRecorder();
            recorder.setCamera(camera);

            recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            recorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);

            recorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);

            recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
            recorder.setVideoEncoder(MediaRecorder.VideoEncoder.H264);
            recorder.setOutputFile(new File(getExternalFilesDir(""),"a.mp4").getAbsolutePath());
            recorder.setVideoSize(640,480);

            recorder.setOrientationHint(90);//调整视频角度
            recorder.setPreviewDisplay(new Surface(mTextureView.getSurfaceTexture()));
//            recorder.setOrientationHint(90);//调整视频角度

            try {
                recorder.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }

            recorder.start();
        }else{
            mButton.setText("开始");

            recorder.stop();
            recorder.release();
            camera.stopPreview();
            camera.release();

        }





    }
}