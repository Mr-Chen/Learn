package com.imooc.map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.amap.api.services.help.Tip;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Author   ： cxw
 * Date     ： 2022/4/19 06:59
 * Explain  :  请在此输入文件说明
 */
public class MyRVAdapter extends RecyclerView.Adapter<MyRVAdapter.MyViewHolder> {

    private Context mContext;
    private List<Tip> list;

    public MyRVAdapter(SearchActivity searchActivity, List<Tip> list) {
        this.list = list;
        mContext = searchActivity;
    }

    public void setData(List<Tip> list) {
        if (list == null) {
            this.list.clear();
            notifyDataSetChanged();
            return;
        }
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_rv, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tv.setText(list.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mClickListener != null) {
                        mClickListener.onItemClick(getAdapterPosition());

                    }
                }
            });

        }
    }

    private OnItemClickListener mClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        mClickListener = listener;
    }

    interface OnItemClickListener {

        void onItemClick(int position);
    }

}
