package com.lk.mynews.Bean.SociologyJsonBean;

import java.util.List;

/**
 * Created by Mr.li on 2016/2/13.
 */
public class PageBean {

    private String allNum;
    private String allPages;
    private List<Contentlist> contentlist;
    private String currentPage;
    private String maxResult;

    public String getAllNum() {
        return allNum;
    }

    public void setAllNum(String allNum) {
        this.allNum = allNum;
    }

    public String getAllPages() {
        return allPages;
    }

    public void setAllPages(String allPages) {
        this.allPages = allPages;
    }

    public List<Contentlist> getContentlist() {
        return contentlist;
    }

    public void setContentlist(List<Contentlist> contentlist) {
        this.contentlist = contentlist;
    }

    public String getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }

    public String getMaxResult() {
        return maxResult;
    }

    public void setMaxResult(String maxResult) {
        this.maxResult = maxResult;
    }

}
