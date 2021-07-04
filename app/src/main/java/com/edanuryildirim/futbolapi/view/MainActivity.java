package com.edanuryildirim.futbolapi.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.edanuryildirim.futbolapi.R;
import com.edanuryildirim.futbolapi.adapter.RecyclerviewAdapter;
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

public class MainActivity extends AppCompatActivity {

        Switch aSwitch;
        savesharePrefences pref;

        ArrayList<FutbolModel> futbolModels;
        private String BASE_URL = "https://raw.githubusercontent.com/";
        Retrofit retrofit;
        RecyclerView recyclerView;
        RecyclerviewAdapter recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        aSwitch= findViewById(R.id.switchh);

        pref=new savesharePrefences(this);

        if(pref.getState()==true){
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }else{
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        if(pref.getState()==true){
            aSwitch.setChecked(true);
        }

        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    pref.setState(true);
                    getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }else{
                    pref.setState(false);
                    getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                }

            }
        });

        recyclerView =findViewById(R.id.recyclerView);

        //Retrofit & JSON
        Gson gson = new GsonBuilder().setLenient().create();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    loadData();

        }

        private void loadData(){
            FutbolAPI futbolAPI = retrofit.create((FutbolAPI.class));
            Call<List<FutbolModel>> call = futbolAPI.getData();
            call.enqueue(new Callback<List<FutbolModel>>() {
                @Override
                public void onResponse(Call<List<FutbolModel>> call, Response<List<FutbolModel>> response) {
                 if(response.isSuccessful()){
                     List<FutbolModel> responseList = response.body();
                     futbolModels = new ArrayList<>(responseList);

                     //RecyclerView
                     recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                     recyclerViewAdapter = new RecyclerviewAdapter(futbolModels);
                     recyclerView.setAdapter(recyclerViewAdapter);
                 }
                }

                @Override
                public void onFailure(Call<List<FutbolModel>> call, Throwable t) {
                t.printStackTrace();
                }
            });
        }

    public void Fikstur(View view) {
        Intent intent = new Intent(getApplicationContext(),DetailActivity.class);
        startActivity(intent);
    }
}

