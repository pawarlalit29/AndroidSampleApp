package com.lalit.retailstore.Adaptor;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.lalit.retailstore.User_Interface.Fragment.Frag_ProductView.Frag_ProductView;


/**
 * Created by Lalit on 17-04-2016.
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    CharSequence Titles[] = {"Electronics", "Furniture"}; // This will Store the Titles of the Tabs which are Going to be passed when ViewPagerAdapter is created


    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public Fragment getItem(int num) {

        return Frag_ProductView.newInstance(num);

    }

    @Override
    public int getCount() {

        return Titles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return Titles[position];
    }
}
