package com.imooc.mvp.ui.home;

import com.imooc.mvp.bean.GoodsBean;

import java.util.List;

/**
 * Author   ： cxw
 * Date     ： 2022/4/23 00:39
 * Explain  :  请在此输入文件说明
 */
public class HomeView implements HomeContract.IHomeView{

    @Override
  public void getGoodsSuccess(List<GoodsBean> goods) {

  }

  @Override
  public void getGoodsFailure(Throwable throwable) {

  }
}
