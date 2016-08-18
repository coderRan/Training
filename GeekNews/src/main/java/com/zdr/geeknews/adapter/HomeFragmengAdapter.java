package com.zdr.geeknews.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.zdr.geeknews.entity.NewsType;

import java.util.List;

/**
 *
 * Created by zdr on 16-8-1.
 */
public class HomeFragmengAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> mData;
    private List<NewsType> mNewsType;
    public HomeFragmengAdapter(FragmentManager fm, List<Fragment> data) {
        super(fm);
        this.mData = data;
    }

    public HomeFragmengAdapter(FragmentManager fm, List<Fragment> mData, List<NewsType> mNewsType) {
        super(fm);
        this.mData = mData;
        this.mNewsType = mNewsType;
    }

    @Override
    public Fragment getItem(int position) {
        return this.mData.get(position);
    }

    @Override
    public int getCount() {
        return this.mData.size();
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return this.mNewsType.get(position).getTypeName();
    }
}
