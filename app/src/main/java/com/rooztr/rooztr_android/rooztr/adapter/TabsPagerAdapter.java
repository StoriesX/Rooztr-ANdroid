package com.rooztr.rooztr_android.rooztr.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.rooztr.rooztr_android.ContactFragment;
import com.rooztr.rooztr_android.RequestFragment;
import com.rooztr.rooztr_android.WaitingFragment;

/**
 * Created by anandsuresh on 8/5/16.
 */
public class TabsPagerAdapter extends FragmentPagerAdapter {

    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new ContactFragment();
            case 1:
                return new RequestFragment();
            case 2:
                return new WaitingFragment();
        }
        return null;
    }

    @Override
    public int getCount() {

        return 0;
    }
}
