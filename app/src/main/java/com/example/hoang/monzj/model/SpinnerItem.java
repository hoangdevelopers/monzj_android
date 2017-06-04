package com.example.hoang.monzj.model;

/**
 * Created by hoang on 20/03/2017.
 */

public class SpinnerItem {

    private String thumbnailUrl;
    private String title;

    public SpinnerItem(String thumbnailUrl, String title) {
        this.thumbnailUrl = thumbnailUrl;
        this.title = title;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
