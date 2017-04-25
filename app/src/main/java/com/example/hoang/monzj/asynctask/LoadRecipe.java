package com.example.hoang.monzj.asynctask;

import android.content.Context;
import android.os.AsyncTask;

import com.example.hoang.monzj.activity.RecipeActivity;
import com.example.hoang.monzj.model.RecipeItem;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by hoang on 22/03/2017.
 */

public class LoadRecipe extends AsyncTask<String, Integer, String> {
    public RecipeActivity delegate = null;
    private Context context;
    private ArrayList<RecipeItem> recipeItems;

    public LoadRecipe(Context context) {
        this.context = context;
        this.recipeItems = new ArrayList<RecipeItem>();
    }

    @Override
    protected String doInBackground(String... params) {
        String url = params[0];
        ReadFileFromURL readFileFromURL = new ReadFileFromURL(url);
        String content = readFileFromURL.load(url).getUrl();
        return content;
    }

    @Override
    protected void onPostExecute(String s) {
        try {

            JSONObject jsonObject = new JSONObject(s);
            String id = jsonObject.getString("id");
            String name = jsonObject.getString("name");
            String thumbnailUrl = jsonObject.getString(("thumbnail"));
            this.addItem(new RecipeItem(id, name, thumbnailUrl));

            delegate.processFinish(this.getRecipeItems());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void addItem(RecipeItem recipeItem) {
        this.recipeItems.add(recipeItem);
    }

    private ArrayList<RecipeItem> getRecipeItems() {
        return this.recipeItems;
    }
}

