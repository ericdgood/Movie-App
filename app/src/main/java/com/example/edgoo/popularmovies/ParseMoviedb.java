package com.example.edgoo.popularmovies;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.Arrays;

import static android.content.ContentValues.TAG;

public class ParseMoviedb {

    private final static String TITLE = "title";

    public static MoviesInfo parseMovieJson (String json){

        Log.d(TAG, "logtag: " + json);

        try {
            JSONObject mainJsonObject = new JSONObject(json);
            JSONArray earthquakeArray = mainJsonObject.getJSONArray("results");

            Log.d(TAG, "logtagobj: " + mainJsonObject);

            for (int i = 0; i < earthquakeArray.length(); i++) {
                JSONObject currentEarthquake = earthquakeArray.getJSONObject(i);
                String titles = currentEarthquake.getString("place");

                MoviesInfo titleList = new MoviesInfo(titles);

                Log.d(TAG, "logtagtitleList: " + titleList);
            }

            String title = mainJsonObject.getString(TITLE);

            return new MoviesInfo(title);

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
