package com.imooc.media;

/**
 * Author   ： cxw
 * Date     ： 2022/4/21 00:43
 * Explain  :  请在此输入文件说明
 */
public class SoundBean {
    private String name;
    private int soundId;

    public SoundBean(String name, int soundId) {
        this.name = name;
        this.soundId = soundId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSoundId() {
        return soundId;
    }

    public void setSoundId(int soundId) {
        this.soundId = soundId;
    }
}
