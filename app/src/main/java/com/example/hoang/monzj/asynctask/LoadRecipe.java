package com.example.hoang.monzj.asynctask;

import android.content.Context;
import android.os.AsyncTask;

import com.example.hoang.monzj.activity.RecipeActivity;
import com.example.hoang.monzj.model.RecipeItem;

import org.json.JSONArray;
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
            JSONObject metaObject = new JSONObject(jsonObject.getString("meta"));
            RecipeItem.Meta meta = new RecipeItem.Meta(
                    Integer.parseInt(metaObject.getString("songuoi")),
                    Integer.parseInt(metaObject.getString("chuanbi")),
                    Integer.parseInt(metaObject.getString("thuchien")));
            JSONArray imgUrlObjects = new JSONArray(jsonObject.getString("materials"));
            ArrayList<String> imgUrls = new ArrayList<String>();
            for (int i = 0; i < imgUrlObjects.length(); i++) {
                imgUrls.add(imgUrlObjects.getString(i));
            }
            JSONArray ingredientObjects = new JSONArray(jsonObject.getString("materials"));
            ArrayList<RecipeItem.Ingredient> ingredients = new ArrayList<RecipeItem.Ingredient>();
            for (int i = 0; i < ingredientObjects.length(); i++) {
                JSONObject ingredientObject = new JSONObject(ingredientObjects.getString(i));
                JSONObject amountObject = new JSONObject(ingredientObject.getString("amount"));
                JSONObject elementObject = new JSONObject(ingredientObject.getString("element"));
                ingredients.add(new RecipeItem.Ingredient(
                        amountObject.getString("value"),
                        amountObject.getString("type"),
                        elementObject.getString("name")
                ));
            }

            ArrayList<RecipeItem.Step> steps = new ArrayList<RecipeItem.Step>();
            JSONArray stepObjects = new JSONArray(jsonObject.getString("steps"));
            for (int i = 0; i < stepObjects.length(); i++) {
                JSONObject stepObject = new JSONObject(stepObjects.getString(i));
                steps.add(new RecipeItem.Step(
                        stepObject.getString("desc"),
                        stepObject.getString("img")
                ));
            }
            RecipeItem recipeItem = new RecipeItem(
                    jsonObject.getString("id"),
                    jsonObject.getString("name"),
                    jsonObject.getString("thumbnail"));

            recipeItem.setInfo(
                    jsonObject.getString("quote"),
                    meta,
                    imgUrls,
                    ingredients,
                    steps,
                    jsonObject.getString("info")
            );
            this.addItem(recipeItem);

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

