package com.edanuryildirim.futbolapi.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Score implements Parcelable {
    public ArrayList<Integer> ft;

    protected Score(Parcel in) {
    }

    public static final Creator<Score> CREATOR = new Creator<Score>() {
        @Override
        public Score createFromParcel(Parcel in) {
            return new Score(in);
        }

        @Override
        public Score[] newArray(int size) {
            return new Score[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
