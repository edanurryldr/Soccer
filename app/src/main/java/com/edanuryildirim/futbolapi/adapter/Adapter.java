package com.edanuryildirim.futbolapi.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.edanuryildirim.futbolapi.R;
import com.edanuryildirim.futbolapi.model.FutbolModel;

import java.util.List;

public class Adapter extends PagerAdapter {

    private List<FutbolModel> models;
    private LayoutInflater layoutInflater;
    private Context context;

    public Adapter(List<FutbolModel> models, Context context) {
        this.models = models;
        this.context = context;
    }

    @Override
    public int getCount() {
        return models.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.items,container,false);

        ImageView imageView;
        TextView team1,team2,score1,score2;

        imageView=view.findViewById(R.id.image);
        imageView.setImageResource(R.drawable.soccer);
        team1 = view.findViewById(R.id.team1);
        team1.setText(models.get(position).team1);

        team2 = view.findViewById(R.id.team2);
        team2.setText(models.get(position).team2);

        score1=view.findViewById(R.id.score1);
        score1.setText(models.get(position).score.ft.get(position));

        container.addView(view,0);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}