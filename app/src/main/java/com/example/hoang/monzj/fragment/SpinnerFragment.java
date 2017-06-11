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
import com.example.hoang.monzj.adapter.ManagerSpinnerList;
import com.example.hoang.monzj.adapter.SpinnerAdapter;
import com.example.hoang.monzj.model.SpinnerItem;

import java.util.ArrayList;

/**
 * Created by hoangdev on 5/10/17.
 */

public class SpinnerFragment extends Fragment implements ManagerSpinnerList {
    public final static SpinnerAdapter mAdapter = new SpinnerAdapter();
    public static ArrayList<SpinnerItem> items = new ArrayList<SpinnerItem>();
    // Store instance variables
    private View view;
    private RecyclerView mRecyclerView;

    public SpinnerFragment() {

    }
    // newInstance constructor for creating fragment with arguments
    public static SpinnerFragment newInstance(ArrayList<SpinnerItem> items) {
        SpinnerFragment fragment = new SpinnerFragment();
        SpinnerFragment.items = items;
        return fragment;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter.activity = this;
        mAdapter.addItem(new SpinnerItem("test", "test"));
        mAdapter.addItem(new SpinnerItem("test", "test"));
        mAdapter.addItem(new SpinnerItem("test", "test"));
        mAdapter.addItem(new SpinnerItem("test", "test"));
        mAdapter.addItem(new SpinnerItem("test", "test"));
        mAdapter.addItem(new SpinnerItem("test", "test"));
    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_spinner, container, false);


        this.configRecipeList();

        return view;
    }

    private void configRecipeList() {
        this.mRecyclerView = (RecyclerView) view.findViewById(R.id.dishTypeSpinner);
        LinearLayoutManager linearLayout = new LinearLayoutManager(view.getContext());
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        this.mRecyclerView.setRecycledViewPool(new RecyclerView.RecycledViewPool());
        this.mRecyclerView.setHasFixedSize(true);
        this.mRecyclerView.setLayoutManager(linearLayout);
        this.mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public SpinnerItem getItem(int position) {
        Log.v("a2", "a");
        return items.get(position);
    }

    @Override
    public int sizeItemList() {
        Log.v("a2", String.valueOf(items.size()));
        return items.size();
    }

    @Override
    public void addItem(SpinnerItem item) {
        Log.v("a2", "c");
        items.add(item);
    }
}
