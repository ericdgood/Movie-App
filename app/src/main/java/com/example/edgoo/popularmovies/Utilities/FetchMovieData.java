package com.example.edgoo.popularmovies.Utilities;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.example.edgoo.popularmovies.MovieAdapter;

import org.json.JSONException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import static android.content.ContentValues.TAG;

public class FetchMovieData extends AsyncTask<String, Void, MoviesInfo[]> {


    private final MovieAdapter mMovieAdapter;
    private final String mParm;

    public FetchMovieData(MovieAdapter movieAdapter, String parm) {
        mMovieAdapter = movieAdapter;
        mParm = parm;
    }

    @Override
    protected MoviesInfo[] doInBackground(String... strings) {

        URL movieUrl = null;
        try {
            movieUrl = getApiUrl(mParm);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        try {
            assert movieUrl != null;
//                BUILDS MOVIEDB URL INTO STRING URL
            String jsonresponse = FetchJson.getResponseFromHttpUrl(movieUrl);

//                PARES MOVIEDB URL

            return ParseMoviedb.parseMovieJson(jsonresponse);

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private URL getApiUrl(String mParm) throws MalformedURLException {

//        final String API_KEY_PARAM = "api_key";
//        https://api.themoviedb.org/3/movie/popular?api_key=d32cc5bd24233a690a9c9c367d00111c&language=en-US&page=1
//        https://api.themoviedb.org/3/movie/top_rated?api_key=d32cc5bd24233a690a9c9c367d00111c&language=en-US&page=1

            final String MOVIEDB_URL = "https://api.themoviedb.org/3/movie/" + mParm;
        Uri baseUri = Uri.parse(MOVIEDB_URL);
        Uri.Builder uriBuilder = baseUri.buildUpon();
        uriBuilder.appendQueryParameter("api_key", "d32cc5bd24233a690a9c9c367d00111c");
        uriBuilder.appendQueryParameter("language", "en-US");
        uriBuilder.appendQueryParameter("page", "1");
        Log.d(TAG, "logtag: " + uriBuilder);

        return new URL(uriBuilder.toString());
    }

    @Override
    protected void onPostExecute(MoviesInfo[] movieData) {
        mMovieAdapter.setMovieData(movieData);
    }
}