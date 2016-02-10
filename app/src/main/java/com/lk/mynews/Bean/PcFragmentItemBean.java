package com.lk.mynews.Bean;

/**
 * Created by Mr.li on 2016/1/25.
 */
public class PcFragmentItemBean {

    private int image;
    private int firText;
    private String secText;

    public PcFragmentItemBean(int image, int firText, String secText) {
        this.image = image;
        this.firText = firText;
        this.secText = secText;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getFirText() {
        return firText;
    }

    public void setFirText(int firText) {
        this.firText = firText;
    }

    public String getSecText() {
        return secText;
    }

    public void setSecText(String secText) {
        this.secText = secText;
    }
}
