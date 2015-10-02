package com.rialzista.edu.periodictable.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.rialzista.edu.periodictable.R;

/**
 * Created by Константин on 02.10.2015.
 */
public class PeriodicItemViewHolder extends RecyclerView.ViewHolder {

    public TextView elementNumber, elementShortName, elementName;

    public PeriodicItemViewHolder(View itemView) {
        super(itemView);

        elementNumber = (TextView) itemView.findViewById(R.id.element_number);
        elementShortName = (TextView) itemView.findViewById(R.id.element_short_name);
        elementName = (TextView) itemView.findViewById(R.id.element_name);
    }
}
