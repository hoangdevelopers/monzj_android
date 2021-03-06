package com.example.hoang.monzj.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hoang.monzj.R;
import com.example.hoang.monzj.activity.RecipeActivity;
import com.example.hoang.monzj.model.RecipeItem;
import com.example.hoang.monzj.view.RecipeHolder;
import com.squareup.picasso.Picasso;

/**
 * Created by hoang on 20/03/2017.
 */

public class RecipeAdapter extends RecyclerView.Adapter<RecipeHolder> {
    public ManagerRecipeList activity = null;
    public RecipeAdapter() {

    }

    private class VIEW_TYPES {
        public static final int Header = 1;
        public static final int Normal = 2;
        public static final int Footer = 3;
    }
    @Override
    public int getItemViewType(int position) {
        return VIEW_TYPES.Normal;
    }

    @Override
    public RecipeHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View rowView;
        rowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_recipe_item, parent, false);
        rowView.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));
        return new RecipeHolder(rowView);
    }

    @Override
    public void onBindViewHolder(final RecipeHolder holder, int position) {
        View container = holder.itemView;
        TextView title = (TextView) container.findViewById(R.id.recipeTitle);
        final RecipeItem item = this.activity.getItem(position);
        title.setText(item.getName());
        ImageView thumbnail = (ImageView) container.findViewById(R.id.recipeThumbnail);
        Picasso.with(container.getContext()).load(item.getThumbnailUrl()).into(thumbnail);

        container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), RecipeActivity.class);
                intent.putExtra("id", item.getId());
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.activity.sizeItemList();
    }

    public void addItem(RecipeItem item) {
        this.activity.addItem(item);
        notifyDataSetChanged();
    }
}
