package com.edanuryildirim.futbolapi.service;

import com.edanuryildirim.futbolapi.model.FutbolModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FutbolAPI {

    //GET,POST,UPDATE,DELETE

    @GET("edanurryldr/Soccer/main/futbol.json")
    Call<List<FutbolModel>> getData();



}
