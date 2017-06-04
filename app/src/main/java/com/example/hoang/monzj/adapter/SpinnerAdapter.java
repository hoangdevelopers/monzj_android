package com.example.hoang.monzj.adapter;

import android.support.v7.widget.RecyclerView;
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
    public static ManagerSpinnerList activity = null;
    private int adapterType;

    public SpinnerAdapter(int adapterType) {
        this.adapterType = adapterType;
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
        final SpinnerItem item = activity.getItem(position, this.adapterType);
        View container = holder.itemView;
        TextView title = (TextView) container.findViewById(R.id.spinner_title);
        title.setText(item.getTitle());
    }

    @Override
    public int getItemCount() {
        return activity.sizeItemList(adapterType);
    }

    public void addItem(SpinnerItem item) {
        activity.addItem(item, adapterType);
        notifyDataSetChanged();
    }
}
