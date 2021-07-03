package com.edanuryildirim.futbolapi.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.edanuryildirim.futbolapi.R;
import com.edanuryildirim.futbolapi.model.FutbolModel;

import java.util.ArrayList;

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.DetailHolder>{

    private ArrayList<FutbolModel> detailList;

    public DetailAdapter(ArrayList<FutbolModel> detailList){
        this.detailList = detailList;
    }

    @NonNull
    @Override
    public DetailAdapter.DetailHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext()) ;
        View view = layoutInflater.inflate(R.layout.item,parent,false);
        return new DetailHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull DetailAdapter.DetailHolder holder, int position) {
    holder.bind(detailList.get(position),position);

    }

    @Override
    public int getItemCount() {
        return detailList.size();
    }

    public class DetailHolder extends RecyclerView.ViewHolder {
        TextView team1,team2,score1,score2;

        public DetailHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void bind(FutbolModel futbolModel,Integer position){

            team1 = itemView.findViewById(R.id.team1);
            team1.setText(futbolModel.team1);

            team2 = itemView.findViewById(R.id.team2);
            team2.setText(futbolModel.team2);

            score1 = itemView.findViewById(R.id.score1);
            score1.setText(futbolModel.score.ft.get(1).toString());

            score2 = itemView.findViewById(R.id.score2);
            score2.setText(futbolModel.score.ft.get(0).toString());


        }
    }
}
