package com.imooc.mvp.ui.home;

import com.imooc.mvp.bean.BaseBean;
import com.imooc.mvp.bean.GoodsBean;
import com.imooc.mvp.network.RetrofitClient;
import com.imooc.mvp.network.service.GoodsService;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

/**
 * Author   ： cxw
 * Date     ： 2022/4/23 00:39
 * Explain  :  请在此输入文件说明
 */
public class HomeModel implements HomeContract.IHomeModle {
    @Override
    public Flowable<BaseBean<List<GoodsBean>>> getGoods() {

        return RetrofitClient.getInstance().getService(GoodsService.class).getGoods();
    }
}
