package com.example.mailson.tcc.Classes;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mailson.tcc.R;

public class ViewHolder extends RecyclerView.ViewHolder {

    public ImageView imgIcone;
    public TextView  tvAcao;
    public TextView tvHorario;


    public ViewHolder(View itemView) {
        super(itemView);
        this.imgIcone = itemView.findViewById(R.id.imgIcone);
        this.tvAcao = itemView.findViewById(R.id.tvAcao);
        this.tvHorario = itemView.findViewById(R.id.tvHorario);


    }

}
