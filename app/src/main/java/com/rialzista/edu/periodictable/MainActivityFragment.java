package com.rialzista.edu.periodictable;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.rialzista.edu.periodictable.Adapters.PeriodicTableAdapter;
import com.rialzista.edu.periodictable.Model.Objects.PeriodicElement;
import com.rialzista.edu.periodictable.Model.Objects.PeriodicSection;
import com.rialzista.edu.periodictable.Model.Objects.PeriodicTable;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private View mRootView;
    private RecyclerView mRecyclerView;
    private GridLayoutManager mLayoutManager;
    private PeriodicTableAdapter mAdapter;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_main, container, false);

        mRecyclerView = (RecyclerView) mRootView.findViewById(R.id.recycler_view);

        mLayoutManager = new GridLayoutManager(getActivity(), 9, LinearLayoutManager.HORIZONTAL, false);
        this.mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new PeriodicTableAdapter(constructDSRightOrder(), getActivity());
        this.mRecyclerView.setHasFixedSize(true);
        this.mRecyclerView.setAdapter(mAdapter);

        return mRootView;
    }

    private List<PeriodicElement> constructDSRightOrder() {
        Gson gson = new Gson();

        InputStream ins = getResources()
                .openRawResource(
                        getResources()
                                .getIdentifier("periodic_table", "raw", getActivity().getPackageName())
                );

        final BufferedReader reader = new BufferedReader(new InputStreamReader(ins));
        PeriodicTable periodicTable = gson.fromJson(reader, PeriodicTable.class);

        List<PeriodicElement> ds = new ArrayList<>();
        List<PeriodicSection> sections = getSections(periodicTable);

        for (int j = 0; j < 18; j++) {
            PeriodicElement[] elem = new PeriodicElement[9];
            for (int i = 0; i < elem.length; i++)
                elem[i] = new PeriodicElement();

            ds.addAll(Arrays.asList(elem));
        }

        for (int col = 0; col < 9; col++) {
            for (PeriodicElement elm :
                    sections.get(col).getElements()) {
                ds.set(col + (elm.getPosition() * 9), elm);
            }
        }

        return ds;
    }

    private List<PeriodicSection> getSections(PeriodicTable periodicTable) {
        List<PeriodicSection> sections = new ArrayList<>();

        sections.addAll(Arrays.asList(periodicTable.getTable()));
        sections.add(new PeriodicSection(periodicTable.getLanthanoids()));
        sections.add(new PeriodicSection(periodicTable.getActinoids()));

        return sections;
    }
}
