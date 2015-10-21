package com.rialzista.edu.periodictable;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.rialzista.edu.periodictable.Model.Objects.PeriodicElement;
import com.rialzista.edu.periodictable.Model.Objects.PeriodicSection;
import com.rialzista.edu.periodictable.Model.Objects.PeriodicTable;
import com.rialzista.edu.periodictable.Model.Objects.Utils;
import com.rialzista.edu.periodictable.View.CustomViewPager;
import com.squareup.picasso.Picasso;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public static String SELECTED_ITEM_POSITION = "SELECTED_ITEM_POSITION";

    public CustomViewPager mViewPager;

    private TextView mElementNumber, mElementShortName, mElementName, mAtomicWeight, mElectionConfig;
    private ImageView mPicElement;
    private Integer mIncElementPosition;

    final List<PeriodicElement> ds = new ArrayList<>();


    private int prevSelectedItemPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        if (getIntent() != null) {
            mIncElementPosition = getIntent().getIntExtra(SELECTED_ITEM_POSITION, 0);
            prevSelectedItemPosition = mIncElementPosition;
        }

        Gson gson = new Gson();

        InputStream ins = getResources()
                .openRawResource(
                        getResources()
                                .getIdentifier("periodic_table", "raw", this.getPackageName())
                );

        final BufferedReader reader = new BufferedReader(new InputStreamReader(ins));
        PeriodicTable periodicTable = gson.fromJson(reader, PeriodicTable.class);


        List<PeriodicSection> sections = Utils.getSections(periodicTable, true);

        for(PeriodicSection section : sections) {
            ds.addAll(Arrays.asList(section.getElements()));
        }

        initVars();
        updateUI(mIncElementPosition);

        mViewPager.setAdapter(adapter);
        mViewPager.setCurrentItem(mIncElementPosition, true);
    }

    final FragmentPagerAdapter adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
        @Override
        public Fragment getItem(int position) {
            Fragment fr  = getSupportFragmentManager().findFragmentByTag("android:switcher:" + R.id.viewpager + ":" + position);
            return fr != null ? fr : PeriodicElementPageFragment.create(ds.get(position), position == prevSelectedItemPosition);
        }

        @Override
        public int getCount() {
            return ds.size();
        }
    };

    private void initVars() {
        mViewPager = (CustomViewPager) findViewById(R.id.viewpager);

        mElementNumber = (TextView) findViewById(R.id.element_number);
        mElementShortName = (TextView) findViewById(R.id.element_short_name);
        mElementName = (TextView) findViewById(R.id.element_name);
        mAtomicWeight = (TextView) findViewById(R.id.atomic_weight);
        mElectionConfig = (TextView) findViewById(R.id.election_config);
        mPicElement = (ImageView) findViewById(R.id.pic);
    }

    public void updateUI(int position) {
        PeriodicElement element = ds.get(position);

        mElementNumber.setText(element.getNumber() + "");
        mElementShortName.setText(element.getSmall());
        mElementName.setText(element.getName());
        mAtomicWeight.setText(element.getMolar() + "");
        Picasso.with(this).load(element.getImagePath()).into(mPicElement);
    }

    public int getPrevSelectedItemPosition() {
        return prevSelectedItemPosition;
    }

    public void setPrevSelectedItemPosition(int position) {
        prevSelectedItemPosition = position;
    }

    public void unselectPrevItem() {
        ((PeriodicElementPageFragment) adapter.getItem(prevSelectedItemPosition)).unselectItem();
        ((PeriodicElementPageFragment) adapter.getItem(prevSelectedItemPosition)).decorData();
    }

/*
    private void animateActivityStart() {
        ViewPropertyAnimator showTitleAnimator = Utils.showViewByScale(mTitleContainer);
        showTitleAnimator.setListener(new CustomAnimatorListener() {

            @Override
            public void onAnimationEnd(Animator animation) {

                super.onAnimationEnd(animation);
                mTitlesContainer.startAnimation(AnimationUtils.loadAnimation(DetailActivity.this, R.anim.alpha_on));
                mTitlesContainer.setVisibility(View.VISIBLE);

                //animate the fab
                Utils.showViewByScale(mFabButton).setDuration(ANIMATION_DURATION_MEDIUM).start();

                //animate the share fab
                Utils.showViewByScale(mFabShareButton)
                        .setDuration(ANIMATION_DURATION_MEDIUM * 2)
                        .start();
                mFabShareButton.animate()
                        .translationX((-1) * Utils.pxFromDp(DetailActivity.this, 58))
                        .setStartDelay(ANIMATION_DURATION_MEDIUM)
                        .setDuration(ANIMATION_DURATION_MEDIUM)
                        .start();

                //animate the download fab
                Utils.showViewByScale(mFabDownloadButton)
                        .setDuration(ANIMATION_DURATION_MEDIUM * 2)
                        .start();
                mFabDownloadButton.animate()
                        .translationX((-1) * Utils.pxFromDp(DetailActivity.this, 108))
                        .setStartDelay(ANIMATION_DURATION_MEDIUM)
                        .setDuration(ANIMATION_DURATION_MEDIUM)
                        .start();
            }
        });

        showTitleAnimator.start();
    }*/

}