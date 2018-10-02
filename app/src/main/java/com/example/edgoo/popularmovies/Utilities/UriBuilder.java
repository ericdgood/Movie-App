package com.example.edgoo.popularmovies.Utilities;

import java.net.MalformedURLException;
import java.net.URL;

public class UriBuilder {

    private static final String MOVIEDB_URL =
//            "sws"
            "https://api.themoviedb.org/3/discover/movie?page=1&include_video=false&include_adult=false&sort_by=popularity.desc&language=en-US&api_key=d32cc5bd24233a690a9c9c367d00111c";


    public static URL buildUri() {
        URL url = null;
        try {
            url = new URL(MOVIEDB_URL);
        } catch (MalformedURLException e) {
            return null;
        }
        return url;
    }
}

