package com.example.hoang.monzj.asynctask;

import android.content.Context;
import android.os.AsyncTask;

import com.example.hoang.monzj.activity.MainActivity;
import com.example.hoang.monzj.fragment.ListRecipeFragment;
import com.example.hoang.monzj.model.RecipeItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by hoang on 22/03/2017.
 */

public class LoadListRecipe extends AsyncTask<String, Integer, String> {
    private Context context;
    private ArrayList<RecipeItem> recipeItems;
    public LoadListRecipe(Context context) {
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
            JSONArray jsonArray = new JSONArray(s);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String id = jsonObject.getString("id");
                String name = jsonObject.getString("name");
                String thumbnailUrl = jsonObject.getString(("thumbnail"));
                this.addItem(new RecipeItem(id, name, thumbnailUrl));
            }
            ListRecipeFragment.processFinish(this.getRecipeItems());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void addItem(RecipeItem recipeItem) {
        this.recipeItems.add(recipeItem);
    }

    public ArrayList<RecipeItem> getRecipeItems() {
        return this.recipeItems;
    }
}

