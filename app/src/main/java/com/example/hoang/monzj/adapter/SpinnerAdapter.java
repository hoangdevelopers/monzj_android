package com.example.hoang.monzj.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hoang.monzj.R;
import com.example.hoang.monzj.model.SpinnerItem;
import com.example.hoang.monzj.view.SpinnerHolder;

/**
 * Created by hoang on 20/03/2017.
 */

public class SpinnerAdapter extends RecyclerView.Adapter<SpinnerHolder> {
    public ManagerSpinnerList activity = null;

    public SpinnerAdapter() {

    }

    @Override
    public SpinnerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_spinner_item, parent, false);
        view.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));
        return new SpinnerHolder(view);
    }

    @Override
    public void onBindViewHolder(final SpinnerHolder holder, int position) {
        final SpinnerItem item = this.activity.getItem(position);
        View container = holder.itemView;
        TextView stepDesc = (TextView) container.findViewById(R.id.spinner_title);
        stepDesc.setText(item.getTitle());
        Log.v("aa1", item.getTitle());
    }

    @Override
    public int getItemCount() {
        return this.activity.sizeItemList();
    }

    public void addItem(SpinnerItem item) {
        this.activity.addItem(item);
        notifyDataSetChanged();
    }
}
