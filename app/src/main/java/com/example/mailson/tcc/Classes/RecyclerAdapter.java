package com.example.mailson.tcc.Classes;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mailson.tcc.R;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<ViewHolder> {

    private List<HistoricoClass> list;
    public RecyclerAdapter(List<HistoricoClass> list){
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_historico, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
            HistoricoClass obj = list.get(position);

            //holder.imgIcone = obj.getIcone();
            holder.tvAcao.setText(obj.getAcao());
            holder.tvHorario.setText(obj.getHorario());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
