package com.lk.mynews.Bean;

/**
 * Created by Mr.li on 2016/1/13.
 */
public class NewsContentBean {

    private String title;
    private String picUrl;
    private String time;
    private String url;

    public NewsContentBean(String title, String picUrl, String time, String url) {
        this.title = title;
        this.picUrl = picUrl;
        this.time = time;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
