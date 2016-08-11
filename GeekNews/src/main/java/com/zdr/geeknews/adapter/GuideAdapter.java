package com.zdr.geeknews.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by zdr on 16-8-3.
 */
public class GuideAdapter extends PagerAdapter {
    List<View> mData;

    public GuideAdapter(List<View> mData) {
        this.mData = mData;
    }

    @Override
    public int getCount() {
        return this.mData.size();
    }

    /**
     * 把数据添加到ViewPage中
     * @param container
     * @param position
     * @return
     */
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
}
