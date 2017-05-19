package com.example.hoang.monzj.fragment;

import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hoang.monzj.R;
import com.example.hoang.monzj.adapter.ManagerRecipeList;
import com.example.hoang.monzj.adapter.RecipeAdapter;
import com.example.hoang.monzj.asynctask.LoadListRecipe;
import com.example.hoang.monzj.model.RecipeItem;

import java.util.ArrayList;


/**
 * Created by hoangdev on 5/10/17.
 */

public class ListRecipeFragment extends Fragment implements ManagerRecipeList {
    // Store instance variables
    public final static RecipeAdapter mRecipeAdapter = new RecipeAdapter();
    public final static ArrayList<RecipeItem> mItemList = new ArrayList<>();
    private static final int DEFAULT_SPAN_COUNT = 2;
    public static int skip = -10;
    int firstVisibleItem, visibleItemCount, totalItemCount;
    private RecyclerView mRecyclerView;
    private int previousTotal = 0;
    private boolean loading = true;
    private int visibleThreshold = 0;

    private View view;

    // newInstance constructor for creating fragment with arguments
    public  ListRecipeFragment (){

    }
    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Lay list Recipe ve ()
        mRecipeAdapter.activity = this;
        loadRecipe();
        configRecipeList();
        //Test add item

    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_ingredient, container, false);
        return view;
    }
    public static void processFinish(ArrayList<RecipeItem> recipeItems) {
        for (RecipeItem recipeItem : recipeItems) {
            mRecipeAdapter.addItem(recipeItem);
        }

    }
    @Override
    public RecipeItem getItem(int position) {

        return mItemList.get(position);
    }

    @Override
    public int sizeItemList() {

        return mItemList.size();
    }

    @Override
    public void addItem(RecipeItem recipeItem) {
        mItemList.add(recipeItem);
    }
    public void loadRecipe() {
        skip += 10;
        if(getActivity() == null)
        {
            return;
        }
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new LoadListRecipe(getActivity().getApplicationContext()).execute("http://monzj-minhlv.rhcloud.com/Food?limit=10&skip=" + skip);
            }
        });
    }
    private void configRecipeList() {
        this.mRecyclerView = (RecyclerView) getActivity().findViewById(R.id.recipeList);
        final GridLayoutManager mLayoutManager = new GridLayoutManager(getActivity().getApplicationContext(), DEFAULT_SPAN_COUNT);
        this.mRecyclerView.setRecycledViewPool(new RecyclerView.RecycledViewPool());
        this.mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                visibleItemCount = mRecyclerView.getChildCount();
                totalItemCount = mLayoutManager.getItemCount();
                firstVisibleItem = mLayoutManager.findFirstVisibleItemPosition();

                if (loading) {
                    if (totalItemCount > previousTotal) {
                        loading = false;
                        previousTotal = totalItemCount;
                    }
                }
                if (!loading && (totalItemCount - visibleItemCount)
                        <= (firstVisibleItem + visibleThreshold)) {
                    // End has been reached

                    Log.i("info", skip + "");

                    // Do something
                    loadRecipe();
                    loading = true;
                }
            }
        });
        this.mRecyclerView.setLayoutManager(mLayoutManager);
        this.mRecyclerView.setAdapter(mRecipeAdapter);
    }
}