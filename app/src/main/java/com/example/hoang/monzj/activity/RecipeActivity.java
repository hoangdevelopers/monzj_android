package com.example.hoang.monzj.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.example.hoang.monzj.R;
import com.example.hoang.monzj.asynctask.LoadRecipe;
import com.example.hoang.monzj.model.RecipeItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import jp.wasabeef.blurry.Blurry;

public class RecipeActivity extends AppCompatActivity {

    private RecipeItem recipeItem;

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
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(this.recipeItem.getName());
        setSupportActionBar(toolbar);
        ImageView thumbnail = (ImageView) findViewById(R.id.recipeCover);
        Picasso.with(getApplicationContext()).load(this.recipeItem.getThumbnailUrl()).into(thumbnail);
        Blurry.with(RecipeActivity.this)
                .radius(10)
                .sampling(1)
//                .color(Color.argb(66, 0, 255, 255))
                .async()
                .capture(thumbnail)
                .into(thumbnail);
    }

}
