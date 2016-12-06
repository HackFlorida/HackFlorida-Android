package com.hackflorida.app.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.hackflorida.api.API;
import com.hackflorida.api.model.AnnouncementModel;
import com.hackflorida.app.R;
import com.hackflorida.app.adapter.DynamicRecyclerAdapter;

import java.util.ArrayList;
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


        ArrayList<AnnouncementModel> announcements = new ArrayList<>();
        for(int i = 0; i < 15; ++i)
        announcements.add(new AnnouncementModel("This is a title.", "This is some content", null));

        mRecyclerView.setAdapter(new DynamicRecyclerAdapter<AnnouncementModel, AnnouncementViewHolder>(
                announcements,
                AnnouncementViewHolder.class
        ){

            @Override
            public int getItemViewType(int position) {
                return R.layout.tile_announcement;
            }

            @Override
            protected void populateViewHolder(final AnnouncementViewHolder viewHolder,
                                              final AnnouncementModel model,
                                              final int position) {

                viewHolder.setTitle(model.getTitle());
                viewHolder.setContent(model.getContent());

            }
        });
    }



    public static class AnnouncementViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView content;
        TextView time;

        public AnnouncementViewHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.tv_title);
            content = (TextView) itemView.findViewById(R.id.tv_content);

        }

        public void setTitle(String s) {
            title.setText(s);
        }

        public void setContent(String s) {
            content.setText(s);
        }


    }

}

