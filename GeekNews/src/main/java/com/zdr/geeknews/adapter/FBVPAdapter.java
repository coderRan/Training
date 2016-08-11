package com.zdr.geeknews.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * 这个类是反馈意见中的ViewPAge的适配器
 * Created by zdr on 16-8-3.
 */
public class FBVPAdapter extends PagerAdapter {
    private List<View> mData;
    private List<String> mTabsText;
    public FBVPAdapter(List<View> mData,List<String> mTabsText) {
        this.mData = mData;
        this.mTabsText = mTabsText;
    }

    @Override
    public int getCount() {
        return this.mData.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View v = mData.get(position);
        container.addView(v);
        return v;
    }

    /**
     * 销毁看不见的某一项
     * @param container
     * @param position
     * @param object
     */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        container.removeView(mData.get(position));
    }
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTabsText.get(position);
    }
}
