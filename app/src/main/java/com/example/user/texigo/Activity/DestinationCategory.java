package com.example.user.texigo.Activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.user.texigo.Fragment.ReligionFragment;
import com.example.user.texigo.Fragment.CityFragment;
import com.example.user.texigo.Fragment.HillFragment;
import com.example.user.texigo.Fragment.HistoryFragment;
import com.example.user.texigo.Model.FlightModel;
import com.example.user.texigo.R;

import java.util.List;

public class DestinationCategory extends AppCompatActivity {

    FragmentPagerAdapter adapterViewPager;
    public List<FlightModel> destinationList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination_category);
        if (null != getIntent()) {
            destinationList = getIntent().getParcelableArrayListExtra("DESTINATION_LISTS");
        }
        ViewPager vpPager = (ViewPager) findViewById(R.id.vpPager);
        adapterViewPager = new MyPagerAdapter(getSupportFragmentManager(), destinationList);
        vpPager.setAdapter(adapterViewPager);
        vpPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            // This method will be invoked when a new page becomes selected.
            @Override
            public void onPageSelected(int position) {
//                Toast.makeText(getApplicationContext(),
//                        "Selected page position: " + position, Toast.LENGTH_SHORT).show();
            }

            // This method will be invoked when the current page is scrolled
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // Code goes here
            }

            // Called when the scroll state changes:
            // SCROLL_STATE_IDLE, SCROLL_STATE_DRAGGING, SCROLL_STATE_SETTLING
            @Override
            public void onPageScrollStateChanged(int state) {
                // Code goes here
            }
        });
    }




    public static class MyPagerAdapter extends FragmentPagerAdapter {
        private static int NUM_ITEMS = 4;
        List<FlightModel> destinationList;
        public MyPagerAdapter(FragmentManager fragmentManager, List<FlightModel> destinationList) {
            super(fragmentManager);
            this.destinationList = destinationList;
        }

        // Returns total number of pages
        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        // Returns the fragment to display for that page
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return HillFragment.newInstance(0, "Hills", destinationList);
                case 1:
                    return ReligionFragment.newInstance(1, "Religious", destinationList);
                case 2:
                    return CityFragment.newInstance(2, "Cities", destinationList);
                case 3:
                    return HistoryFragment.newInstance(2, "Historic", destinationList);
                default:
                    return null;
            }
        }

        // Returns the page title for the top indicator
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Hills";
                case 1:
                    return "Religious";
                case 2:
                    return "Cities";
                case 3:
                    return "Historic";
                default:
                    return null;
            }
        }

    }
}
