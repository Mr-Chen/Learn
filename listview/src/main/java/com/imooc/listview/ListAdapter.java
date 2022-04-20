package com.imooc.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Author   ： cxw
 * Date     ： 2022/4/17 03:22
 * Explain  :  请在此输入文件说明
 */
public class ListAdapter extends BaseAdapter {

  private Context mContext;
  private List<ListBean> dataList;

  public ListAdapter(MainActivity mContext, List<ListBean> dataList) {
    this.mContext = mContext;
    this.dataList = dataList;
  }

  @Override
  public int getCount() {
    if (dataList.size() > 0) {
      return dataList.size();
    }else{
      return 0;
    }
  }

  @Override
  public Object getItem(int i) {
    return dataList.get(i);
  }

  @Override
  public long getItemId(int i) {
    return i;
  }

  @Override
  public View getView(int i, View convertView, ViewGroup viewGroup) {

    ViewHolder holder;
    if (convertView == null) {
      convertView =  LayoutInflater.from(mContext).inflate(R.layout.item_list, viewGroup, false);
      holder = new ViewHolder();
      holder.title = convertView.findViewById(R.id.tv_title);
      holder.content = convertView.findViewById(R.id.tv_content);

      convertView.setTag(holder);
    }else{
      holder = (ViewHolder) convertView.getTag();
    }

    holder.title.setText(dataList.get(i).getTitle());
    holder.content.setText(dataList.get(i).getContent());

    return convertView;
  }

  private final class ViewHolder{

    TextView title;
    TextView content;

  }
}
