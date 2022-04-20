package com.imooc.media;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.media.SoundPool;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class SoundPoolActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private SoundPool mSoundPool;

    private List<SoundBean> sounds = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound_pool);

        mRecyclerView = findViewById(R.id.rv);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(manager);

        mSoundPool = new SoundPool.Builder().build();
        sounds.add(new SoundBean("小鸟", mSoundPool.load(this, R.raw.birds, 1)));
        sounds.add(new SoundBean("小猫", mSoundPool.load(this, R.raw.cat, 1)));
        sounds.add(new SoundBean("小鸡", mSoundPool.load(this, R.raw.chicken, 1)));
        sounds.add(new SoundBean("小狗", mSoundPool.load(this, R.raw.dog, 1)));
        sounds.add(new SoundBean("小猪", mSoundPool.load(this, R.raw.pig, 1)));


        SoundAdapter adapter = new SoundAdapter(sounds, this);
        mRecyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new SoundAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                mSoundPool.play(sounds.get(position).getSoundId(),
                        0.5f, 0.5f, 1, 0, 1.0f);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mSoundPool != null) {
            for (SoundBean sound : sounds) {
                mSoundPool.unload(sound.getSoundId());
                mSoundPool.stop(sound.getSoundId());
            }

            mSoundPool.release();
        }
    }
}