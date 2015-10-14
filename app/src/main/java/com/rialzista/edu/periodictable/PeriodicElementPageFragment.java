package com.rialzista.edu.periodictable;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rialzista.edu.periodictable.Model.Objects.PeriodicElement;

public class PeriodicElementPageFragment extends Fragment {

    public static String ELEMENT_TAG = "ELEMENT_TAG";

    private TextView mElementNumber, mElementShortName, mElementName;
    private View mBg;

    private PeriodicElement mElement;
    private Context mCtx;

    public static PeriodicElementPageFragment create(PeriodicElement element) {
        PeriodicElementPageFragment fragment = new PeriodicElementPageFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ELEMENT_TAG, element);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCtx = getActivity();

        if (getArguments() != null) {
            mElement = getArguments().getParcelable(ELEMENT_TAG);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.item_viewpage, container, false);

        mElementNumber = (TextView) rootView.findViewById(R.id.element_number);
        mElementShortName = (TextView) rootView.findViewById(R.id.element_short_name);
        mElementName = (TextView) rootView.findViewById(R.id.element_name);
        mBg = rootView.findViewById(R.id.bg);
        RelativeLayout mClickLayout = (RelativeLayout) rootView.findViewById(R.id.pg_cover);

        setDataToView();

        mClickLayout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int position = mElement.getNumber();
                ((DetailActivity) getActivity()).mViewPager.setCurrentItem(position -1, true);
            }

        });

        return rootView;
    }

    private void setDataToView() {
        if (mElement != null) {
            mElementNumber.setText(mElement.getNumber() +"");
            mElementShortName.setText(mElement.getSmall());
            mElementName.setText(mElement.getName());

            decorData();
        }
    }

    private void decorData() {
        switch (mElement.getMarker()) {
            case "g1" :
                decorViewHolder(R.color.group1_text_color, R.color.group1_text_color, R.color.group1_bg_color);
                break;
            case "g2" :
                decorViewHolder(R.color.group2_text_color, R.color.group2_text_color, R.color.group2_bg_color);
                break;
            case "g3" :
                decorViewHolder(R.color.group3_text_color, R.color.group3_text_color, R.color.group3_bg_color);
                break;
            case "g4" :
                decorViewHolder(R.color.group4_text_color, R.color.group4_text_color, R.color.group4_bg_color);
                break;
            case "g5" :
                decorViewHolder( R.color.group5_text_color, R.color.group5_text_color, R.color.group5_bg_color);
                break;
            case "g6" :
                decorViewHolder(R.color.group6_text_color, R.color.group6_text_color, R.color.group6_bg_color);
                break;
            case "g7" :
                decorViewHolder(R.color.group7_text_color, R.color.group7_text_color, R.color.group7_bg_color);
                break;
            case "g8" :
                decorViewHolder(R.color.group8_text_color, R.color.group8_text_color, R.color.group8_bg_color);
                break;
            case "g9" :
                decorViewHolder(R.color.group9_text_color, R.color.group9_text_color, R.color.group9_bg_color);
                break;
            case "g10" :
                decorViewHolder(R.color.group10_text_color, R.color.group10_text_color, R.color.group10_bg_color);
                break;
            default:
//                    holder.bg.setBackgroundColor(ContextCompat.getColor(this.mCtx, android.R.color.transparent));
                break;
        }
    }

    private void decorViewHolder(int textColor, int posColor, int bgColor) {
        mElementShortName.setTextColor(ContextCompat.getColor(this.mCtx, textColor));
        mElementName.setTextColor(ContextCompat.getColor(this.mCtx, textColor));
        mElementNumber.setTextColor(ContextCompat.getColor(this.mCtx, posColor));
    }
}
