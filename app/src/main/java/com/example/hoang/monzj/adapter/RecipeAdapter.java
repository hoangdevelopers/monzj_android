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
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by hoang on 20/03/2017.
 */

public class RecipeAdapter extends RecyclerView.Adapter <RecipeHolder> {
    private final ArrayList<RecipeItem> mItemList;
    public RecipeAdapter(ArrayList<RecipeItem> itemList) {
        mItemList = itemList;
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
        //ImageView thumbnail = (ImageView) container.findViewById(R.id.recipeThumbnail);
      //  Picasso.with(container.getContext()).load(item.getThumbnailUrl()).into(thumbnail);
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
