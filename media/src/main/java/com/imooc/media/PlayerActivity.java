package com.imooc.media;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.IOException;

public class PlayerActivity extends AppCompatActivity implements MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener {

    private TextureView mTextureView;
    private Button mButton;
    private MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        mTextureView = findViewById(R.id.texture);
        mButton = findViewById(R.id.button);
    }

    public void player(View view) {
        if (TextUtils.equals("播放",mButton.getText().toString())) {
            mButton.setText("停止");

            player = new MediaPlayer();
            player.setOnPreparedListener(this);
            player.setOnCompletionListener(this);

            try {
                player.setDataSource(new File(getExternalFilesDir(""),"a.mp4").getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
            }

            player.setSurface(new Surface(mTextureView.getSurfaceTexture()));
            player.prepareAsync();



        }else{

            mButton.setText("播放");
            player.stop();
            player.release();

        }
    }

    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {
        //准备完毕
        player.start();
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        //播放完毕
        player.stop();
        player.release();
    }
}