package com.hackflorida.api;

import com.hackflorida.api.model.AnnouncementModel;
import com.hackflorida.api.model.BaseModel;

import java.util.List;


public class API {


    public void getAnnouncements(final APICallback<AnnouncementModel> callback) {
        // TODO implement announcement fetching
        callback.onDataReady(null);
    }

    public interface APICallback<T extends BaseModel> {
        void onDataReady(List<T> dataSet);
    }


}
