package com.example.testactivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class PagerAdapter2  extends FragmentPagerAdapter{


    String title[]={"最新","热门"};
    public PagerAdapter2(FragmentManager fm){
        super(fm);
    }
    @Override
    public Fragment getItem(int position) {
        if(position==0){
            return new ThirdFragment();
        }else {
            return new ForthFragment();
        }
    }

    public CharSequence getPageTitle(int position) {
        return title[position];
    }

    @Override
    public int getCount() {
        return 2;
    }
}
