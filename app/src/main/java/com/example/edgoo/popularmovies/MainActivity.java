package com.example.edgoo.popularmovies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.edgoo.popularmovies.Utilities.FetchMovieData;
import com.example.edgoo.popularmovies.Utilities.MoviesInfo;

public class MainActivity extends AppCompatActivity {

    private static final String MOVIEDB_URL =
//            "https://api.themoviedb.org/3/movie/top_rated?api_key=d32cc5bd24233a690a9c9c367d00111c&language=en-US&page=1";
            "https://api.themoviedb.org/3/discover/movie?page=1&include_video=false&include_adult=false&sort_by=popularity.desc&language=en-US&api_key=d32cc5bd24233a690a9c9c367d00111c";

    private GridView mGridView;
    private MovieAdapter mMovieAdapter;
    MoviesInfo[] mMovies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGridView = (GridView) findViewById(R.id.gridview);
        mGridView.setOnItemClickListener(moviePosterClickListener);
        mMovieAdapter = new MovieAdapter(this, mMovies);
        mGridView.setAdapter(mMovieAdapter);
        loadMovieData();
    }

    private final GridView.OnItemClickListener moviePosterClickListener = new GridView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            MoviesInfo movie = (MoviesInfo) parent.getItemAtPosition(position);

            Intent intent = new Intent(getApplicationContext(), MovieDetails.class);
            intent.putExtra("movie_title", movie.getTitle());
            intent.putExtra("overview", movie.getOverview());

            startActivity(intent);
        }
    };

    private void loadMovieData() {
        new FetchMovieData(MOVIEDB_URL, mMovieAdapter).execute();
    }
}

