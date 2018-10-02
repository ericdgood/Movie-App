package com.example.edgoo.popularmovies.Utilities;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static android.content.ContentValues.TAG;

public class ParseMoviedb {

    public static String[] parseMovieJson(Context context, String jsonResponse) throws JSONException {

        String[] parsedMovieArray;

//          CREATES JSON OBJECT WITH JSON STRING
        JSONObject mainJsonObject = new JSONObject(jsonResponse);
//        GETS ARRAY OF MOVIE FROM RESULTS
        JSONArray movieTitleArray = mainJsonObject.getJSONArray("results");
//        ARRAY OF PARSED MOVIE INFO
        parsedMovieArray = new String[movieTitleArray.length()];


//                 LOOPS THROUGH EACH MOVE ARRAY RESULT
        for (int i = 0; i < movieTitleArray.length(); i++) {
//                CREATES A CURRENT MOVIE OBJECT
            JSONObject currentMovie = movieTitleArray.getJSONObject(i);
//                USED TO GET TITLE OF MOVIE
            String poster_path = currentMovie.getString("poster_path");
            String poster = "http://image.tmdb.org/t/p/w185/" + poster_path;


            parsedMovieArray[i] = poster;
        }
        return parsedMovieArray;
    }

//    EXAMPLE OF A MOVIE ARRAY
//{
//    "page": 1,
//        "total_results": 380171,
//        "total_pages": 19009,
//        "results": [
//    {
//        "vote_count": 3571,
//            "id": 351286,
//            "video": false,
//            "vote_average": 6.5,
//            "title": "Jurassic World: Fallen Kingdom",
//            "popularity": 155.521,
//            "poster_path": "/c9XxwwhPHdaImA2f1WEfEsbhaFB.jpg",
//            "original_language": "en",
//            "original_title": "Jurassic World: Fallen Kingdom",
//            "genre_ids": [
//        28,
//                12,
//                878
//      ],
//        "backdrop_path": "/3s9O5af2xWKWR5JzP2iJZpZeQQg.jpg",
//            "adult": false,
//            "overview": "Three years after the demise of Jurassic World, a volcanic eruption threatens the remaining dinosaurs on the isla Nublar, so Claire Dearing, the former park manager, recruits Owen Grady to help prevent the extinction of the dinosaurs once again.",
//            "release_date": "2018-06-06"
//    },
}
