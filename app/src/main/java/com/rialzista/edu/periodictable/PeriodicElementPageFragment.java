package com.rialzista.edu.periodictable;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rialzista.edu.periodictable.Model.Objects.PeriodicElement;

public class PeriodicElementPageFragment extends Fragment {

    public static String ELEMENT_TAG = "ELEMENT_TAG";
    public static String IS_VIEW_SELECTED_TAG = "IS_VIEW_SELECTED_TAG";

    private TextView mElementNumber, mElementShortName, mElementName;
    private View mBg;

    private PeriodicElement mElement;
    private boolean mIsViewSelected = false;
    private Context mCtx;

    public static PeriodicElementPageFragment create(PeriodicElement element, boolean isViewSelected) {
        PeriodicElementPageFragment fragment = new PeriodicElementPageFragment();

        Bundle bundle = new Bundle();
        bundle.putParcelable(ELEMENT_TAG, element);
        bundle.putBoolean(IS_VIEW_SELECTED_TAG, isViewSelected);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCtx = getActivity();

        if (getArguments() != null) {
            mIsViewSelected = getArguments().getBoolean(IS_VIEW_SELECTED_TAG);
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

        RelativeLayout mClickLayout = (RelativeLayout) rootView.findViewById(R.id.pg_cover);
        mBg = mClickLayout;

        setDataToView();

        mClickLayout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int position = mElement.getNumber();
                mIsViewSelected = true;
                ((DetailActivity) getActivity()).unselectPrevItem();
                ((DetailActivity) getActivity()).mViewPager.setCurrentItem(position - 1, true);
                ((DetailActivity) getActivity()).updateUI(position - 1);
                ((DetailActivity) getActivity()).setPrevSelectedItemPosition(position - 1);
                viewSelected();
            }

        });

        return rootView;
    }

    private void setDataToView() {
        if (mElement != null) {
            mElementNumber.setText(mElement.getNumber() + "");
            mElementShortName.setText(mElement.getSmall());
            mElementName.setText(mElement.getName());

            if (mIsViewSelected)
                viewSelected();
            else
                decorData();
        }
    }

    public void unselectItem() {
        mIsViewSelected = false;
    }

    public void decorData() {
        switch (mElement.getMarker()) {
            case "g1" :
                decorView(R.color.group1_text_color, R.color.group1_text_color, R.color.group1_bg_color);
                break;
            case "g2" :
                decorView(R.color.group2_text_color, R.color.group2_text_color, R.color.group2_bg_color);
                break;
            case "g3" :
                decorView(R.color.group3_text_color, R.color.group3_text_color, R.color.group3_bg_color);
                break;
            case "g4" :
                decorView(R.color.group4_text_color, R.color.group4_text_color, R.color.group4_bg_color);
                break;
            case "g5" :
                decorView(R.color.group5_text_color, R.color.group5_text_color, R.color.group5_bg_color);
                break;
            case "g6" :
                decorView(R.color.group6_text_color, R.color.group6_text_color, R.color.group6_bg_color);
                break;
            case "g7" :
                decorView(R.color.group7_text_color, R.color.group7_text_color, R.color.group7_bg_color);
                break;
            case "g8" :
                decorView(R.color.group8_text_color, R.color.group8_text_color, R.color.group8_bg_color);
                break;
            case "g9" :
                decorView(R.color.group9_text_color, R.color.group9_text_color, R.color.group9_bg_color);
                break;
            case "g10" :
                decorView(R.color.group10_text_color, R.color.group10_text_color, R.color.group10_bg_color);
                break;
            default:
//                    holder.bg.setBackgroundColor(ContextCompat.getColor(this.mCtx, android.R.color.transparent));
                break;
        }
    }

    private void decorView(int textColor, int posColor, int bgColor) {
        mElementShortName.setTextColor(ContextCompat.getColor(this.mCtx, textColor));
        mElementName.setTextColor(ContextCompat.getColor(this.mCtx, textColor));
        mElementNumber.setTextColor(ContextCompat.getColor(this.mCtx, posColor));

        int[] attrs = new int[]{R.attr.selectableItemBackground};
        TypedArray typedArray = this.mCtx.obtainStyledAttributes(attrs);
        int backgroundResource = typedArray.getResourceId(0, 0);
        mBg.setBackgroundResource(backgroundResource);
        typedArray.recycle();
    }

    private void viewSelected() {
        mElementShortName.setTextColor(ContextCompat.getColor(this.mCtx, R.color.text_color_highlights));
        mElementName.setTextColor(ContextCompat.getColor(this.mCtx, R.color.text_color_highlights));
        mElementNumber.setTextColor(ContextCompat.getColor(this.mCtx, R.color.text_color_highlights));

        int colorFrom = android.R.color.transparent;
        int colorTo = Color.WHITE;
        int duration = 200;
        ObjectAnimator.ofObject(mBg, "backgroundColor", new ArgbEvaluator(), colorFrom, colorTo)
                .setDuration(duration)
                .start();

    }
}
