package com.example.hoang.monzj.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hoang.monzj.R;
import com.example.hoang.monzj.model.RecipeItem;
import com.example.hoang.monzj.view.IngredientHolder;

/**
 * Created by hoang on 20/03/2017.
 */

public class IngredientAdapter extends RecyclerView.Adapter<IngredientHolder> {
    public ManagerIngredientList activity = null;

    public IngredientAdapter() {

    }

    @Override
    public IngredientHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_ingredient_item, parent, false);
        view.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));
        return new IngredientHolder(view);
    }

    @Override
    public void onBindViewHolder(final IngredientHolder holder, int position) {
        final RecipeItem.Ingredient item = this.activity.getItem(position);
        View container = holder.itemView;
        TextView ingredientName = (TextView) container.findViewById(R.id.ingredient_name);
        ingredientName.setText(item.getElement().substring(0, 1).toUpperCase() + item.getElement().substring(1).toLowerCase() + "");
        TextView ingredientValue = (TextView) container.findViewById(R.id.ingredient_value);
        ingredientValue.setText(" ( " + item.getWeigh() + "");
        TextView ingredientType = (TextView) container.findViewById(R.id.ingredient_type);
        ingredientType.setText(item.getType().toLowerCase() + " )");

    }

    @Override
    public int getItemCount() {
        return this.activity.sizeItemList();
    }

    public void addItem(RecipeItem.Ingredient item) {
        this.activity.addItem(item);
        notifyDataSetChanged();
    }
}
