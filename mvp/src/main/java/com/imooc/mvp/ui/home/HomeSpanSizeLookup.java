package com.imooc.mvp.ui.home;

import com.imooc.mvp.bean.GoodsBean;

import java.util.List;

import androidx.recyclerview.widget.GridLayoutManager;

/**
 * Author   ： cxw
 * Date     ： 2022/4/23 00:27
 * Explain  :  请在此输入文件说明
 */
public class HomeSpanSizeLookup extends GridLayoutManager.SpanSizeLookup {

    private List<GoodsBean> list;

    public HomeSpanSizeLookup(List<GoodsBean> list) {
        this.list = list;
    }

    @Override
    public int getSpanSize(int position) {
        return list.get(position).getSpanSize();
    }

    public void setData(List<GoodsBean> goods) {
        list = goods;
    }
}
