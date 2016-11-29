package com.hackflorida.api;

import android.util.Log;

import com.hackflorida.api.model.AnnouncementModel;
import com.hackflorida.api.model.BaseModel;
import com.hackflorida.api.model.MapModel;
import com.hackflorida.api.model.SponsorModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;


public class API {

    NetworkClient networkClient = new NetworkClient();

    public void getTest(final TestCallback callback) {
        // url points to Juan's digital ocean server, so long as it's up
        networkClient.get("http://162.243.15.139:5000/hackjson", new NetworkClient.NetworkCallback() {
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
        // networkClient.get("http://hackflorida.io/api/announcements",
        networkClient.get("http://hackflorida.herokuapp.com/api/announcements",
            new NetworkClient.NetworkCallback() {
                @Override
                public void onComplete(String json) {
                    // parse JSON into list of objects based on Announcements data model to pass to functions

                    /*
                    JSON format sample:
                    [
                        {
                            "title":"SuchAnnounce",
                            "content":"Muchcontents",
                            "date":"2016-10-11 12:12:12"
                        },
                        {"
                            title":"SuchAnnounce2",
                            "content":"Muchcontents2",
                            "date":"2016-10-11 15:12:12"
                        }
                    ]
                     */
                    JSONArray announcementsJSON;
                    List<AnnouncementModel> announcements = new ArrayList();

                    // Catch the exception if (for some reason) we don't get JSON
                    try {
                        announcementsJSON = new JSONArray(json);

                        // Make sure we get the right JSON
                        // TODO LATER: Check and add only NEW announcements - may need POST to send what the last announcement we got was or check for 304 Modified in request?
                        for (int i = 0; i < announcementsJSON.length(); i++) {
                            JSONObject temp = announcementsJSON.optJSONObject(i);
                            if (temp != null) {
                                // Pull data members from JSON into AnnouncementModel to add to List
                                Calendar announcementDate = Calendar.getInstance();
                                // date format - yyyy-mm-dd hh:mm:ss
                                DateFormat formatter = new SimpleDateFormat("mm/dd/yyyy hh:mm:ss", Locale.US);
                                try {
                                    announcementDate.setTime(formatter.parse(temp.getString("date")));
                                } catch (ParseException e) {
                                    // handle parse exception - set to now
                                }

                                AnnouncementModel tempAnnouncement = new AnnouncementModel(temp.getString("title"), temp.getString("content"), announcementDate);
                                announcements.add(tempAnnouncement);
                            }
                        }

                        callback.onDataReady(announcements);
                    } catch (JSONException e) {
                        // handle the exception - send error back to the server?
                    }
                }

                @Override
                public void onFailure(Exception e) {

                }
            });
    }

    // TODO: Maps, Schedule, Sponsor, Countdown
    public void getMaps(final APICallback<MapModel> callback) {
        // networkClient.get("http://hackflorida.io/api/announcements",
        networkClient.get("http://hackflorida.herokuapp.com/api/maps",
            new NetworkClient.NetworkCallback() {
                @Override
                public void onComplete(String json) {
                    // parse JSON into list of objects based on Maps data model to pass to functions

                    /*
                    JSON format sample:
                    [
                        {
                            "label":"SuchAnnounce",
                            "url":"Muchcontents",
                            "order":"2016-10-11 12:12:12"
                        }
                    ]
                     */
                    JSONArray mapJSON;
                    List<MapModel> maps = new ArrayList();

                    // Catch the exception if (for some reason) we don't get JSON
                    try {
                        mapJSON = new JSONArray(json);

                        // Make sure we get the right JSON
                        // TODO LATER: Check and add only NEW maps - check for 304 Modified in request?
                        // Cache?
                        for (int i = 0; i < mapJSON.length(); i++) {
                            JSONObject temp = mapJSON.optJSONObject(i);
                            if (temp != null) {
                                // Pull data members from JSON into AnnouncementModel to add to List
                                MapModel tempMap = new MapModel(temp.getString("label"), temp.getString("url"), temp.getInt("order"));
                                maps.add(tempMap);
                            }
                        }

                        callback.onDataReady(maps);
                    } catch (JSONException e) {
                        // handle the exception - send error back to the server?
                    }
                }

                @Override
                public void onFailure(Exception e) {

                }
            });
    }

    public void getSchedule() {

    }

    public void getSponsors() {

    }

    public interface APICallback<T extends BaseModel> {
        void onDataReady(List<T> dataSet);
    }


    public interface TestCallback {
        void onDataReady(String dummy);
    }


}
