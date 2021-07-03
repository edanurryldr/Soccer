package com.edanuryildirim.futbolapi.view;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.edanuryildirim.futbolapi.R;
import com.edanuryildirim.futbolapi.adapter.DetailAdapter;
import com.edanuryildirim.futbolapi.model.FutbolModel;
import com.edanuryildirim.futbolapi.service.FutbolAPI;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailActivity extends AppCompatActivity {

    ArrayList<FutbolModel> futbolModels;
    private String BASE_URL = "https://raw.githubusercontent.com/";
    Retrofit retrofit;
    RecyclerView recyclerView;
    DetailAdapter recyclerViewAdapter;
    TextView date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        recyclerView = findViewById(R.id.recyclerViewDetail);
        date = findViewById(R.id.date);
        //Retrofit & JSON
        Gson gson = new GsonBuilder().setLenient().create();

        retrofit= new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        loadDetailData();

    }

    private void loadDetailData() {
        final FutbolAPI futbolAPI = retrofit.create(FutbolAPI.class);
        Call<List<FutbolModel>> call = futbolAPI.getData();
        call.enqueue(new Callback<List<FutbolModel>>() {
            @Override
            public void onResponse(Call<List<FutbolModel>> call, Response<List<FutbolModel>> response) {
                if(response.isSuccessful()){
                    List<FutbolModel> responseDetailList = response.body();
                    futbolModels = new ArrayList<>(responseDetailList);

                    //RecyclerView
                    recyclerView.setLayoutManager(new LinearLayoutManager(DetailActivity.this));
                    recyclerViewAdapter = new DetailAdapter(futbolModels);
                    recyclerView.setAdapter(recyclerViewAdapter);

                    date.setText(futbolModels.get(0).date);

                }
            }

            @Override
            public void onFailure(Call<List<FutbolModel>> call, Throwable t) {
            t.printStackTrace();
            }
        });
    }
}
