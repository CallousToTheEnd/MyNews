package com.lk.mynews.Bean;

/**
 * Created by Mr.li on 2016/1/22.
 */
public class ReadFragmentItemBean {

    private String image;
    private String source;
    private String desc;

    public ReadFragmentItemBean(String image, String source, String desc) {
        this.image = image;
        this.source = source;
        this.desc = desc;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
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
