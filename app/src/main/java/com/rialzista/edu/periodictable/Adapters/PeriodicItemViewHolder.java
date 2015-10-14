package com.rialzista.edu.periodictable.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rialzista.edu.periodictable.OnItemClickListener;
import com.rialzista.edu.periodictable.R;

public class PeriodicItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private final OnItemClickListener onItemClickListener;

    public TextView elementNumber, elementShortName, elementName;
    public View bg;
    public RelativeLayout main;

    public PeriodicItemViewHolder(View itemView, OnItemClickListener onItemClickListener) {

        super(itemView);
        this.onItemClickListener = onItemClickListener;

        elementNumber = (TextView) itemView.findViewById(R.id.element_number);
        elementShortName = (TextView) itemView.findViewById(R.id.element_short_name);
        elementName = (TextView) itemView.findViewById(R.id.element_name);
        bg = itemView.findViewById(R.id.bg);
        main = (RelativeLayout) itemView;

        main.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        this.onItemClickListener.onClick(v, getAdapterPosition());
    }
}
