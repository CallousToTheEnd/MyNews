package com.lk.mynews.Bean;

/**
 * Created by Mr.li on 2016/1/26.
 */
public class VaFragmentItemBean {

    private String title;
    private String desc;
    private int duration;
    private int tie;
    private int playCount;

    public VaFragmentItemBean(String title, String desc, int duration, int playCount, int tie) {
        this.title = title;
        this.desc = desc;
        this.duration = duration;
        this.tie = tie;
        this.playCount = playCount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getTie() {
        return tie;
    }

    public void setTie(int tie) {
        this.tie = tie;
    }

    public int getPlayCount() {
        return playCount;
    }

    public void setPlayCount(int playCount) {
        this.playCount = playCount;
    }

}
