package com.edanuryildirim.futbolapi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;

import com.edanuryildirim.futbolapi.R;
import com.edanuryildirim.futbolapi.model.FutbolModel;

import java.util.List;

public class ViewAdapter extends PagerAdapter {

    private List<FutbolModel> models;
    private LayoutInflater layoutInflater;
    private Context context;

    public ViewAdapter(List<FutbolModel> models, Context context) {
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
        View view = layoutInflater.inflate(R.layout.activity_detail,container,false);

        TextView date;
        RecyclerView recyclerView;

        date = view.findViewById(R.id.date);

        date.setText(models.get(position).date);
        container.addView(view,0);
        return view;

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}
