package com.example.hoang.monzj.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hoang.monzj.R;
import com.example.hoang.monzj.activity.MainActivity;
import com.example.hoang.monzj.activity.RecipeActivity;
import com.example.hoang.monzj.view.RecipeHolder;
import com.example.hoang.monzj.model.RecipeItem;
import com.squareup.picasso.Picasso;

/**
 * Created by hoang on 20/03/2017.
 */

public class RecipeAdapter extends RecyclerView.Adapter<RecipeHolder> {

    public RecipeAdapter() {

    }

    @Override
    public RecipeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_recipe_item, parent, false);
        view.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));
        return new RecipeHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecipeHolder holder, int position) {
        View container = holder.itemView;
        TextView title = (TextView) container.findViewById(R.id.recipeTitle);
        final RecipeItem item = (RecipeItem) MainActivity.mItemList.get(position);
        title.setText(item.getName().replaceAll("(\\r|\\n|\\t)", ""));
        ImageView thumbnail = (ImageView) container.findViewById(R.id.recipeThumbnail);
        Picasso.with(container.getContext()).load(item.getThumbnailUrl()).into(thumbnail);

        container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), RecipeActivity.class);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return MainActivity.mItemList.size();
    }

    public void addItem(RecipeItem item) {
        MainActivity.mItemList.add(item);
        notifyDataSetChanged();
    }
}
