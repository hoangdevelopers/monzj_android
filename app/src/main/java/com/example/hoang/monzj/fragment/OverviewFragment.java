package com.example.hoang.monzj.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hoang.monzj.R;
import com.example.hoang.monzj.model.RecipeItem;
import com.squareup.picasso.Picasso;

/**
 * Created by user on 12/31/15.
 */
public class OverviewFragment extends android.support.v4.app.Fragment {
    // Store instance variables
    private String title;
    private int page;
    private RecipeItem recipeItem;

    // newInstance constructor for creating fragment with arguments
    public static OverviewFragment newInstance(int page, RecipeItem recipeItem) {
        OverviewFragment fragment = new OverviewFragment();
        Bundle args = new Bundle();
        args.putInt("page", page);
        fragment.recipeItem = recipeItem;
        fragment.setArguments(args);
        return fragment;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("page", 0);
    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ovrecipe, container, false);
        ImageView thumbnail = (ImageView) view.findViewById(R.id.recipe_cover);
        Picasso.with(container.getContext())
                .load(this.recipeItem.getThumbnailUrl())
                .into(thumbnail);
        TextView recipeDesc = (TextView) view.findViewById(R.id.recipe_desc);
        recipeDesc.setText(this.recipeItem.getQuote());
        TextView prepTime = (TextView) view.findViewById(R.id.prep_time);
        prepTime.setText(this.recipeItem.getMeta().getChuanbi() + "");
        TextView cookTime = (TextView) view.findViewById(R.id.cook_time);
        cookTime.setText(this.recipeItem.getMeta().getThuchien() + "");
        TextView serve = (TextView) view.findViewById(R.id.serve);
        serve.setText(this.recipeItem.getMeta().getSonguoi() + "");
        return view;
    }
}
