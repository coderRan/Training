package com.zdr.geeknews.entity;

/**
 * Created by zdr on 16-8-2.
 */
public class NewsType {
    private int id;
    private String typeName;
    private String uri;
    private boolean checked = false;
    private int fragmetType = 1;


    public NewsType(int id, String typeName, String uri) {
        this.id = id;
        this.typeName = typeName;
        this.uri = uri;
    }

    public NewsType(int id, String typeName, String uri,int fragmetType) {
        this.id = id;
        this.typeName = typeName;
        this.uri = uri;
        this.checked = checked;
        this.fragmetType = fragmetType;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
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

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public int getFragmetType() {
        return fragmetType;
    }
}
