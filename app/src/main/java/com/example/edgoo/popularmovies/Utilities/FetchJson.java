package com.example.edgoo.popularmovies.Utilities;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Scanner;

import static android.content.ContentValues.TAG;

public class FetchJson {

    public static String getResponseFromHttpUrl(URL movieUrl) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) movieUrl.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();
            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }

    public static URL createUrl(String MOVIEDB_URL) {
        URL url = null;
        try {
            url = new URL(MOVIEDB_URL);
        } catch (MalformedURLException e) {
            return null;
        }
        return url;
    }
}
