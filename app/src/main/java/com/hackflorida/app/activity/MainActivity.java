package com.hackflorida.app.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.hackflorida.api.API;
import com.hackflorida.app.R;

public class MainActivity extends AppCompatActivity {

    AHBottomNavigation mBottomNavigation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        configureBottomNavigation();

        final Button button = (Button) findViewById(R.id.test_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                API api = new API();
                api.getTest(new API.TestCallback() {
                    @Override
                    public void onDataReady(String dummy) {
                        button.setText(dummy);
                    }
                });
            }
        });
    }


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

        // Set background color
//        mBottomNavigation.setDefaultBackgroundColor(Color.parseColor("#FEFEFE"));

        // Disable the translation inside the CoordinatorLayout
//        mBottomNavigation.setBehaviorTranslationEnabled(false);

        // Enable the translation of the FloatingActionButton
//        mBottomNavigation.manageFloatingActionButtonBehavior(floatingActionButton);

        // Change colors
//        mBottomNavigation.setAccentColor(Color.parseColor("#F63D2B"));
//        mBottomNavigation.setInactiveColor(Color.parseColor("#747474"));


        // Force to tint the drawable (useful for font with icon for example)
        mBottomNavigation.setForceTint(true);

        // Display color under navigation bar (API 21+)
        // Don't forget these lines in your style-v21
        // <item name="android:windowTranslucentNavigation">true</item>
        // <item name="android:fitsSystemWindows">true</item>
        mBottomNavigation.setTranslucentNavigationEnabled(true);

        // Manage titles
        mBottomNavigation.setTitleState(AHBottomNavigation.TitleState.SHOW_WHEN_ACTIVE);
//        mBottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);
//        mBottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_HIDE);

        // Use colored navigation with circle reveal effect
        mBottomNavigation.setColored(true);

        // Set current item programmatically
        mBottomNavigation.setCurrentItem(1);

        // Customize notification (title, background, typeface)
//        mBottomNavigation.setNotificationBackgroundColor(Color.parseColor("#F63D2B"));

        // Set listeners
        mBottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                // Do something cool here...
                return true;
            }
        });

//        mBottomNavigation.setOnNavigationPositionListener(new AHBottomNavigation.OnNavigationPositionListener() {
//            @Override public void onPositionChange(int y) {
//                // Manage the new y position
//            }
//        });

    }
}
