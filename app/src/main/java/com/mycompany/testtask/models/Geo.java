package com.mycompany.testtask.models;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;

public class Geo implements Parcelable {
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        @RequiresApi(api = Build.VERSION_CODES.M)
        public Geo createFromParcel(Parcel in) {
            return new Geo(in);
        }

        public Geo[] newArray(int size) {
            return new Geo[size];
        }
    };
    private String lat;
    private String lng;

    public Geo(String lat, String lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public Geo() {
    }

    public Geo(Parcel in) {
        this.lng = in.readString();
        this.lat = in.readString();
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.lat);
        dest.writeString(this.lng);
    }
}
