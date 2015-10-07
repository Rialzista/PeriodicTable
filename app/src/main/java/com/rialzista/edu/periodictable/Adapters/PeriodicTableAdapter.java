package com.rialzista.edu.periodictable.Adapters;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rialzista.edu.periodictable.Model.Objects.PeriodicElement;
import com.rialzista.edu.periodictable.R;

import java.util.List;

public class PeriodicTableAdapter extends RecyclerView.Adapter<PeriodicItemViewHolder> {

    private List<PeriodicElement> mDataSet;
    private Context mCtx;

    public PeriodicTableAdapter(List<PeriodicElement> dataSet, Context ctx) {
        mDataSet = dataSet;
        this.mCtx = ctx;
    }

    @Override
    public PeriodicItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);

        return new PeriodicItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(PeriodicItemViewHolder holder, int position) {
        PeriodicElement element = mDataSet.get(position);


        if (element.getNumber().equals("") && !element.getSmall().equals("")) {
            holder.elementNumber.setText(element.getSmall());
            holder.elementName.setText("");
            holder.elementShortName.setText("");
        } else if (element.getNumber().equals("") && element.getName().equals("")) {
            holder.elementName.setText("");
            holder.elementNumber.setText("");
            holder.elementShortName.setText("");
        }
        else {
            holder.elementName.setText(element.getName());
            holder.elementNumber.setText(element.getNumber());
            holder.elementShortName.setText(element.getSmall());
        }

        switch (element.getMarker()) {
            case "g1" :
                decorViewHolder(holder, R.color.group1_text_color, R.color.group1_text_color, R.color.group1_bg_color);
                break;
            case "g2" :
                decorViewHolder(holder, R.color.group2_text_color, R.color.group2_text_color, R.color.group2_bg_color);
                break;
            case "g3" :
                decorViewHolder(holder, R.color.group3_text_color, R.color.group3_text_color, R.color.group3_bg_color);
                break;
            case "g4" :
                decorViewHolder(holder, R.color.group4_text_color, R.color.group4_text_color, R.color.group4_bg_color);
                break;
            case "g5" :
                decorViewHolder(holder, R.color.group5_text_color, R.color.group5_text_color, R.color.group5_bg_color);
                break;
            case "g6" :
                decorViewHolder(holder, R.color.group6_text_color, R.color.group6_text_color, R.color.group6_bg_color);
                break;
            case "g7" :
                decorViewHolder(holder, R.color.group7_text_color, R.color.group7_text_color, R.color.group7_bg_color);
                break;
            case "g8" :
                decorViewHolder(holder, R.color.group8_text_color, R.color.group8_text_color, R.color.group8_bg_color);
                break;
            case "g9" :
                decorViewHolder(holder, R.color.group9_text_color, R.color.group9_text_color, R.color.group9_bg_color);
                break;
            case "g10" :
                decorViewHolder(holder, R.color.group10_text_color, R.color.group10_text_color, R.color.group10_bg_color);
                break;
            default:
                holder.bg.setBackgroundColor(ContextCompat.getColor(this.mCtx, android.R.color.transparent));
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    private void decorViewHolder(PeriodicItemViewHolder holder, int textColor, int posColor, int bgColor) {
        holder.elementShortName.setTextColor(ContextCompat.getColor(this.mCtx, textColor));
        holder.elementName.setTextColor(ContextCompat.getColor(this.mCtx, textColor));
        holder.elementNumber.setTextColor(ContextCompat.getColor(this.mCtx, posColor));
        holder.bg.setBackgroundColor(ContextCompat.getColor(this.mCtx, bgColor));
    }

}
