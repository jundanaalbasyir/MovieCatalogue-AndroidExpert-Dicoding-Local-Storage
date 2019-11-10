package com.jundana.moviecatalogue.model;

import android.os.Parcel;
import android.os.Parcelable;

public class MovieFav implements Parcelable {
    private	int	id;
    private	String movieName;
    private	String movieDetail;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMovieName() {
        return movieName;
    }

    public String getMovieDetail() {
        return movieDetail;
    }

    public MovieFav(int id, String movieName, String movieDetail) {
        this.id = id;
        this.movieName = movieName;
        this.movieDetail = movieDetail;
    }

    private MovieFav(Parcel in) {
        id = in.readInt();
        movieName = in.readString();
        movieDetail = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(movieName);
        dest.writeString(movieDetail);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<MovieFav> CREATOR = new Creator<MovieFav>() {
        @Override
        public MovieFav createFromParcel(Parcel in) {
            return new MovieFav(in);
        }

        @Override
        public MovieFav[] newArray(int size) {
            return new MovieFav[size];
        }
    };
}
