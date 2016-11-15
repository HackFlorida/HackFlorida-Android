package com.hackflorida.app.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.hackflorida.sdk.API;
import com.hackflorida.sdk.model.AnnouncementModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AnnouncementFragment extends RecyclerFragment {


    public AnnouncementFragment() {
        // Required empty public constructor
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        API api = new API();
        api.getAnnouncements(new API.APICallback<AnnouncementModel>() {
            @Override
            public void onDataReady(List<AnnouncementModel> dataSet) {

            }
        });
    }

}
