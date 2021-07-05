package com.edanuryildirim.futbolapi.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.edanuryildirim.futbolapi.R;
import com.edanuryildirim.futbolapi.model.FutbolModel;

import java.util.List;

public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerviewAdapter.RowHolder> {

    private List<FutbolModel> futbolList;


    public RecyclerviewAdapter(List<FutbolModel> futbolList) {
        this.futbolList = futbolList;
    }

    @NonNull
    @Override
    public RowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext()) ;
        View view = layoutInflater.inflate(R.layout.row_layout,parent,false);
        return new RowHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RowHolder holder, int position) {
    holder.bind(futbolList.get(position),position);
    }

    @Override
    public int getItemCount() {
        return futbolList.size();
    }

    public class RowHolder extends RecyclerView.ViewHolder {
        TextView textTeamName;

        public RowHolder(@NonNull View itemView) {
            super(itemView);

        }

        public void bind(FutbolModel futbolModel,Integer position){

            textTeamName = itemView.findViewById(R.id.text_teamName);
            textTeamName.setText(futbolModel.team1);

        }
    }
}
