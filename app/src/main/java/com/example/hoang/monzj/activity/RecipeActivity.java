package com.example.hoang.monzj.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hoang.monzj.R;
import com.example.hoang.monzj.adapter.RecipeFragmentAdapter;
import com.example.hoang.monzj.asynctask.LoadRecipe;
import com.example.hoang.monzj.model.RecipeItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import jp.wasabeef.blurry.Blurry;

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
        recipeFragmentAdapter = new RecipeFragmentAdapter(getSupportFragmentManager());
        viewPager = (ViewPager) findViewById(R.id.recipeViewPager);
        viewPager.setAdapter(recipeFragmentAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

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
                .async()
                .capture(thumbnail)
                .into(thumbnail);
    }

    public static class OverviewFragment extends Fragment {
        // Store instance variables
        private String title;
        private int page;

        // newInstance constructor for creating fragment with arguments
        public static OverviewFragment newInstance(int page, String title) {
            OverviewFragment fragment = new OverviewFragment();
            Bundle args = new Bundle();
            args.putInt("someInt", page);
            args.putString("someTitle", title);
            fragment.setArguments(args);
            return fragment;
        }

        // Store instance variables based on arguments passed
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            page = getArguments().getInt("someInt", 0);
            title = getArguments().getString("someTitle");
        }

        // Inflate the view for the fragment based on layout XML
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_ovrecipe, container, false);
            TextView tvLabel = (TextView) view.findViewById(R.id.title);
            tvLabel.setText(page + " -- " + title);
            return view;
        }
    }

}
