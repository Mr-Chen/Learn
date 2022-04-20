package com.imooc.fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

public class ReplaceActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn1;
    private Button btn2;
    private FrameLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_replace);

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        container = findViewById(R.id.container);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                ReplaceFrag1 frag1 = new ReplaceFrag1();
                Bundle bundle = new Bundle();
                bundle.putString("msg", "Activity-->Fragment通信   Bundle");
                frag1.setArguments(bundle);
                frag1.setIReplaceFragment(new IReplaceFragment() {
                    @Override
                    public void sendMsgToActivity(String msg) {
                        Toast.makeText(ReplaceActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public String getMsgFromActivity() {
                        return "Activity向Fragment通过接口发送消息";
                    }
                });
                replaceFragment(frag1);
                break;
            case R.id.btn2:
                replaceFragment(new ReplaceFrag2());
                break;
        }
    }

    private void replaceFragment(Fragment frag) {

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.container, frag);
        transaction.addToBackStack(null);//入栈
        transaction.commit();

    }
}