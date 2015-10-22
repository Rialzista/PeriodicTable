package com.rialzista.edu.periodictable;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.google.gson.Gson;
import com.rialzista.edu.periodictable.Model.Objects.PeriodicElement;
import com.rialzista.edu.periodictable.Model.Objects.PeriodicSection;
import com.rialzista.edu.periodictable.Model.Objects.PeriodicTable;
import com.rialzista.edu.periodictable.Model.Objects.Utils;
import com.rialzista.edu.periodictable.View.CustomViewPager;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public static final String ANDROID_SWITCHER = "android:switcher:";
    public static String SELECTED_ITEM_POSITION = "SELECTED_ITEM_POSITION";

    public CustomViewPager topViewPager;
    public ViewPager detailViewPager;

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

        topViewPager.setAdapter(adapterForTopViewPager);
        topViewPager.setCurrentItem(mIncElementPosition, true);

        detailViewPager.setAdapter(detailViewPagerAdapter);
        detailViewPager.setCurrentItem(mIncElementPosition, true);
        detailViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                topViewPager.setCurrentItem(position, true);
                unSelectPrevItem();
                prevSelectedItemPosition = position;
                ((PeriodicElementPageFragment) adapterForTopViewPager.getItem(position)).selectItem();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    final FragmentPagerAdapter adapterForTopViewPager = new FragmentPagerAdapter(getSupportFragmentManager()) {
        @Override
        public Fragment getItem(int position) {
            Fragment fr  = getSupportFragmentManager().findFragmentByTag(ANDROID_SWITCHER + R.id.viewpager + ":" + position);
            return fr != null ? fr : PeriodicElementPageFragment.create(ds.get(position), position == prevSelectedItemPosition);
        }

        @Override
        public int getCount() {
            return ds.size();
        }
    };

    final FragmentPagerAdapter detailViewPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
        @Override
        public Fragment getItem(int position) {
            Fragment fr  = getSupportFragmentManager().findFragmentByTag(ANDROID_SWITCHER + R.id.detail_viewpager + ":" + position);
            return fr != null ? fr : PeriodicElementDetailPageFragment.create(ds.get(position));
        }

        @Override
        public int getCount() {
            return ds.size();
        }
    };

    private void initVars() {
        topViewPager = (CustomViewPager) findViewById(R.id.viewpager);
        detailViewPager = (ViewPager) findViewById(R.id.detail_viewpager);
    }

    public void updateUI(int position) {
        detailViewPager.setCurrentItem(position, true);
    }

    public void setPrevSelectedItemPosition(int position) {
        prevSelectedItemPosition = position;
    }

    public void unSelectPrevItem() {
        ((PeriodicElementPageFragment) adapterForTopViewPager.getItem(prevSelectedItemPosition)).unSelectItem();
        ((PeriodicElementPageFragment) adapterForTopViewPager.getItem(prevSelectedItemPosition)).decorData();
    }

}