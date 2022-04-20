package com.imooc.media;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Author   ： cxw
 * Date     ： 2022/4/21 00:50
 * Explain  :  请在此输入文件说明
 */
public class SoundAdapter extends RecyclerView.Adapter<SoundAdapter.MyHolder> {

    private List<SoundBean> sounds;
    private Context mContext;

    public SoundAdapter(List<SoundBean> sounds, Context context) {
        this.sounds = sounds;
        mContext = context;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(mContext).inflate(R.layout.item_sound, parent, false);
        return new MyHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {

        holder.tv.setText(sounds.get(position).getName());

    }


    @Override
    public int getItemCount() {
        return sounds != null ? sounds.size() : 0;
    }

    public class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tv;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.textView);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) {
                mClickListener.onItemClick(getAdapterPosition());
            }
        }
    }

    private OnItemClickListener mClickListener;

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        mClickListener = itemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}
