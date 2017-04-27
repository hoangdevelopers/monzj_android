package com.example.hoang.monzj.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hoang.monzj.R;
import com.example.hoang.monzj.model.RecipeItem;
import com.example.hoang.monzj.view.DirectionHolder;

/**
 * Created by hoang on 20/03/2017.
 */

public class DirectionAdapter extends RecyclerView.Adapter<DirectionHolder> {
    public ManagerDirectionList activity = null;

    public DirectionAdapter() {

    }

    @Override
    public DirectionHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_direction_item, parent, false);
        view.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));
        return new DirectionHolder(view);
    }

    @Override
    public void onBindViewHolder(final DirectionHolder holder, int position) {
        final RecipeItem.Step item = this.activity.getItem(position);
        View container = holder.itemView;
        TextView stepDesc = (TextView) container.findViewById(R.id.step_desc);
        stepDesc.setText(item.getDesc());
        TextView stepIndex = (TextView) container.findViewById(R.id.step_index);
        stepIndex.setText(position + 1 + "");

    }

    @Override
    public int getItemCount() {
        return this.activity.sizeItemList();
    }

    public void addItem(RecipeItem.Step item) {
        this.activity.addItem(item);
        notifyDataSetChanged();
    }
}
