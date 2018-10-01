package com.example.edgoo.popularmovies;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class MovieDetails extends AppCompatActivity {

    private static final String MOVIEDB_URL =
            "https://api.themoviedb.org/3/discover/movie?page=1&include_video=false&include_adult=false&sort_by=popularity.desc&language=en-US&api_key=d32cc5bd24233a690a9c9c367d00111c";
    private TextView mMovieTitle;
    private MovieAdapter mAdapter;

//    public static final String EXTRA_POSITION = "extra_position";
//    private static final int DEFAULT_POSITION = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_detail);

        mMovieTitle = findViewById(R.id.movie_name);

        loadWeatherData();
    }

    public class FetchMovieTask extends AsyncTask<String, Void, String[]> {

        @Override
        protected String[] doInBackground(String... strings) {

            URL movieUrl = FetchJson.createUrl(MOVIEDB_URL);

            try {
                String jsonresponse = FetchJson.getResponseFromHttpUrl(movieUrl);

                String[] movieTitles = ParseMoviedb.parseMovieJson(jsonresponse);

                return movieTitles;

            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(String[] weatherData) {
            if (weatherData != null) {
                /*
                 * Iterate through the array and append the Strings to the TextView. The reason why we add
                 * the "\n\n\n" after the String is to give visual separation between each String in the
                 * TextView. Later, we'll learn about a better way to display lists of data.
                 */
                for (String weatherString : weatherData) {
                    mMovieTitle.append((weatherString) + "\n\n\n");
                }
            }
        }
    }

    private void loadWeatherData() {
        new FetchMovieTask().execute();
    }


}
