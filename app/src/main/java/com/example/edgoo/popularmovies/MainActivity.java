package com.example.edgoo.popularmovies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.edgoo.popularmovies.Utilities.FetchMovieData;
import com.example.edgoo.popularmovies.Utilities.MoviesInfo;

public class MainActivity extends AppCompatActivity {

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
        new FetchMovieData(mMovieAdapter).execute();
    }

    @Override
    // This method initialize the contents of the Activity's options menu.
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the Options Menu we specified in XML
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Insert dummy data" menu option
            case R.id.sort_top_rate:
//                getU
                return true;
            // Respond to a click on the "Delete all entries" menu option
            case R.id.sort_popular:
//                deleteAllBooks();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

