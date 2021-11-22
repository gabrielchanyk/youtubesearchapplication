package com.youtubemetricapp.youtubesearch.classes;

//class to store information from youtube video url and title
public class YtInfo {
    private String url;
    private String title;

    //public constructor for url and title
    public YtInfo(String url, String title) {
        this.url = url;
        this.title = title;
    }

    //getters and setters
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}
