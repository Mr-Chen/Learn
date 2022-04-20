package com.imooc.learn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "LTAG";
    private Button mButton;
    private EditText mEditText;
    private ProgressBar pb1;
    private ProgressBar pb2;

    private NotificationManager manager;
    private  Notification notification;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initListener();
        initNotification();
    }

    private void initNotification() {
        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel("learn", "通知", NotificationManager.IMPORTANCE_HIGH);
            manager.createNotificationChannel(channel);
        }

        Intent intent = new Intent(this,NotificationActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,2,intent,0);

        notification = new NotificationCompat.Builder(this,"learn")
                .setSmallIcon(R.drawable.vc_user)
                .setContentTitle("通知标题")
                .setContentText("通知内容")
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.p2))
                .setColor(Color.parseColor("#FF03DAC5"))
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .build();

    }

    public void sendNotify(View view) {
        manager.notify(1,notification);
    }

    public void cancelNotify(View view) {
        manager.cancel(1);
    }

    private void initViews() {
        mButton = findViewById(R.id.button);
        mEditText = findViewById(R.id.edit_text);
        pb1 = findViewById(R.id.pb1);
        pb2 = findViewById(R.id.pb2);
    }

    private void initListener() {
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e(TAG, "onClick: ");
                Log.e(TAG, "onClick: " + mEditText.getText().toString());
            }
        });

        mButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Log.e(TAG, "onLongClick: ");
                return false;
            }
        });

        mButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Log.e(TAG, "onTouch: ");
                return false;
            }
        });
    }

    public void progressClick(View view) {
        if (pb1.getVisibility() == View.VISIBLE) {
            pb1.setVisibility(View.GONE);
        } else {
//            pb1.setVisibility(View.VISIBLE);
        }

        int progress = pb2.getProgress();
        progress += 10;
        pb2.setProgress(progress);

    }



}