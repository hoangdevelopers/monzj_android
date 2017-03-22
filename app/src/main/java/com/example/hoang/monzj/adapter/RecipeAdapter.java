package com.example.hoang.monzj.adapter;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hoang.monzj.R;
import com.example.hoang.monzj.view.RecipeHolder;
import com.example.hoang.monzj.model.RecipeItem;

import java.util.List;

/**
 * Created by hoang on 20/03/2017.
 */

public class RecipeAdapter extends RecyclerView.Adapter <RecipeHolder> {
    private final List<RecipeItem> mItemList;
    private final int mDefaultSpanCount;
    public RecipeAdapter(List<RecipeItem> itemList, GridLayoutManager gridLayoutManager, int defaultSpanCount) {
        mItemList = itemList;
        mDefaultSpanCount = defaultSpanCount;
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return 1;
            }
        });
    }
    @Override
    public RecipeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_recipe_item, parent, false);
        view.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));
        return new RecipeHolder(view);
    }

    @Override
    public void onBindViewHolder(RecipeHolder holder, int position) {
        View container = holder.itemView;
        TextView title = (TextView) container.findViewById(R.id.recipeTitle);
        final RecipeItem item = (RecipeItem) mItemList.get(position);
        title.setText(item.getName());
      //  ImageView thumbnail = (ImageView) container.findViewById(R.id.recipeThumbnail);
    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }
    public void addItem(RecipeItem item) {
        mItemList.add(item);
        notifyDataSetChanged();
    }
}
