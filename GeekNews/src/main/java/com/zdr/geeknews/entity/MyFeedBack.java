package com.zdr.geeknews.entity;

/**
 * ’我的意见中‘的ListView的item
 * Created by zdr on 16-8-3.
 */
public class MyFeedBack {
    private int headId = 0;
    private String context;
    private String time;

    public int getHeadId() {
        return headId;
    }

    public void setHeadId(int headId) {
        this.headId = headId;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
