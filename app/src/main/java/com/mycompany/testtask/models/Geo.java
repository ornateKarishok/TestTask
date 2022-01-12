package com.mycompany.testtask.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Geo implements Parcelable {
    public static final Creator<Geo> CREATOR = new Creator<Geo>() {
        @Override
        public Geo createFromParcel(Parcel in) {
            return new Geo(in);
        }

        @Override
        public Geo[] newArray(int size) {
            return new Geo[size];
        }
    };

    private final String lat;
    private final String lng;

    protected Geo(Parcel in) {
        lat = in.readString();
        lng = in.readString();
    }

    public String getLat() {
        return lat;
    }

    public String getLng() {
        return lng;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(lat);
        dest.writeString(lng);
    }
}
