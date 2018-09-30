package com.example.edgoo.popularmovies;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import static android.content.ContentValues.TAG;

public class ParseMoviedb {

    private final static String TITLE = "title";

    public static MoviesInfo parseMovieJson (String json){

        Log.d(TAG, "logtag: " + json);

        try {
            JSONObject mainJsonObject = new JSONObject(json);

            Log.d(TAG, "logtagobj: " + json);

            String title = mainJsonObject.getString(TITLE);

            return new MoviesInfo(title);

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
