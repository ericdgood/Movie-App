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
    private String mParm = "popular";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGridView = (GridView) findViewById(R.id.gridview);
        mGridView.setOnItemClickListener(moviePosterClickListener);
        mMovieAdapter = new MovieAdapter(this, mMovies);
        mGridView.setAdapter(mMovieAdapter);
        setMyTitle(0);
        loadMovieData();
    }

    private final GridView.OnItemClickListener moviePosterClickListener = new GridView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            MoviesInfo movie = (MoviesInfo) parent.getItemAtPosition(position);

            Intent intent = new Intent(getApplicationContext(), MovieDetails.class);
            intent.putExtra("movie_title", movie.getTitle());
            intent.putExtra("overview", movie.getOverview());
            intent.putExtra("release", movie.getReleaseDate());
            intent.putExtra("vote", movie.getVoteAverage());
            intent.putExtra("poster", movie.getDetailMoviePoster());

            startActivity(intent);
        }
    };

    private void loadMovieData() {
        new FetchMovieData(mMovieAdapter, mParm).execute();
    }

    @Override
    // This method initialize the contents of the Activity's options menu.
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the Options Menu we specified in XML
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    private void setMyTitle(Integer setTitleTo){
        if(setTitleTo == 1){
            setTitle(getString(R.string.title_ratings));
        } else {
            setTitle(getString(R.string.title_popular));
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sort_top_rate:
                mParm = getString(R.string.ratings_parm);
                setMyTitle(1);
                loadMovieData();
                return true;
            case R.id.sort_popular:
                mParm = getString(R.string.popular_parm);
                setMyTitle(0);
                loadMovieData();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

