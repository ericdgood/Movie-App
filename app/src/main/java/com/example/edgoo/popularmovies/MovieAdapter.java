package com.example.edgoo.popularmovies;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

class MovieAdapter extends ArrayAdapter<MoviesInfo> {

    public MovieAdapter(MovieDetails movieDetails, ArrayList<MoviesInfo> moviesInfos) {
        super(movieDetails, 0, moviesInfos);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.movie_grid, parent, false);
        }

        MoviesInfo currentQuake = getItem(position);

        TextView movietitleview = (TextView) listItemView.findViewById(R.id.movie_title_listview);
        String movietitle = currentQuake.getTitle();
        movietitleview.setText(movietitle);

        return listItemView;
    }
}
