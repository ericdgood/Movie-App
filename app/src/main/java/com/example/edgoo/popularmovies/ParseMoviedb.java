package com.example.edgoo.popularmovies;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class ParseMoviedb {

    private final static String TITLE = "title";

    public static String[] parseMovieJson(String jsonResponse) throws JSONException {

        String[] movieTitlesArray = null;

            JSONObject mainJsonObject = new JSONObject(jsonResponse);
            JSONArray movieTitleArray = mainJsonObject.getJSONArray("results");
        Log.d(TAG, "logtag parseMovieJson: " + movieTitleArray);

            for (int i = 0; i < movieTitleArray.length(); i++) {
                JSONObject currentEarthquake = movieTitleArray.getJSONObject(i);
                Log.d(TAG, "logtag currentMovie: " + currentEarthquake);
                String title = currentEarthquake.getString("title");
                Log.d(TAG, "logtag title: " + title);


                movieTitlesArray[i] = title;
            }


        return movieTitlesArray;
    }
}
