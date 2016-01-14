package com.example.li.fragmenttabhosttest.Bean;

/**
 * Created by Mr.li on 2016/1/13.
 */
public class SportNewsSlideBean {

    String picUrl;
    String link;
    String desc;

    public SportNewsSlideBean(String picUrl, String link, String desc) {
        this.picUrl = picUrl;
        this.link = link;
        this.desc = desc;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
