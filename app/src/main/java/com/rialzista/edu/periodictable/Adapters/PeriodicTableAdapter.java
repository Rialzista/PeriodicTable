package com.rialzista.edu.periodictable.Adapters;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rialzista.edu.periodictable.Model.Objects.PeriodicElement;
import com.rialzista.edu.periodictable.OnItemClickListener;
import com.rialzista.edu.periodictable.R;

import java.util.List;

public class PeriodicTableAdapter extends RecyclerView.Adapter<PeriodicItemViewHolder> {

    private List<PeriodicElement> mDataSet;

    private Context mCtx;

    private OnItemClickListener onItemClickListener;
    private int focusedItem = 0;

    public PeriodicTableAdapter(List<PeriodicElement> dataSet, Context ctx) {
        mDataSet = dataSet;
        this.mCtx = ctx;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public PeriodicItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);

        return new PeriodicItemViewHolder(v, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(PeriodicItemViewHolder holder, int position) {
        PeriodicElement element = mDataSet.get(position);
        boolean clickable = true;

        if (element.getName().equals("") && !element.getSmall().equals("")) {
            holder.elementNumber.setText(element.getSmall());
            holder.elementName.setText("");
            holder.elementShortName.setText("");
            clickable = false;
        } else if (element.getNumber()== -1 && element.getName().equals("")) {
            holder.elementName.setText("");
            holder.elementNumber.setText("");
            holder.elementShortName.setText("");
            clickable = false;
        }
        else {
            holder.elementName.setText(element.getName());
            holder.elementNumber.setText(element.getNumber() +"");
            holder.elementShortName.setText(element.getSmall());
        }

        switch (element.getMarker()) {
            case "g1" :
                decorViewHolder(holder, R.color.group1_text_color, R.color.group1_text_color, R.color.group1_bg_color, clickable);
                break;
            case "g2" :
                decorViewHolder(holder, R.color.group2_text_color, R.color.group2_text_color, R.color.group2_bg_color, clickable);
                break;
            case "g3" :
                decorViewHolder(holder, R.color.group3_text_color, R.color.group3_text_color, R.color.group3_bg_color, clickable);
                break;
            case "g4" :
                decorViewHolder(holder, R.color.group4_text_color, R.color.group4_text_color, R.color.group4_bg_color, clickable);
                break;
            case "g5" :
                decorViewHolder(holder, R.color.group5_text_color, R.color.group5_text_color, R.color.group5_bg_color, clickable);
                break;
            case "g6" :
                decorViewHolder(holder, R.color.group6_text_color, R.color.group6_text_color, R.color.group6_bg_color, clickable);
                break;
            case "g7" :
                decorViewHolder(holder, R.color.group7_text_color, R.color.group7_text_color, R.color.group7_bg_color, clickable);
                break;
            case "g8" :
                decorViewHolder(holder, R.color.group8_text_color, R.color.group8_text_color, R.color.group8_bg_color, clickable);
                break;
            case "g9" :
                decorViewHolder(holder, R.color.group9_text_color, R.color.group9_text_color, R.color.group9_bg_color, clickable);
                break;
            case "g10" :
                decorViewHolder(holder, R.color.group10_text_color, R.color.group10_text_color, R.color.group10_bg_color, clickable);
                break;
            default:
                holder.bg.setBackgroundColor(ContextCompat.getColor(this.mCtx, android.R.color.transparent));
                holder.main.setClickable(false);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    @Override
    public void onAttachedToRecyclerView(final RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

        recyclerView.setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                RecyclerView.LayoutManager lm = recyclerView.getLayoutManager();

                // Return false if scrolled to the bounds and allow focus to move off the list
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_DPAD_DOWN) {
                        return tryMoveSelection(lm, 1);
                    } else if (keyCode == KeyEvent.KEYCODE_DPAD_UP) {
                        return tryMoveSelection(lm, -1);
                    }
                }

                return false;
            }
        });
    }

    private boolean tryMoveSelection(RecyclerView.LayoutManager lm, int direction) {
        int tryFocusItem = focusedItem + direction;

        // If still within valid bounds, move the selection, notify to redraw, and scroll
        if (tryFocusItem >= 0 && tryFocusItem < getItemCount()) {
            notifyItemChanged(focusedItem);
            focusedItem = tryFocusItem;
            notifyItemChanged(focusedItem);
            lm.scrollToPosition(focusedItem);
            return true;
        }

        return false;
    }

    private void decorViewHolder(PeriodicItemViewHolder holder, int textColor, int posColor, int bgColor, boolean clickable) {
        holder.elementShortName.setTextColor(ContextCompat.getColor(this.mCtx, textColor));
        holder.elementName.setTextColor(ContextCompat.getColor(this.mCtx, textColor));
        holder.elementNumber.setTextColor(ContextCompat.getColor(this.mCtx, posColor));
        holder.bg.setBackgroundColor(ContextCompat.getColor(this.mCtx, bgColor));
        holder.main.setClickable(clickable);
    }

    public PeriodicElement getItem(int position) {
        return mDataSet.get(position);
    }

}
