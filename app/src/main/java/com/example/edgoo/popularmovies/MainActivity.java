package com.example.edgoo.popularmovies;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.edgoo.popularmovies.Utilities.FetchJson;
import com.example.edgoo.popularmovies.Utilities.MoviesInfo;
import com.example.edgoo.popularmovies.Utilities.ParseMoviedb;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity {

    private static final String MOVIEDB_URL =
            "https://api.themoviedb.org/3/discover/movie?page=1&include_video=false&include_adult=false&sort_by=popularity.desc&language=en-US&api_key=d32cc5bd24233a690a9c9c367d00111c";

    private RecyclerView mRecyclerView;
    private MovieAdapter mMovieAdapter;
    String[] movies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        RECYCLER VIEW SETTER WITH ADAPTER
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        mMovieAdapter = new MovieAdapter(this);
        mRecyclerView.setAdapter(mMovieAdapter);
        loadMovieData();
    }

    public class FetchMovieTask extends AsyncTask<String, Void, String[]> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String[] doInBackground(String... strings) {

            URL movieUrl = FetchJson.createUrl(MOVIEDB_URL);

            try {
                assert movieUrl != null;
//                BUILDS MOVIEDB URL INTO STRING URL
                String jsonresponse = FetchJson.getResponseFromHttpUrl(movieUrl);

//                PARES MOVIEDB URL
                String[] movieTitles = ParseMoviedb.parseMovieJson(MainActivity.this, jsonresponse);

                return movieTitles;

            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String[] movieData) {
            mMovieAdapter.setMovieData(movieData);
        }
    }

    private void loadMovieData() {
        new FetchMovieTask().execute();
    }
}

