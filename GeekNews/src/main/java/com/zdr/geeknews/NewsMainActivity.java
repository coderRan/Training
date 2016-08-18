package com.zdr.geeknews;


import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.zdr.geeknews.adapter.HomeFragmengAdapter;
import com.zdr.geeknews.fragmentdeom.CareFragment;
import com.zdr.geeknews.fragmentdeom.HomeListViewFragment;
import com.zdr.geeknews.fragmentdeom.HomeFragment;
import com.zdr.geeknews.fragmentdeom.MineFragment;
import com.zdr.geeknews.fragmentdeom.TabsFragment;
import com.zdr.geeknews.fragmentdeom.VideoFragment;

import java.util.ArrayList;
import java.util.List;

public class NewsMainActivity extends AppCompatActivity {
    private List<Fragment> mData;
    TabsFragment fmTabs;
    ViewPager vp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_mian);
        vp = (ViewPager) findViewById(R.id.vp_news_main);


        mData = new ArrayList<>();
        initFragment();
        HomeFragmengAdapter adapter = new HomeFragmengAdapter(getSupportFragmentManager(), mData);

        vp.setAdapter(adapter);

        fmTabs = (TabsFragment) getSupportFragmentManager().findFragmentById(R.id.fm_tabs);

        fmTabs.setOnTabSelectChangeListener(new TabsFragment.OnTabSelectChangeListener() {
            @Override
            public void tabChange(int position) {
                vp.setCurrentItem(position);
            }
        });
        //改变vp，修改rg
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                fmTabs.setRgTabs(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initFragment() {
        HomeFragment home = new HomeFragment();

        VideoFragment video = new VideoFragment();

        CareFragment care = new CareFragment();

        MineFragment mine = new MineFragment();

        mData.add(home);
        mData.add(video);
        mData.add(care);
        mData.add(mine);

    }
}
