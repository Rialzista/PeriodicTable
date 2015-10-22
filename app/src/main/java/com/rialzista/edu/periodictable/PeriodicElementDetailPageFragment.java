package com.rialzista.edu.periodictable;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rialzista.edu.periodictable.Model.Objects.PeriodicElement;
import com.squareup.picasso.Picasso;

public class PeriodicElementDetailPageFragment extends Fragment {

    public static String ELEMENT_TAG = "ELEMENT_TAG";
    private Context mCtx;
    private PeriodicElement mElement;

    private TextView mElementNumber;
    private TextView mElementShortName;
    private TextView mElementName;
    private TextView mAtomicWeight;
    private ImageView mPicElement;

    public static PeriodicElementDetailPageFragment create(PeriodicElement element) {
        PeriodicElementDetailPageFragment fragment = new PeriodicElementDetailPageFragment();

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

    private void updateUI() {
        mElementNumber.setText(mElement.getNumber() + "");
        mElementShortName.setText(mElement.getSmall());
        mElementName.setText(mElement.getName());
        mAtomicWeight.setText(mElement.getMolar() + "");
        Picasso.with(mCtx).load(mElement.getImagePath()).fit().centerCrop().into(mPicElement);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.detail_item, container, false);

        mElementNumber = (TextView) rootView.findViewById(R.id.element_number);
        mElementShortName = (TextView) rootView.findViewById(R.id.element_short_name);
        mElementName = (TextView) rootView.findViewById(R.id.element_name);
        mAtomicWeight = (TextView) rootView.findViewById(R.id.atomic_weight);
        TextView mElectionConfig = (TextView) rootView.findViewById(R.id.election_config);
        mPicElement = (ImageView) rootView.findViewById(R.id.pic);

        updateUI();

        return rootView;
    }
}
