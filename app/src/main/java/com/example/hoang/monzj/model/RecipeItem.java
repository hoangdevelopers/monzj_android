package com.example.hoang.monzj.model;

import java.util.ArrayList;

/**
 * Created by hoang on 20/03/2017.
 */

public class RecipeItem {
    private String id;
    private String name;
    private String thumbnailUrl;
    private Meta meta;
    private String quote;
    private ArrayList<String> imgUrls;
    private ArrayList<Ingredient> ingredients;
    private ArrayList<Step> steps;
    private String info;

    public static class Meta {
        private int songuoi;
        private int chuanbi;
        private int thuchien;

        public Meta(int songuoi, int chuanbi, int thuchien) {
            this.songuoi = songuoi;
            this.chuanbi = chuanbi;
            this.thuchien = thuchien;
        }

        public int getSonguoi() {
            return songuoi;
        }

        public int getChuanbi() {
            return chuanbi;
        }

        public int getThuchien() {
            return thuchien;
        }
    }

    public static class Ingredient {
        private String weigh;
        private String type;
        private String element;

        public Ingredient(String weigh, String type, String element) {
            this.weigh = weigh;
            this.type = type;
            this.element = element;
        }

        public String getWeigh() {
            return weigh;
        }

        public String getType() {
            return type;
        }

        public String getElement() {
            return element;
        }
    }

    public static class Step {
        private String desc;
        private String imgUrl;

        public Step(String desc, String imgUrl) {
            this.desc = desc;
            this.imgUrl = imgUrl;
        }

        public String getDesc() {
            return desc;
        }

        public String getImgUrl() {
            return imgUrl;
        }
    }
    public RecipeItem(String id, String name, String thumbnailUrl) {
        this.id = id;
        this.name = name;
        this.thumbnailUrl = thumbnailUrl;
    }

    public void setInfo(String quote, Meta meta, ArrayList<String> imgUrls, ArrayList<Ingredient> ingredients, ArrayList<Step> steps, String info) {
        this.quote = quote;
        this.meta = meta;
        this.imgUrls = imgUrls;
        this.ingredients = ingredients;
        this.steps = steps;
        this.info = info;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public Meta getMeta() {
        return meta;
    }

    public String getQuote() {
        return quote;
    }

    public ArrayList<String> getImgUrls() {
        return imgUrls;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public ArrayList<Step> getSteps() {
        return steps;
    }

    public String getInfo() {
        return info;
    }
}
