package com.example.hoang.monzj.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.hoang.monzj.R;
import com.example.hoang.monzj.adapter.RecipeFragmentAdapter;
import com.example.hoang.monzj.asynctask.LoadRecipe;
import com.example.hoang.monzj.model.RecipeItem;

import java.util.ArrayList;


public class RecipeActivity extends AppCompatActivity {

    private RecipeItem recipeItem;
    private RecipeFragmentAdapter recipeFragmentAdapter;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        Intent intent = this.getIntent();
        final String url = "http://monzj-minhlv.rhcloud.com/Food/" + intent.getStringExtra("id");
        final LoadRecipe loadRecipe = new LoadRecipe(getApplicationContext());
        loadRecipe.delegate = this;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                loadRecipe.execute(url);
            }
        });


    }

    public void processFinish(ArrayList<RecipeItem> recipeItems) {
        this.recipeItem = recipeItems.get(0);

        recipeFragmentAdapter = new RecipeFragmentAdapter(getSupportFragmentManager(), this.recipeItem);
        viewPager = (ViewPager) findViewById(R.id.recipeViewPager);
        viewPager.setAdapter(recipeFragmentAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(this.recipeItem.getName());
        setSupportActionBar(toolbar);

    }

}
