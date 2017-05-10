package com.example.hoang.monzj.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.hoang.monzj.R;
import com.example.hoang.monzj.adapter.IngredientAdapter;
import com.example.hoang.monzj.adapter.ManagerIngredientList;
import com.example.hoang.monzj.model.RecipeItem;

import java.util.ArrayList;

/**
 * Created by hoangdev on 5/10/17.
 */

public class IngredientFragment extends Fragment implements ManagerIngredientList {
    public final static IngredientAdapter mIngredientAdapter = new IngredientAdapter();
    public static ArrayList<RecipeItem.Ingredient> items = new ArrayList<RecipeItem.Ingredient>();
    // Store instance variables
    private int page;
    private View view;
    private RecyclerView mRecyclerView;

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
        items.add(item);
    }
}