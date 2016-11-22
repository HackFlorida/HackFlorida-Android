package com.hackflorida.app.activity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.hackflorida.app.R;
import com.hackflorida.app.fragments.AnnouncementFragment;
import com.hackflorida.app.fragments.MapsFragment;
import com.hackflorida.app.fragments.RecyclerFragment;
import com.hackflorida.app.fragments.ScheduleFragment;
import com.hackflorida.app.fragments.SponsorsFragment;

/**
 *
 */
public class MainActivity extends AppCompatActivity {

    AHBottomNavigation mBottomNavigation;
    RecyclerFragment mActiveFragment;


    DrawerLayout mDrawerLayout;
    AppBarLayout mAppBarLayout;
    CollapsingToolbarLayout mCollapsingToolbar;
    Toolbar mToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mAppBarLayout = (AppBarLayout) findViewById(R.id.appbar_layout);
        mCollapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsingtoolbar_layout);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        // Configure text ui
        final Typeface tf = Typeface.createFromAsset(getAssets(), "last-paradise.ttf");
        mCollapsingToolbar.setCollapsedTitleTypeface(tf);
        mCollapsingToolbar.setExpandedTitleTypeface(tf);

        //
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDrawerLayout.openDrawer(GravityCompat.START, true);
            }
        });



        configureBottomNavigation();


        mBottomNavigation.setCurrentItem(0);
        changeActiveFragment(0);


    }

    /**
     * @param position
     */
    private void changeActiveFragment(int position) {

        RecyclerFragment newFragment = null;
        switch (position) {
            case 0:
                newFragment = new AnnouncementFragment();
                break;
            case 1:
                newFragment = new ScheduleFragment();
                break;
            case 2:
                newFragment = new MapsFragment();
                break;
            case 3:
                newFragment = new SponsorsFragment();
        }

        if (newFragment == null) return;

        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction()
                .replace(R.id.content_anchor, newFragment)
                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                .commit();

        mActiveFragment = newFragment;

    }


    /**
     *
     */
    private void configureBottomNavigation() {

        mBottomNavigation = (AHBottomNavigation) findViewById(R.id.bottom_navigation);

        // Create items
        AHBottomNavigationItem item1 = new AHBottomNavigationItem(R.string.bbar_announcements, R.drawable.ic_dashboard_black_24dp, R.color.hf_red);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem(R.string.bbar_schedule, R.drawable.ic_clock_black_24dp, R.color.hf_orange);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem(R.string.bbar_map, R.drawable.ic_map_black_24dp, R.color.hf_blue);
        AHBottomNavigationItem item4 = new AHBottomNavigationItem(R.string.bbar_sponsor, R.drawable.ic_people_black_24dp, R.color.hf_purple);

        // Add items
        mBottomNavigation.addItem(item1);
        mBottomNavigation.addItem(item2);
        mBottomNavigation.addItem(item3);
        mBottomNavigation.addItem(item4);

        // Force to tint the drawable (useful for font with icon for example)
        mBottomNavigation.setForceTint(true);

        // Display color under navigation bar (API 21+)
        mBottomNavigation.setTranslucentNavigationEnabled(true);

        // Manage titles
        mBottomNavigation.setTitleState(AHBottomNavigation.TitleState.SHOW_WHEN_ACTIVE);

        // Use colored navigation with circle reveal effect
        mBottomNavigation.setColored(true);

        // Set listeners
        mBottomNavigation.setOnTabSelectedListener(new BottomNavigationListener());
    }

    /**
     *
     */
    private class BottomNavigationListener implements AHBottomNavigation.OnTabSelectedListener {

        @Override
        public boolean onTabSelected(int position, boolean wasSelected) {
            if (!wasSelected) changeActiveFragment(position);
            else {
                if (mActiveFragment != null) mActiveFragment.performQuickReturn(true);
            }
            return true;
        }

    }
}
