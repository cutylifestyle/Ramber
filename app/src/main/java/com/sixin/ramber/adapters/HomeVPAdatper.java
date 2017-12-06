package com.sixin.ramber.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author  zhou
 * */

public class HomeVPAdatper extends FragmentStatePagerAdapter {

    private List<Fragment> childFragments = new ArrayList<>();
    private List<String> fragmentTitles = new ArrayList<>();

    public HomeVPAdatper(FragmentManager fm) {
        super(fm);
    }

    public void addContent(Fragment fragment, String title) {

        if(fragment == null || title == null){
            throw new NullPointerException("fragment or title must not be null");
        }

        childFragments.add(fragment);
        fragmentTitles.add(title);
    }

    @Override
    public Fragment getItem(int position) {
        return childFragments.get(position);
    }

    @Override
    public int getCount() {
        return childFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentTitles.get(position);
    }
}
