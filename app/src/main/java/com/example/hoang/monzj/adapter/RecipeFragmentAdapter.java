package com.example.hoang.monzj.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.hoang.monzj.activity.RecipeActivity;
import com.example.hoang.monzj.model.RecipeItem;


/**
 * Created by hoangdev on 25/04/2017.
 */


public class RecipeFragmentAdapter extends FragmentPagerAdapter {
    private static int NUM_ITEMS = 4;
    private RecipeItem recipeItem;

    public RecipeFragmentAdapter(FragmentManager fragmentManager, RecipeItem recipeItem) {
        super(fragmentManager);
        this.recipeItem = recipeItem;
    }

    // Returns total number of pages
    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    // Returns the fragment to display for that page
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: // Fragment # 0 - OverView
                return RecipeActivity.OverviewFragment.newInstance(0, this.recipeItem);
            case 1: // Fragment # 0 - Ingredients
                return RecipeActivity.IngredientFragment.newInstance(1, this.recipeItem.getIngredients());
            case 2: // Fragment # 1 - Directions
                return RecipeActivity.OverviewFragment.newInstance(2, this.recipeItem);
            case 3: // Fragment # 1 - Review
                return RecipeActivity.OverviewFragment.newInstance(3, this.recipeItem);
            default:
                return null;
        }
    }

    // Returns the page title for the top indicator
    @Override
    public CharSequence getPageTitle(int position) {

        switch (position) {
            case 0: // Fragment # 0 - OverView
                return "Tổng quan";
            case 1: // Fragment # 1 - Ingredients
                return "Thành phần";
            case 2: // Fragment # 2 - Directions
                return "Cách làm";
            case 3: // Fragment # 3 - Review
                return "Bình luận";
            default:
                return null;
        }
    }
}