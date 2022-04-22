package com.imooc.mvp.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Author   ： cxw
 * Date     ： 2022/4/23 00:21
 * Explain  :  请在此输入文件说明
 */

public class GoodsBean implements Serializable {

  private int goodsId;
  private String text;
  private int spanSize;
  private String iamgeUrl;
  private List<String> banners;

  public int getGoodsId() {
    return goodsId;
  }

  public void setGoodsId(int goodsId) {
    this.goodsId = goodsId;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public int getSpanSize() {
    return spanSize;
  }

  public void setSpanSize(int spanSize) {
    this.spanSize = spanSize;
  }

  public String getIamgeUrl() {
    return iamgeUrl;
  }

  public void setIamgeUrl(String iamgeUrl) {
    this.iamgeUrl = iamgeUrl;
  }

  public List<String> getBanners() {
    return banners;
  }

  public void setBanners(List<String> banners) {
    this.banners = banners;
  }
}
