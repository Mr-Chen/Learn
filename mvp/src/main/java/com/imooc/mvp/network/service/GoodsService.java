package com.imooc.mvp.network.service;

import com.imooc.mvp.bean.BaseBean;
import com.imooc.mvp.bean.GoodsBean;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;
import retrofit2.http.GET;

/**
 * Author   ： cxw
 * Date     ： 2022/4/23 00:40
 * Explain  :  请在此输入文件说明
 */
public interface GoodsService {

 @GET("getGoods")
 Flowable<BaseBean<List<GoodsBean>>> getGoods();
}
