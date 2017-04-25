package com.example.hoang.monzj.asynctask;

import com.example.hoang.monzj.model.RecipeItem;

import java.util.ArrayList;

/**
 * Created by hoangdev on 24/04/2017.
 */

public interface AsyncResponse {
    void processFinish(ArrayList<RecipeItem> recipeItems);
}
