package com.example.edgoo.popularmovies;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.edgoo.popularmovies.Utilities.FetchJson;
import com.example.edgoo.popularmovies.Utilities.ParseMoviedb;

import org.json.JSONException;

import java.io.IOException;
import java.net.URL;

public class MovieDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_detail);

        getIncomingIntent();
    }

    private void getIncomingIntent(){

        String movieTitle = getIntent().getStringExtra("movie_title");

        setItemInfo(movieTitle);
    }

    private void setItemInfo(String movieTitle){

        TextView name = findViewById(R.id.movie_detail_name);
        name.setText(movieTitle);
    }
}
