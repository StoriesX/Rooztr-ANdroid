package com.rooztr.rooztr_android.rooztr.adapter;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.rooztr.rooztr_android.ContactFragment;
import com.rooztr.rooztr_android.RequestFragment;
import com.rooztr.rooztr_android.WaitingFragment;

import java.util.ArrayList;
import java.util.List;

public class TabsPagerAdapter extends FragmentPagerAdapter {
    public List<String> fragments = new ArrayList<>();

    private Context context;

    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
        fragments.add(ContactFragment.class.getName());
        fragments.add(RequestFragment.class.getName());
        fragments.add(WaitingFragment.class.getName());
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        Log.d("pos",String.valueOf(position));
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

        return 3;
    }
}
