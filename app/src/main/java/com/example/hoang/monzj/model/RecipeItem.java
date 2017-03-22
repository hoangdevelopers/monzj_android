package com.example.hoang.monzj.model;

/**
 * Created by hoang on 20/03/2017.
 */

public class RecipeItem {
    public RecipeItem(String name) {
        this.name = name;
    }

    private String id;
    private String name;
    private String thumbnailUrl;

    public RecipeItem(String id, String name, String thumbnailUrl) {
        this.id = id;
        this.name = name;
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getId() {
        return id;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public String getName() {

        return name;
    }

}
