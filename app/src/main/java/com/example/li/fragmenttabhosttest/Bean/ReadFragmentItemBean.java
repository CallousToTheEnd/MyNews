package com.example.li.fragmenttabhosttest.Bean;

/**
 * Created by Mr.li on 2016/1/22.
 */
public class ReadFragmentItemBean {

    private int image;
    private String source;
    private String desc;

    public ReadFragmentItemBean(int image, String source, String desc) {
        this.image = image;
        this.source = source;
        this.desc = desc;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
