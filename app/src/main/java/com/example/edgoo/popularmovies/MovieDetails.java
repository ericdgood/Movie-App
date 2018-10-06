package com.example.edgoo.popularmovies;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MovieDetails extends AppCompatActivity {

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_detail);

        getIncomingIntent();
    }

    private void getIncomingIntent(){

        String movieTitle = getIntent().getStringExtra("movie_title");
        String overview = getIntent().getStringExtra("overview");
        String release = getIntent().getStringExtra("release");
        String vote = getIntent().getStringExtra("vote");
        String poster = getIntent().getStringExtra("poster");

        setItemInfo(movieTitle, overview, release, vote, poster);
    }

    private void setItemInfo(String movieTitle, String overview, String release, String vote, String poster){

        TextView title = findViewById(R.id.movie_detail_name);
        title.setText(movieTitle);
        TextView overviewView = findViewById(R.id.overview);
        overviewView.setText(overview);
        TextView  releaseView= findViewById(R.id.release);
        releaseView.setText(release);
        TextView voteView = findViewById(R.id.vote);
        voteView.setText(vote);
        ImageView posterView = findViewById(R.id.poster_detail_view);
        Picasso.with(mContext)
                .load(poster)
                .into(posterView);
    }
}
