package com.example.hoang.monzj.adapter;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.hoang.monzj.activity.RecipeActivity;


/**
 * Created by hoangdev on 25/04/2017.
 */


public class RecipeFragmentAdapter extends FragmentPagerAdapter {
    private static int NUM_ITEMS = 4;

    public RecipeFragmentAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    // Returns total number of pages
    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    // Returns the fragment to display for that page
    @Override
    public RecipeActivity.OverviewFragment getItem(int position) {
        switch (position) {
            case 0: // Fragment # 0 - OverView
                return RecipeActivity.OverviewFragment.newInstance(0, "Page # 1");
            case 1: // Fragment # 0 - Ingredients
                return RecipeActivity.OverviewFragment.newInstance(1, "Page # 2");
            case 2: // Fragment # 1 - Directions
                return RecipeActivity.OverviewFragment.newInstance(2, "Page # 3");
            case 3: // Fragment # 1 - Review
                return RecipeActivity.OverviewFragment.newInstance(3, "Page # 4");
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