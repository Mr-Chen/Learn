package com.imooc.mvp.ui.home;

import com.imooc.mvp.bean.BaseBean;
import com.imooc.mvp.bean.GoodsBean;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

/**
 * Author   ： cxw
 * Date     ： 2022/4/23 00:33
 * Explain  :  请在此输入文件说明
 */
interface HomeContract {

    interface IHomePresenter {
        void getGoods();
    }

    interface IHomeModle {
        Flowable<BaseBean<List<GoodsBean>>> getGoods();
    }

    interface IHomeView {

        void getGoodsSuccess(List<GoodsBean> goods);

        void getGoodsFailure(Throwable throwable);
    }
}
