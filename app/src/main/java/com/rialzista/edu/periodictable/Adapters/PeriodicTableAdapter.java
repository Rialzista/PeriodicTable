package com.rialzista.edu.periodictable.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rialzista.edu.periodictable.Model.Objects.PeriodicElement;
import com.rialzista.edu.periodictable.R;

import java.util.List;

/**
 * Created by Константин on 02.10.2015.
 */
public class PeriodicTableAdapter extends RecyclerView.Adapter<PeriodicItemViewHolder> {

    private List<PeriodicElement> mDataSet;

    public PeriodicTableAdapter(List<PeriodicElement> dataSet) {
        mDataSet = dataSet;
    }

    @Override
    public PeriodicItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);

        return new PeriodicItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(PeriodicItemViewHolder holder, int position) {
        holder.elementName.setText(mDataSet.get(position).getName());

        if (mDataSet.get(position).getNumber().equals(""))
            holder.elementNumber.setText(mDataSet.get(position).getSmall());
        else {
            holder.elementNumber.setText(mDataSet.get(position).getNumber());
            holder.elementShortName.setText(mDataSet.get(position).getSmall());
        }
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

}
