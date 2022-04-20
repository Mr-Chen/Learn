package com.imooc.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ReplaceFrag1#} factory method to
 * create an instance of this fragment.
 */
public class ReplaceFrag1 extends Fragment implements View.OnClickListener {

    private Context mContext;

    private TextView tv;
    private String msg;
    private Button btnSend;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        msg = bundle.getString("msg");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_replace_frag1, container, false);
        Button button = root.findViewById(R.id.btn);
        tv = root.findViewById(R.id.tv);
        btnSend = root.findViewById(R.id.send);

        btnSend.setOnClickListener(this);

        if (msg != null) {
            tv.setText(msg);
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "按钮点击", Toast.LENGTH_SHORT).show();
            }
        });
        return root;
    }

    private IReplaceFragment interReplace;

    public void setIReplaceFragment(IReplaceFragment interReplace) {
        this.interReplace = interReplace;
    }


    @Override
    public void onClick(View view) {
        if (interReplace != null) {
            interReplace.sendMsgToActivity("Fragment通过接口向Activity发送消息");
            btnSend.setText(interReplace.getMsgFromActivity());
        }
    }
}