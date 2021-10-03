package com.mycompany.testtask.models;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;

public class Company implements Parcelable {

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        @RequiresApi(api = Build.VERSION_CODES.M)
        public Company createFromParcel(Parcel in) {
            return new Company(in);
        }

        public Company[] newArray(int size) {
            return new Company[size];
        }
    };
    private String name;
    private String catchPhrase;
    private String bs;

    public Company(String name, String catchPhrase, String bs) {
        this.name = name;
        this.catchPhrase = catchPhrase;
        this.bs = bs;
    }

    public Company() {
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public Company(Parcel in) {
        this.name = in.readString();
        this.catchPhrase = in.readString();
        this.bs = in.readString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCatchPhrase() {
        return catchPhrase;
    }

    public void setCatchPhrase(String catchPhrase) {
        this.catchPhrase = catchPhrase;
    }

    public String getBs() {
        return bs;
    }

    public void setBs(String bs) {
        this.bs = bs;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.catchPhrase);
        dest.writeString(this.bs);
    }
}
