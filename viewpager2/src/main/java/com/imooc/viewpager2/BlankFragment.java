package com.imooc.viewpager2;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * Author   ： cxw
 * Date     ： 2022/4/18 02:45
 * Explain  :  请在此输入文件说明
 */
public class BlankFragment extends Fragment {

    private Context mContext;
    private int tag;

    private View root;
    private RelativeLayout mRelativeLayout;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            tag = getArguments().getInt("msg");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        if (root == null) {
            root = inflater.inflate(R.layout.frag_blank, container, false);
        }

        mRelativeLayout = root.findViewById(R.id.root_view);

        setBackground();
        return root;
    }

    private void setBackground() {
        switch (tag) {
            case 0:
                mRelativeLayout.setBackground(mContext.getDrawable(R.drawable.v1));
                break;
            case 1:
                mRelativeLayout.setBackground(mContext.getDrawable(R.drawable.v2));
                break;
            case 2:
                mRelativeLayout.setBackground(mContext.getDrawable(R.drawable.v3));
                break;
            case 3:
                mRelativeLayout.setBackground(mContext.getDrawable(R.drawable.v4));
                break;
        }
    }

    public static Fragment newInstance(int tag) {
        BlankFragment fragment = new BlankFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("msg", tag);
        fragment.setArguments(bundle);

        return fragment;
    }
}
