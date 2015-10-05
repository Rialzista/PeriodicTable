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

        if (element.getMarker().equals("g1"))
            holder.elementShortName.setTextColor(ContextCompat.getColor(this.mCtx, R.color.group1_text_color));
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

}
