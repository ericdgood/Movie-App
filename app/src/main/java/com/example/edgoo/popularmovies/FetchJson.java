package com.example.edgoo.popularmovies;

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

  private static String LOG_TAG;


//    public static List<MoviesInfo> fetchEarthquakeData(String requestUrl) {
//
//        // Create URL object
//        URL url = createUrl(requestUrl);
//        // Perform HTTP request to the URL and receive a JSON response back
//        String jsonResponse = null;
//        try {
//            jsonResponse = getResponseFromHttpUrl(url);
//            Log.d(LOG_TAG, "logtag Fetchdata jsonresponse" + jsonResponse);
//        } catch (IOException e) {
//            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
//        }
//        // Extract relevant fields from the JSON response and create a list of {@link Earthquake}s
//        MoviesInfo sandwich = ParseMoviedb.parseMovieJson(jsonResponse);
//        Log.d(TAG, "logtagFetchData: " + sandwich);
//        // Return the list of {@link Earthquake}s
//        return null;
//    }

    public static String getResponseFromHttpUrl(URL movieUrl) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) movieUrl.openConnection();
        Log.i(TAG, "logtag urlconnect: " + urlConnection);
        try {
            InputStream in = urlConnection.getInputStream();
            Log.i(TAG, "logtag in: " + in);

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

    /**
     * Returns new URL object from the given string URL.
     */
    public static URL createUrl(String MOVIEDB_URL) {
        URL url = null;
        try {
            url = new URL(MOVIEDB_URL);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Problem building the URL ", e);
        }
        Log.d(TAG, "logtagCreateUrl: " + url);
        return url;
    }

//    public static String makeHttpRequest(URL movieUrl) throws IOException {
//        String jsonResponse = "";
//        // If the URL is null, then return early.
//        if (movieUrl == null) {
//            return jsonResponse;
//        }
//        HttpURLConnection urlConnection = null;
//        InputStream inputStream = null;
//        try {
//            urlConnection = (HttpURLConnection) movieUrl.openConnection();
//            urlConnection.setReadTimeout(10000 /* milliseconds */);
//            urlConnection.setConnectTimeout(15000 /* milliseconds */);
//            urlConnection.setRequestMethod("GET");
//            urlConnection.connect();
//            // If the request was successful (response code 200),
//            // then read the input stream and parse the response.
//            if (urlConnection.getResponseCode() == 200) {
//                inputStream = urlConnection.getInputStream();
//                jsonResponse = readFromStream(inputStream);
//                Log.d(TAG, "logtagMakeHttpjsonResponse: " + jsonResponse);
//            } else {
//                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
//            }
//        } catch (IOException e) {
//            Log.e(LOG_TAG, "Problem retrieving the earthquake JSON results.", e);
//        } finally {
//            if (urlConnection != null) {
//                urlConnection.disconnect();
//            }
//            if (inputStream != null) {
//                // Closing the input stream could throw an IOException, which is why
//                // the makeHttpRequest(URL url) method signature specifies than an IOException
//                // could be thrown.
//                inputStream.close();
//            }
//        }
//        return jsonResponse;
//    }
//
//    /**
//     * Convert the {@link InputStream} into a String which contains the
//     * whole JSON response from the server.
//     */
//    private static String readFromStream(InputStream inputStream) throws IOException {
//        StringBuilder output = new StringBuilder();
//        if (inputStream != null) {
//            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
//            BufferedReader reader = new BufferedReader(inputStreamReader);
//            String line = reader.readLine();
//            while (line != null) {
//                output.append(line);
//                line = reader.readLine();
//            }
//        }
//        return output.toString();
//    }
    }
