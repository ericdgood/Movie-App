package com.example.edgoo.popularmovies.Utilities;

import android.os.AsyncTask;

import com.example.edgoo.popularmovies.MainActivity;
import com.example.edgoo.popularmovies.MovieAdapter;

import org.json.JSONException;

import java.io.IOException;
import java.net.URL;

public class FetchMovieData extends AsyncTask<String, Void, MoviesInfo[]> {


    private MovieAdapter mMovieAdapter;
    private final String MOVIEDB_URL;

    public FetchMovieData(String moviedbUrl, MovieAdapter movieAdapter) {
        MOVIEDB_URL = moviedbUrl;
        mMovieAdapter = movieAdapter;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected MoviesInfo[] doInBackground(String... strings) {

        URL movieUrl = FetchJson.createUrl(MOVIEDB_URL);

        try {
            assert movieUrl != null;
//                BUILDS MOVIEDB URL INTO STRING URL
            String jsonresponse = FetchJson.getResponseFromHttpUrl(movieUrl);

//                PARES MOVIEDB URL
            MoviesInfo[] movieTitles = ParseMoviedb.parseMovieJson(MainActivity.class, jsonresponse);

            return movieTitles;

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(MoviesInfo[] movieData) {
        mMovieAdapter.setMovieData(movieData);
    }
}