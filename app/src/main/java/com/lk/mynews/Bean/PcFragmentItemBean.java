package com.lk.mynews.Bean;

/**
 * Created by Mr.li on 2016/1/25.
 */
public class PcFragmentItemBean {

    private int image;
    private String firText;
    private String secText;

    public PcFragmentItemBean(int image, String firText, String secText) {
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

    public String getFirText() {
        return firText;
    }

    public void setFirText(String firText) {
        this.firText = firText;
    }

    public String getSecText() {
        return secText;
    }

    public void setSecText(String secText) {
        this.secText = secText;
    }
}
