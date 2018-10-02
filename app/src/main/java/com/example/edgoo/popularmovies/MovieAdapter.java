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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.edgoo.popularmovies.Utilities.MoviesInfo;

import java.util.List;

import static android.content.ContentValues.TAG;

class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private String[] movies;
    private Context mContext;

    MovieAdapter(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public MovieAdapter.MovieViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.movie_grid;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        return new MovieViewHolder(view);
    }

    //          SETS ITEMS WITH VIEWHOLDER
    @Override
    public void onBindViewHolder(MovieViewHolder viewHolder, int position) {
        viewHolder.movieTitle.setText(movies[position]);

        viewHolder.movieList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(mContext, MovieDetails.class);
                intent.putExtra("movie_title", movies[position]);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (null == movies) return 0;
        return movies.length;
    }

    //      GETS AND HOLDS VIEWS
    class MovieViewHolder extends RecyclerView.ViewHolder {
        TextView movieTitle;
        LinearLayout movieList;

        public MovieViewHolder(View view) {
            super(view);
            movieTitle = view.findViewById(R.id.movie_title);
            movieList = view.findViewById(R.id.movie_list);
        }
    }

    //    SETS MOVIE TITLES
    public void setMovieData(String[] movieData) {
        this.movies = movieData;
        notifyDataSetChanged();
    }
}
