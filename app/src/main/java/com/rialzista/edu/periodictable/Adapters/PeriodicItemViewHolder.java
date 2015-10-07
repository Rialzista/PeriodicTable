package com.rialzista.edu.periodictable.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rialzista.edu.periodictable.R;

public class PeriodicItemViewHolder extends RecyclerView.ViewHolder {

    public TextView elementNumber, elementShortName, elementName;
    public RelativeLayout bg;

    public PeriodicItemViewHolder(View itemView) {
        super(itemView);

        elementNumber = (TextView) itemView.findViewById(R.id.element_number);
        elementShortName = (TextView) itemView.findViewById(R.id.element_short_name);
        elementName = (TextView) itemView.findViewById(R.id.element_name);
        bg = (RelativeLayout) itemView.findViewById(R.id.bg);
    }
}
