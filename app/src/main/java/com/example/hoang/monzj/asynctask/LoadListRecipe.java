package com.example.hoang.monzj.asynctask;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

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
    private ArrayList<RecipeItem> items;
    public LoadListRecipe(Context context, ArrayList<RecipeItem> items) {
        this.context = context;
        this.items = items;
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
            for (int i=0; i<jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String id = jsonObject.getString("id");
                String name = jsonObject.getString("name");
                String thumbnailUrl = jsonObject.getString(("thumbnail"));
                items.add(new RecipeItem(id, name, thumbnailUrl));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
