package com.example.wojtekkurylo.tourguide;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import static com.example.wojtekkurylo.tourguide.R.layout.activity_main;
import static com.example.wojtekkurylo.tourguide.R.layout.activity_swipe;

/**
 * Created by wojtekkurylo on 06.06.2017.
 */

public class SwipeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the content of the activity to use the activity_main.xml layout file
        setContentView(activity_swipe);

        // logo in App-bar
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        getSupportActionBar().setLogo(R.drawable.logo);
//        getSupportActionBar().setDisplayUseLogoEnabled(true);
//        getSupportActionBar().setDisplayShowTitleEnabled(false);

//        SETTING UP ViewPager - which support screen swipe

        // Find the view pager that will allow the user to swipe between fragments
        ViewPager viewPagerWojtek = (ViewPager) findViewById(R.id.viewPager);

        // Create an adapter that knows which fragment should be shown on each page
        FragmentPageAdapterWojtek adapterWojtek = new FragmentPageAdapterWojtek(getSupportFragmentManager(), this);

        // Set the adapter onto the view pager
        viewPagerWojtek.setAdapter(adapterWojtek);

//        SETTING UP TabLayout - which display current displayed Object - NAME

        // Find the tab layout that shows the tabs
        TabLayout tabLayoutWojtek = (TabLayout) findViewById(R.id.tab_layout);

        // Connect the tab layout with the view pager. This will
        //   1. Update the tab layout when the view pager is swiped
        //   2. Update the view pager when a tab is selected
        //   3. Set the tab layout's tab names with the view pager's adapter's titles
        //      by calling onPageTitle()
        tabLayoutWojtek.setupWithViewPager(viewPagerWojtek);

        // attach icon to each tablayout listed - on top of text displayed by CharSequence method
        for (int i = 0; i < tabLayoutWojtek.getTabCount(); i++) {
            if (i == 0) {
                tabLayoutWojtek.getTabAt(i).setIcon(R.drawable.see);
            } else if (i == 1) {
                tabLayoutWojtek.getTabAt(i).setIcon(R.drawable.eat);
            } else if (i == 2) {
                tabLayoutWojtek.getTabAt(i).setIcon(R.drawable.sleep);
            } else {
                tabLayoutWojtek.getTabAt(i).setIcon(R.drawable.shop);
            }
        }
    }
}
