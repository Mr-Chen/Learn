package com.imooc.recyclerview;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Author   ： cxw
 * Date     ： 2022/4/17 03:54
 * Explain  :  请在此输入文件说明
 */
public class RVAdapter extends RecyclerView.Adapter<RVAdapter.MyHolder> {

    private Context mContext;
    private List<DataBean> dataList;

    public RVAdapter(MainActivity mContext, List<DataBean> dataList) {
        this.mContext = mContext;
        this.dataList = dataList;

    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(mContext).inflate(R.layout.item_recycler, parent, false);
        return new MyHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {

        holder.title.setText(dataList.get(position).getTitle());
        holder.content.setText(dataList.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return dataList == null ? 0 : dataList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private TextView content;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_title);
            content = itemView.findViewById(R.id.tv_content);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        listener.itemClickListener(getAdapterPosition());
                    }
                }
            });
        }
    }

    private OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    interface OnItemClickListener {
        void itemClickListener(int position);
    }
}
