package com.example.hoang.monzj.asynctask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by hoang on 22/03/2017.
 */

public class ReadFileFromURL {
    public ReadFileFromURL(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    private String url;

    public ReadFileFromURL load(String theUrl) {
        StringBuilder content = new StringBuilder();
        try {
            URL url = new URL(theUrl);
            URLConnection urlConnection = url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line + "\n");
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.url = content.toString();
        return this;
    }
}
