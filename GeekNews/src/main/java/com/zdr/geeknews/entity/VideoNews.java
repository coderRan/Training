package com.zdr.geeknews.entity;

/**
 * 视频类
 * Created by zdr on 16-8-18.
 */
public class VideoNews {
    private String title;
    private String videoUrl;
    private int ScrIc;
    private String srcName;
    private String playCount;
    private String videoTime;
    private String commentCount;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public int getScrIc() {
        return ScrIc;
    }

    public void setScrIc(int scrIc) {
        ScrIc = scrIc;
    }

    public String getSrcName() {
        return srcName;
    }

    public void setSrcName(String srcName) {
        this.srcName = srcName;
    }

    public String getPlayCount() {
        return playCount;
    }

    public void setPlayCount(String playCount) {
        this.playCount = playCount;
    }

    public String getVideoTime() {
        return videoTime;
    }

    public void setVideoTime(String videoTime) {
        this.videoTime = videoTime;
    }

    public String getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(String commentCount) {
        this.commentCount = commentCount;
    }
}
