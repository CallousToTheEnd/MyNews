package com.lk.mynews.Bean;

/**
 * Created by Mr.li on 2016/1/23.
 */
public class TopicFragmentItemBean {

    private String name;
    private String bgImage;
    private String headImage;
    private String desc;
    private int interest;
    private int type;
    private int state;

    public TopicFragmentItemBean(String name, String bgImage, String headImage, String desc, int interest, int type, int state) {
        this.name = name;
        this.bgImage = bgImage;
        this.headImage = headImage;
        this.desc = desc;
        this.interest = interest;
        this.type = type;
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBgImage() {
        return bgImage;
    }

    public void setBgImage(String bgImage) {
        this.bgImage = bgImage;
    }

    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getInterest() {
        return interest;
    }

    public void setInterest(int interest) {
        this.interest = interest;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

}
