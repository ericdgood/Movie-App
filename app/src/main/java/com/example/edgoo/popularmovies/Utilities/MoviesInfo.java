package com.example.edgoo.popularmovies.Utilities;

import android.os.Parcel;
import android.os.Parcelable;

public class MoviesInfo implements Parcelable {

    private String title;
    private String poster;

    private MoviesInfo(Parcel in) {
        title = in.readString();
        poster = in.readString();
        overview = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(poster);
        dest.writeString(overview);
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    private String overview;

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    MoviesInfo() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<MoviesInfo> CREATOR = new Creator<MoviesInfo>() {
        @Override
        public MoviesInfo createFromParcel(Parcel in) {
            return new MoviesInfo(in);
        }

        @Override
        public MoviesInfo[] newArray(int size) {
            return new MoviesInfo[size];
        }
    };
}
