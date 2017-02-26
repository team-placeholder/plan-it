package com.example.antoan.planit.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by Antoan on 2/20/2017.
 */

public class TabsNavigationAdapter extends FragmentStatePagerAdapter {
    private List<String> tabsNames;
    private List<Fragment> fragments;
    public TabsNavigationAdapter(FragmentManager fm, List<String> tabsName,List<Fragment> fragments) {
        super(fm);
        this.tabsNames = tabsName;
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabsNames.get(position);
    }
}
