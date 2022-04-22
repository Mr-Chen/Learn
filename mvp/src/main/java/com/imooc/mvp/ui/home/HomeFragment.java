package com.imooc.mvp.ui.home;

import android.app.Activity;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gyf.immersionbar.ImmersionBar;
import com.imooc.mvp.MainActivity;
import com.imooc.mvp.R;
import com.imooc.mvp.bean.GoodsBean;
import com.imooc.mvp.ui.adapter.HomeAdapter;
import com.imooc.mvp.ui.base.BaseActivity;
import com.imooc.mvp.ui.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, HomeContract.IHomeView {


    private SwipeRefreshLayout refreshLayout;
    private RecyclerView recyclerView;
    private Toolbar mToolbar;

    private HomePresenter mPresenter;
    private List<GoodsBean> goods = new ArrayList<>();
    private HomeAdapter adapter;
    private HomeSpanSizeLookup spanSizeLookup;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initViews() {

        mToolbar = find(R.id.toolbar);
        refreshLayout = find(R.id.home_swip);
        recyclerView = find(R.id.home_recy);

        ImmersionBar.with((MainActivity) mContext)
                .titleBar(mToolbar)
                .init();

        GridLayoutManager manager = new GridLayoutManager(mContext, 4);
        spanSizeLookup = new HomeSpanSizeLookup(goods);
        manager.setSpanSizeLookup(spanSizeLookup);
        recyclerView.setLayoutManager(manager);

        adapter = new HomeAdapter(mContext, goods);
        recyclerView.setAdapter(adapter);


        refreshLayout.setOnRefreshListener(this);

        mPresenter = new HomePresenter(this);
        mPresenter.getGoods();


    }


    @Override
    public void onRefresh() {
        mPresenter.getGoods();
    }

    @Override
    public void getGoodsSuccess(List<GoodsBean> goods) {
        refreshLayout.setRefreshing(false);
        spanSizeLookup.setData(goods);
        adapter.setData(goods);
    }

    @Override
    public void getGoodsFailure(Throwable throwable) {
        refreshLayout.setRefreshing(false);
    }
}