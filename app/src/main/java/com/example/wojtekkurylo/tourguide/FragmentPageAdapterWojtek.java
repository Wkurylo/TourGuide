package com.example.wojtekkurylo.tourguide;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentPageAdapterWojtek extends FragmentPagerAdapter {

    private final int PAGE_COUNT = 4;
    /*
     * Context of the app
     */
    private Context mContext;

    /**
     * Create a new {@link FragmentPageAdapterWojtek} object.
     *
     * @param context  is the context of the app
     * @param fmWojtek is the fragment manager that will keep each fragment's state in the adapter
     *                 across swipes.
     */

    public FragmentPageAdapterWojtek(FragmentManager fmWojtek, Context context) {
        super(fmWojtek);
        mContext = context;
    }


    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    //     method to display next (as per index number) Java Fragment Object
    @Override
    public android.support.v4.app.Fragment getItem(int position) {
        if (position == 0) {
            return new SeeFragment();
        } else if (position == 1) {
            return new EatFragment();
        } else if (position == 2) {
            return new SleepFragment();
        } else {
            return new ShopFragment();
        }
    }

    // method to display next (as per index number) Java Fragment Object - NAME


    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return mContext.getString(R.string.category_see);
        } else if (position == 1) {
            return mContext.getString(R.string.category_eat);
        } else if (position == 2) {
            return mContext.getString(R.string.category_sleep);
        } else {
            return mContext.getString(R.string.category_shop);
        }
    }

}
