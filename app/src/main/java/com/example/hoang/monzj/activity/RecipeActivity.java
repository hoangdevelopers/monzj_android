package com.example.hoang.monzj.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.hoang.monzj.R;
import com.example.hoang.monzj.adapter.IngredientAdapter;
import com.example.hoang.monzj.adapter.ManagerIngredientList;
import com.example.hoang.monzj.adapter.RecipeFragmentAdapter;
import com.example.hoang.monzj.asynctask.LoadRecipe;
import com.example.hoang.monzj.model.RecipeItem;
import com.squareup.picasso.Picasso;

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

    public static class OverviewFragment extends Fragment {
        // Store instance variables
        private String title;
        private int page;
        private RecipeItem recipeItem;

        // newInstance constructor for creating fragment with arguments
        public static OverviewFragment newInstance(int page, RecipeItem recipeItem) {
            OverviewFragment fragment = new OverviewFragment();
            Bundle args = new Bundle();
            args.putInt("page", page);
            fragment.recipeItem = recipeItem;
            fragment.setArguments(args);
            return fragment;
        }

        // Store instance variables based on arguments passed
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            page = getArguments().getInt("page", 0);
        }

        // Inflate the view for the fragment based on layout XML
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_ovrecipe, container, false);
            ImageView thumbnail = (ImageView) view.findViewById(R.id.recipe_cover);
            Picasso.with(container.getContext())
                    .load(this.recipeItem.getThumbnailUrl())
                    .into(thumbnail);
            TextView recipeDesc = (TextView) view.findViewById(R.id.recipe_desc);
            recipeDesc.setText(this.recipeItem.getQuote());
            TextView prepTime = (TextView) view.findViewById(R.id.prep_time);
            prepTime.setText(this.recipeItem.getMeta().getChuanbi() + "");
            TextView cookTime = (TextView) view.findViewById(R.id.cook_time);
            cookTime.setText(this.recipeItem.getMeta().getThuchien() + "");
            TextView serve = (TextView) view.findViewById(R.id.serve);
            serve.setText(this.recipeItem.getMeta().getSonguoi() + "");
            return view;
        }
    }

    public static class IngredientFragment extends Fragment implements ManagerIngredientList {
        // Store instance variables
        private int page;
        private View view;
        private RecyclerView mRecyclerView;
        public final static IngredientAdapter mIngredientAdapter = new IngredientAdapter();
        public static ArrayList<RecipeItem.Ingredient> items = new ArrayList<RecipeItem.Ingredient>();

        // newInstance constructor for creating fragment with arguments
        public static IngredientFragment newInstance(int page, ArrayList<RecipeItem.Ingredient> ingredients) {
            IngredientFragment fragment = new IngredientFragment();
            Bundle args = new Bundle();
            args.putInt("page", page);
            items = ingredients;
            fragment.setArguments(args);
            return fragment;
        }

        // Store instance variables based on arguments passed
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            page = getArguments().getInt("page", 0);
        }

        // Inflate the view for the fragment based on layout XML
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            this.view = inflater.inflate(R.layout.fragment_ingredient, container, false);
            mIngredientAdapter.activity = this;
            this.configRecipeList();
            return view;
        }

        private void configRecipeList() {
            this.mRecyclerView = (RecyclerView) view.findViewById(R.id.ingredientList);
            LinearLayoutManager linearLayout = new LinearLayoutManager(view.getContext());
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            this.mRecyclerView.setRecycledViewPool(new RecyclerView.RecycledViewPool());
            this.mRecyclerView.setHasFixedSize(true);
            this.mRecyclerView.setLayoutManager(linearLayout);
            this.mRecyclerView.setAdapter(mIngredientAdapter);
        }

        @Override
        public RecipeItem.Ingredient getItem(int position) {
            return items.get(position);
        }

        @Override
        public int sizeItemList() {
            return items.size();
        }

        @Override
        public void addItem(RecipeItem.Ingredient item) {
            Log.v("axax", "hjhj");
            items.add(item);
        }
    }

}
