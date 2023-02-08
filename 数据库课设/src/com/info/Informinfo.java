package com.info;

public class Informinfo {
    private int index;
    private String title;
    private String infotime;
    private String content;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInfotime() {
        return infotime;
    }

    public void setInfotime(String infotime) {
        this.infotime = infotime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Informinfo() {
    }

    public Informinfo(int index, String title, String infotime, String content) {
        this.index = index;
        this.title = title;
        this.infotime = infotime;
        this.content = content;
    }
}
