package com.edanuryildirim.futbolapi.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

public class FutbolModel implements Parcelable {

    @SerializedName("team1")
    public String team1;

    @SerializedName("team2")
    public String team2;

    @SerializedName("date")
    public String date;

    @SerializedName("score")
    public Score score;

    public FutbolModel() {
    }

    protected FutbolModel(Parcel in) {
        team1 = in.readString();
        team2 = in.readString();
        date = in.readString();
    }

    public static final Creator<FutbolModel> CREATOR = new Creator<FutbolModel>() {
        @Override
        public FutbolModel createFromParcel(Parcel in) {
            return new FutbolModel(in);
        }

        @Override
        public FutbolModel[] newArray(int size) {
            return new FutbolModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(team1);
        dest.writeString(team2);
        dest.writeString(date);
    }
}
