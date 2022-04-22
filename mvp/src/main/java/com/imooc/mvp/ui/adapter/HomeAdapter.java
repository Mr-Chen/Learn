package com.imooc.mvp.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.FitCenter;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.imooc.mvp.MainActivity;
import com.imooc.mvp.R;
import com.imooc.mvp.bean.GoodsBean;
import com.youth.banner.Banner;
import com.youth.banner.adapter.BannerImageAdapter;
import com.youth.banner.holder.BannerImageHolder;
import com.youth.banner.indicator.CircleIndicator;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Author   ： cxw
 * Date     ： 2022/4/23 00:32
 * Explain  :  请在此输入文件说明
 */
public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private Context mContext;
    private List<GoodsBean> goods = new ArrayList<>();

    public HomeAdapter(Context context, List<GoodsBean> goods) {
        this.mContext = context;
        this.goods = goods;
    }

    public void setData(List<GoodsBean> data) {
        goods.clear();
        goods.addAll(data);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(viewType, parent, false);
        switch (viewType) {
            case R.layout.home_recycler_image_text:
                return new MultiHolder(view);
        }
        return new SingleHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        GoodsBean goodsBean = goods.get(position);
        switch (getItemViewType(position)) {
            case R.layout.home_recycler_banner:
                ((Banner) holder.itemView).setAdapter(new BannerImageAdapter<String>(goodsBean.getBanners()) {
                    @Override
                    public void onBindView(BannerImageHolder holder, String imageUrl, int position, int size) {
                        //图片加载自己实现
                        Glide.with(holder.itemView)
                                .load(imageUrl)
                                .apply(RequestOptions.bitmapTransform(new RoundedCorners(30)))
                                .into(holder.imageView);
                    }
                })
                        .addBannerLifecycleObserver((LifecycleOwner) mContext)//添加生命周期观察者
                        .setIndicator(new CircleIndicator(mContext));
                break;
            case R.layout.home_recycler_image_text:
                MultiHolder multiHolder = (MultiHolder) holder;
                multiHolder.text.setText(goodsBean.getText());
                Glide.with(mContext)
                        .load(goodsBean.getIamgeUrl())
                        .apply(RequestOptions.bitmapTransform(new CenterCrop()))
                        .into(multiHolder.image);
                break;
            case R.layout.home_recycler_image:
                Glide.with(mContext)
                        .load(goodsBean.getIamgeUrl())
                        .apply(RequestOptions.bitmapTransform(new CenterCrop()))
                        .into((ImageView) holder.itemView);

                break;
            case R.layout.home_recycler_text:
                ((TextView) holder.itemView).setText(goodsBean.getText());
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        GoodsBean goodsBean = goods.get(position);
        if (goodsBean.getBanners() != null) {//banner
            return R.layout.home_recycler_banner;

        } else if (goodsBean.getIamgeUrl() != null && goodsBean.getText() != null) {//图片+文字
            return R.layout.home_recycler_image_text;


        } else if (goodsBean.getIamgeUrl() != null) {//图片
            return R.layout.home_recycler_image;

        } else {//文字
            return R.layout.home_recycler_text;
        }
    }

    @Override
    public int getItemCount() {
        return goods.size();
    }

    //只显示图片或者文字
    class SingleHoler extends RecyclerView.ViewHolder {
        public SingleHoler(@NonNull View itemView) {
            super(itemView);
        }
    }

    //显示图片或者文字
    class MultiHolder extends RecyclerView.ViewHolder {

        TextView text;
        ImageView image;

        public MultiHolder(@NonNull View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.text);
            image = itemView.findViewById(R.id.image);
        }
    }


}
