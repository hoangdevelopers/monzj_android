package com.example.hoang.monzj.adapter;

import com.example.hoang.monzj.model.RecipeItem;

/**
 * Created by hoangdev on 26/04/2017.
 */

public interface ManagerIngredientList {
    RecipeItem.Ingredient getItem(int position);

    int sizeItemList();

    void addItem(RecipeItem.Ingredient item);
}