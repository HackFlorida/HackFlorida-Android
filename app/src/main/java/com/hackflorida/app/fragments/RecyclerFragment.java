package com.hackflorida.app.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hackflorida.app.R;

/**
 * Created by andrew on 11/14/16.
 */

public abstract class RecyclerFragment extends Fragment {

    public RecyclerFragment() {
        // Required empty constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recycler, container, false);
    }

    public boolean performQuickReturn(boolean animate) {
        return true;
    }
}
