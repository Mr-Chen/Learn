package com.imooc.anim;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "LOGE";
    private boolean flag;

    private RelativeLayout rootView;
    private ImageView iv;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rootView = findViewById(R.id.root);
        iv = findViewById(R.id.iv);
        tv = findViewById(R.id.tv);

        /* 帧动画*/
        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AnimationDrawable animationDrawable = (AnimationDrawable) rootView.getBackground();
                if (!flag) {
                    animationDrawable.start();
                    flag = true;
                } else {
                    animationDrawable.stop();
                    flag = false;
                }


            }
        });

        /*补间动画：设定初始和结束值*/
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.scale);
//                Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.translate);
//                Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.alpha);
//                Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.rotate);
                Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.anim_set);

                iv.startAnimation(animation);

            }
        });
        
        /*属性动画*/
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0f,1f);
        valueAnimator.setDuration(3000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                Log.e(TAG, "onAnimationUpdate: "+valueAnimator.getAnimatedValue());
            }
        });
        valueAnimator.start();

        /**
         * 第二个参数根据绑定对象是否包含set/get方法
         */
        ObjectAnimator animator = ObjectAnimator.ofFloat(tv,"alpha",0f,1f);
        animator.setDuration(4000);
        animator.start();

        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                Log.e(TAG, "AnimatorListener---》onAnimationStart ");
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                Log.e(TAG, "AnimatorListener---》动画执行结束 ");
            }

            @Override
            public void onAnimationCancel(Animator animator) {
                Log.e(TAG, "AnimatorListener---》onAnimationCancel ");
            }

            @Override
            public void onAnimationRepeat(Animator animator) {
                Log.e(TAG, "AnimatorListener---》onAnimationRepeat ");
            }
        });

        //适配器 可以选择要覆写的监听方法
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                Log.e(TAG, "AnimatorListenerAdapter---》动画执行结束 ");
            }
        });
    }
}