package com.example.edgoo.popularmovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MovieDetails extends AppCompatActivity{

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    private TextView mMovieTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_detail);

        mMovieTitle = findViewById(R.id.movie_name);

        Intent intent = getIntent();
        if (intent == null) {
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.movie_json);
        String json = sandwiches[position];
        MoviesInfo sandwich = ParseMoviedb.parseMovieJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            return;
        }

        populateUI(sandwich);
    }

    private void populateUI(MoviesInfo sandwich) {


        // set Text to title
        mMovieTitle.setText(sandwich.getTitle());
    }

}
