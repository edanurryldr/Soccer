package com.edanuryildirim.futbolapi.view;

import android.animation.ArgbEvaluator;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.edanuryildirim.futbolapi.R;
import com.edanuryildirim.futbolapi.adapter.Adapter;
import com.edanuryildirim.futbolapi.model.FutbolModel;
import com.edanuryildirim.futbolapi.model.Model;

import java.util.ArrayList;
import java.util.List;

public class SwipeActivity  extends AppCompatActivity {

    ViewPager viewPager;
    Adapter adapter;
    List<Model> models;
    Integer[] colors = null;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();
    ArrayList<FutbolModel> futbolModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        getIntentValue(getIntent());


        adapter = new Adapter(futbolModels,this);
        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);
        viewPager.setPadding(130,0,130,0);

        Integer[] colors_temp = {
                getResources().getColor(R.color.color1),
                getResources().getColor(R.color.color2),
                getResources().getColor(R.color.color3),
                getResources().getColor(R.color.color4)

        };
        colors= colors_temp;

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if(position <(adapter.getCount()-1)&& position < (colors.length-1)){
                    viewPager.setBackgroundColor((Integer)argbEvaluator.evaluate(
                            positionOffset,
                            colors[position],
                            colors[position+1]
                            )
                    );
                }
                else{
                    viewPager.setBackgroundColor(colors[colors.length-1]);
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    private void getIntentValue(Intent intent){
        if (intent.getParcelableArrayListExtra("futbolResponseModel")!=null){
            futbolModels=intent.getParcelableArrayListExtra("futbolResponseModel");
        }
    }
}
