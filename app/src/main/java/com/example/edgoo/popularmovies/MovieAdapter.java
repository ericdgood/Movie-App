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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.edgoo.popularmovies.Utilities.MoviesInfo;
import com.squareup.picasso.Picasso;

import java.util.List;

import static android.content.ContentValues.TAG;

class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private MoviesInfo[] mMovies;
    private Context mContext;

    MovieAdapter(Context context, MoviesInfo[] movies) {
        mContext = context;
        mMovies = movies;
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
        Picasso.with(mContext).load(mMovies[position].getPoster()).into(viewHolder.movieTitle);


        viewHolder.movieList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(mContext, MovieDetails.class);
                intent.putExtra("movie_title", mMovies[position].getTitle());
                intent.putExtra("overview", mMovies[position].getOverview());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (null == mMovies) return 0;
        return mMovies.length;
    }

    //      GETS AND HOLDS VIEWS
    class MovieViewHolder extends RecyclerView.ViewHolder {
        ImageView movieTitle;
        LinearLayout movieList;

        public MovieViewHolder(View view) {
            super(view);
            movieTitle = view.findViewById(R.id.movie_title);
            movieList = view.findViewById(R.id.movie_list);
        }
    }

    //    SETS MOVIE TITLES
    public void setMovieData(MoviesInfo[] movieData) {
        this.mMovies = movieData;
        notifyDataSetChanged();
    }
}
