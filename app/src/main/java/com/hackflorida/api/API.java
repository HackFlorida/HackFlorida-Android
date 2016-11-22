package com.hackflorida.api;

import android.util.Log;

import com.hackflorida.api.model.AnnouncementModel;
import com.hackflorida.api.model.BaseModel;

import java.util.List;


public class API {

    NetworkClient networkClient = new NetworkClient();

    public void getTest(final TestCallback callback) {
        networkClient.get("testurl", new NetworkClient.NetworkCallback() {
            @Override
            public void onComplete(String json) {
                callback.onDataReady(json);
            }

            @Override
            public void onFailure(Exception e) {
                Log.e(API.class.getName(), e.getLocalizedMessage());
            }
        });
    }

    public void getAnnouncements(final APICallback<AnnouncementModel> callback) {
        networkClient.get("http://hackflorida.io/api/announcements",
                new NetworkClient.NetworkCallback() {
                    @Override
                    public void onComplete(String json) {
                        // TODO parse results
                        callback.onDataReady(null);
                    }

                    @Override
                    public void onFailure(Exception e) {

                    }
                });
    }

    public interface APICallback<T extends BaseModel> {
        void onDataReady(List<T> dataSet);
    }


    public interface TestCallback {
        void onDataReady(String dummy);
    }


}
