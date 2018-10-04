package com.example.edgoo.popularmovies;

import android.content.Context;
import android.content.Intent;
import android.graphics.Movie;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.edgoo.popularmovies.Utilities.MoviesInfo;
import com.squareup.picasso.Picasso;

import java.util.List;

import static android.content.ContentValues.TAG;

class MovieAdapter extends BaseAdapter {

    private MoviesInfo[] mMovies;
    private Context mContext;

    MovieAdapter(Context context, MoviesInfo[] movies) {
        mContext = context;
        mMovies = movies;
    }

    @Override
    public int getCount() {
        if (null == mMovies) return 0;
        return mMovies.length;
    }

    @Override
    public Object getItem(int position) {
        if (mMovies == null || mMovies.length == 0) {
            return null;
        }

        return mMovies[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView movieTitle;

        // Will be null if it's not recycled. Will initialize ImageView if new.
        if (convertView == null) {
            movieTitle = new ImageView(mContext);
            movieTitle.setAdjustViewBounds(true);
        } else {
            movieTitle = (ImageView) convertView;
        }

        Picasso.with(mContext)
                .load(mMovies[position].getPoster())
                .into(movieTitle);

        return movieTitle;
    }

    //    SETS MOVIE TITLES
    public void setMovieData(MoviesInfo[] movieData) {
        this.mMovies = movieData;
        notifyDataSetChanged();
    }
}

