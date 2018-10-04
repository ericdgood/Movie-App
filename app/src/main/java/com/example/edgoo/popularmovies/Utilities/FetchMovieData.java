package com.example.edgoo.popularmovies.Utilities;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.example.edgoo.popularmovies.MainActivity;
import com.example.edgoo.popularmovies.MovieAdapter;

import org.json.JSONException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import static android.content.ContentValues.TAG;

public class FetchMovieData extends AsyncTask<String, Void, MoviesInfo[]> {


    private MovieAdapter mMovieAdapter;

    public FetchMovieData(MovieAdapter movieAdapter) {
        mMovieAdapter = movieAdapter;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected MoviesInfo[] doInBackground(String... strings) {

        URL movieUrl = null;
        try {
            movieUrl = getApiUrl();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

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

    private URL getApiUrl() throws MalformedURLException {
        final String MOVIEDB_URL = "https://api.themoviedb.org/3/discover/movie?";
//        final String SORT_BY_PARAM = "sort_by";
//        final String API_KEY_PARAM = "api_key";

        Uri baseUri = Uri.parse(MOVIEDB_URL);
        Uri.Builder uriBuilder = baseUri.buildUpon();
        uriBuilder.appendQueryParameter("page", "1");
        uriBuilder.appendQueryParameter("include_video", "false");
        uriBuilder.appendQueryParameter("include_adult", "false");
        uriBuilder.appendQueryParameter("sort_by", "popularity.desc");
        uriBuilder.appendQueryParameter("language", "en-US");
        uriBuilder.appendQueryParameter("api_key", "d32cc5bd24233a690a9c9c367d00111c");

        return new URL(uriBuilder.toString());
    }

    @Override
    protected void onPostExecute(MoviesInfo[] movieData) {
        mMovieAdapter.setMovieData(movieData);
    }
}