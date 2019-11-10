package com.jundana.moviecatalogue.model;

import android.os.Parcel;
import android.os.Parcelable;

public class TvshowFav implements Parcelable {
    private	int	id;
    private	String tvshowName;
    private	String tvshowDetail;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTvshowName() {
        return tvshowName;
    }

    public String getTvshowDetail() {
        return tvshowDetail;
    }

    public TvshowFav(int id, String tvshowName, String tvshowDetail) {
        this.id = id;
        this.tvshowName = tvshowName;
        this.tvshowDetail = tvshowDetail;
    }

    private TvshowFav(Parcel in) {
        id = in.readInt();
        tvshowName = in.readString();
        tvshowDetail = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(tvshowName);
        dest.writeString(tvshowDetail);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<TvshowFav> CREATOR = new Creator<TvshowFav>() {
        @Override
        public TvshowFav createFromParcel(Parcel in) {
            return new TvshowFav(in);
        }

        @Override
        public TvshowFav[] newArray(int size) {
            return new TvshowFav[size];
        }
    };
}
