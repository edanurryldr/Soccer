package com.edanuryildirim.futbolapi.model;

import com.google.gson.annotations.SerializedName;

public class FutbolModel {

    @SerializedName("team1")
    public String team1;

    @SerializedName("team2")
    public String team2;

    @SerializedName("date")
    public String date;

    @SerializedName("score")
    public Score score;


}
