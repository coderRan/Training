package com.zdr.jiketoutiao.entity;

/**
 * Created by zdr on 16-8-2.
 */
public class News {
    private String newsTitle;
    private int newsImg;
    private String newsSrc;
    private String newsComm;
    private String newsData;

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public int getNewsImg() {
        return newsImg;
    }

    public void setNewsImg(int newsImg) {
        this.newsImg = newsImg;
    }

    public String getNewsSrc() {
        return newsSrc;
    }

    public void setNewsSrc(String newsSrc) {
        this.newsSrc = newsSrc;
    }

    public String getNewsComm() {
        return newsComm;
    }

    public void setNewsComm(String newsComm) {
        this.newsComm = newsComm;
    }

    public String getNewsData() {
        return newsData;
    }

    public void setNewsData(String newsData) {
        this.newsData = newsData;
    }
}
