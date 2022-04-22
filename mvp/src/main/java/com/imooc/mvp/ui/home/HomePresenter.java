package com.imooc.mvp.ui.home;

import android.accounts.NetworkErrorException;

import com.imooc.mvp.bean.BaseBean;
import com.imooc.mvp.bean.GoodsBean;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * Author   ： cxw
 * Date     ： 2022/4/23 00:38
 * Explain  :  请在此输入文件说明
 */
public class HomePresenter implements HomeContract.IHomePresenter {

    private HomeContract.IHomeView mHomeView;
    private HomeContract.IHomeModle model;

    public HomePresenter(HomeContract.IHomeView mHomeView) {
        this.mHomeView = mHomeView;
        model = new HomeModel();
    }

    @Override
    public void getGoods() {


        model.getGoods()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BaseBean<List<GoodsBean>>>() {
                    @Override
                    public void accept(BaseBean<List<GoodsBean>> listBaseBean) throws Throwable {
                        if (listBaseBean != null && listBaseBean.getData() != null && listBaseBean.getData().size() > 0) {
                            mHomeView.getGoodsSuccess(listBaseBean.getData());
                        } else {
                            mHomeView.getGoodsFailure(new NetworkErrorException());
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        mHomeView.getGoodsFailure(throwable);
                    }
                });


    }
}
