package com.imooc.internet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Transformations;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory;

public class GlideActivity extends AppCompatActivity {

    private String url = "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fup.enterdesk.com%2Fedpic_source%2Ff5%2F58%2F97%2Ff55897c1b5fdcee3222bd9b3f97975ac.jpg&refer=http%3A%2F%2Fup.enterdesk.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1652965509&t=11ff0402dfb5000507547c4f947568bd";

    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide);

        ImageView iv = findViewById(R.id.iv);


        DrawableCrossFadeFactory factory =
                new DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build();
        BitmapTransformation s = null;
        Glide.with(this)
                .load(url)
                .placeholder(R.drawable.ic_loading)
                .error(R.drawable.ic_error)
//                .transition(DrawableTransitionOptions.withCrossFade())//淡入效果 时间
                .transition(DrawableTransitionOptions.withCrossFade(factory))//淡入效果
                .circleCrop()//变换
                .transform(new CircleCrop())//同上
                .transform(new RoundedCorners(35))
                .skipMemoryCache(true)//跳过内存缓存
                .diskCacheStrategy(DiskCacheStrategy.NONE)//跳过硬盘缓存
                .into(iv);


    }
}