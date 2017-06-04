package com.example.hoang.monzj.fragment;

import android.app.Fragment;
import android.os.Bundle;
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
    public final static SpinnerAdapter dishTypeAdapter = new SpinnerAdapter(1);
    public final static SpinnerAdapter ingredientAdapter = new SpinnerAdapter(2);
    public final static SpinnerAdapter timeAdapter = new SpinnerAdapter(3);
    public static ArrayList<SpinnerItem> dishTypeList = new ArrayList<SpinnerItem>();
    public static ArrayList<SpinnerItem> ingredientList = new ArrayList<SpinnerItem>();
    public static ArrayList<SpinnerItem> timeList = new ArrayList<SpinnerItem>();
    // Store instance variables
    private View view;
    private RecyclerView dishTypeRecyclerView;
    private RecyclerView ingredientRecyclerView;
    private RecyclerView timeRecyclerView;

    public SpinnerFragment() {
    }

    // newInstance constructor for creating fragment with arguments
    public static SpinnerFragment newInstance(ArrayList<SpinnerItem> dishTypeList, ArrayList<SpinnerItem> ingredientList, ArrayList<SpinnerItem> timeList) {
        SpinnerFragment fragment = new SpinnerFragment();
        SpinnerFragment.dishTypeList = dishTypeList;
        SpinnerFragment.ingredientList = ingredientList;
        SpinnerFragment.timeList = timeList;
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
        view = inflater.inflate(R.layout.fragment_spinner, container, false);
        dishTypeAdapter.activity = this;
        ingredientAdapter.activity = this;
        timeAdapter.activity = this;
        this.configRecipeList(1);
        this.configRecipeList(2);
        this.configRecipeList(3);
        return view;
    }

    private void configRecipeList(int type) {
        LinearLayoutManager linearLayout;
        switch (type) {
            case 1:
                this.dishTypeRecyclerView = (RecyclerView) view.findViewById(R.id.dishTypeSpinner);
                linearLayout = new LinearLayoutManager(view.getContext());
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                this.dishTypeRecyclerView.setRecycledViewPool(new RecyclerView.RecycledViewPool());
                this.dishTypeRecyclerView.setHasFixedSize(true);
                this.dishTypeRecyclerView.setLayoutManager(linearLayout);
                this.dishTypeRecyclerView.setAdapter(dishTypeAdapter);
            case 2:
                this.ingredientRecyclerView = (RecyclerView) view.findViewById(R.id.ingredientSpinner);
                linearLayout = new LinearLayoutManager(view.getContext());
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                this.ingredientRecyclerView.setRecycledViewPool(new RecyclerView.RecycledViewPool());
                this.ingredientRecyclerView.setHasFixedSize(true);
                this.ingredientRecyclerView.setLayoutManager(linearLayout);
                this.ingredientRecyclerView.setAdapter(ingredientAdapter);
            case 3:
                this.timeRecyclerView = (RecyclerView) view.findViewById(R.id.timeSpinner);
                linearLayout = new LinearLayoutManager(view.getContext());
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                this.timeRecyclerView.setRecycledViewPool(new RecyclerView.RecycledViewPool());
                this.timeRecyclerView.setHasFixedSize(true);
                this.timeRecyclerView.setLayoutManager(linearLayout);
                this.timeRecyclerView.setAdapter(timeAdapter);
        }

    }

    @Override
    public SpinnerItem getItem(int position, int adapterType) {
        SpinnerItem spinnerItem = new SpinnerItem("", "");
        switch (adapterType) {
            case 1:
                spinnerItem = dishTypeList.get(position);
                break;
            case 2:
                spinnerItem = ingredientList.get(position);
                break;
            case 3:
                spinnerItem = timeList.get(position);
                break;
        }
        Log.v("a2", spinnerItem.getTitle() + " " + adapterType);
        return spinnerItem;
    }

    @Override
    public int sizeItemList(int adapterType) {

        int size = 0;
        switch (adapterType) {
            case 1:
                size = dishTypeList.size();
                break;
            case 2:
                size = ingredientList.size();
                break;
            case 3:
                size = timeList.size();
                break;
        }

        return size;
    }

    @Override
    public void addItem(SpinnerItem item, int adapterType) {

        switch (adapterType) {
            case 1:
                dishTypeList.add(item);
                break;
            case 2:
                ingredientList.add(item);
                break;
            case 3:
                timeList.add(item);
                break;
        }
    }
}
