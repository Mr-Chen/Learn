package com.imooc.mvp.ui.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * Author   ： cxw
 * Date     ： 2022/4/22 22:32
 * Explain  :  请在此输入文件说明
 */
public abstract class BaseFragment extends Fragment {

    private View rootView;
    protected Context mContext;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(getLayoutId(), container, false);
        initViews();
        return rootView;
    }

    protected abstract void initViews();

    protected abstract int getLayoutId();

    protected <T extends View> T find(@IdRes int id) {
        return rootView.findViewById(id);
    }
}
