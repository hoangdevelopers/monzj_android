package com.example.hoang.monzj.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.hoang.monzj.R;
import com.example.hoang.monzj.adapter.DirectionAdapter;
import com.example.hoang.monzj.adapter.ManagerDirectionList;
import com.example.hoang.monzj.model.RecipeItem;

import java.util.ArrayList;

/**
 * Created by hoangdev on 5/10/17.
 */

public class DirectionFragment extends Fragment implements ManagerDirectionList {
    public final static DirectionAdapter mAdapter = new DirectionAdapter();
    public static ArrayList<RecipeItem.Step> items = new ArrayList<RecipeItem.Step>();
    // Store instance variables
    private View view;
    private RecyclerView mRecyclerView;

    // newInstance constructor for creating fragment with arguments
    public static DirectionFragment newInstance(int page, ArrayList<RecipeItem.Step> items) {
        DirectionFragment fragment = new DirectionFragment();
        Bundle args = new Bundle();
        DirectionFragment.items = items;
        fragment.setArguments(args);
        return fragment;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_direction, container, false);
        mAdapter.activity = this;
        this.configRecipeList();
        return view;
    }

    private void configRecipeList() {
        this.mRecyclerView = (RecyclerView) view.findViewById(R.id.directionList);
        LinearLayoutManager linearLayout = new LinearLayoutManager(view.getContext());
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        this.mRecyclerView.setRecycledViewPool(new RecyclerView.RecycledViewPool());
        this.mRecyclerView.setHasFixedSize(true);
        this.mRecyclerView.setLayoutManager(linearLayout);
        this.mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public RecipeItem.Step getItem(int position) {
        Log.v("awa2", position + "");
        Log.v("awa2", position + "");
        return items.get(position);
    }

    @Override
    public int sizeItemList() {
        return items.size();
    }

    @Override
    public void addItem(RecipeItem.Step item) {
        items.add(item);
    }
}
