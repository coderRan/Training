package com.zdr.geeknews.entity;

import com.alibaba.fastjson.serializer.SerializeFilter;

import java.io.Serializable;

/**
 * JSON解析类
 * Created by zdr on 16-8-5.
 */
public class NetNews implements Serializable{


    /**
     * ctime : 2016-08-05 07:20
     * title : 专家预测中国将培育第一个基因编辑“超人”
     * description : 腾讯科技
     * picUrl : http://inews.gtimg.com/newsapp_ls/0/463946704_300240/0
     * url : http://tech.qq.com/a/20160805/004409.htm
     * type ： keji
     */

    private String ctime;
    private String title;
    private String description;
    private String picUrl;
    private String url;
    private String type;

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
