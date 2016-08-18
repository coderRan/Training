package com.zdr.geeknews.entity;

/**
 *
 * Created by zdr on 16-8-2.
 */
public class NewsType {
    private int id;
    private String typeName;
    private String url;
    private int checked = 0;
    private int fragmetType = 1;

    public NewsType() {
    }

    public NewsType(int id, String typeName, String url) {
        this.id = id;
        this.typeName = typeName;
        this.url = url;
    }

    public NewsType(int id, String typeName, String url, int fragmetType,int checked) {
        this.id = id;
        this.typeName = typeName;
        this.url = url;
        this.checked = checked;
        this.fragmetType = fragmetType;
    }

    public int isChecked() {

        return checked;
    }

    public void setChecked(int checked) {
        this.checked = checked;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getFragmetType() {
        return fragmetType;
    }

    public void setFragmetType(int fragmetType) {
        this.fragmetType = fragmetType;
    }
}
