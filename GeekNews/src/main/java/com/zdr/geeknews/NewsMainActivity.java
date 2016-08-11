package com.zdr.geeknews;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.zdr.geeknews.adapter.HomeFragmengAdapter;
import com.zdr.geeknews.fragmentdeom.HomeListViewFragment;
import com.zdr.geeknews.fragmentdeom.HomeFragment;
import com.zdr.geeknews.fragmentdeom.MineFragment;
import com.zdr.geeknews.fragmentdeom.TabsFragment;

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
        Bundle bundle = new Bundle();
        bundle.putString("key", "首页");
        home.setArguments(bundle);

        HomeListViewFragment video = new HomeListViewFragment();
        bundle = new Bundle();
        bundle.putString("key", "视频");
        video.setArguments(bundle);

        HomeListViewFragment care = new HomeListViewFragment();
        bundle = new Bundle();
        bundle.putString("key", "关心");
        care.setArguments(bundle);

        MineFragment mine = new MineFragment();
        bundle = new Bundle();
        bundle.putString("key", "我的");
        mine.setArguments(bundle);

        mData.add(home);
        mData.add(video);
        mData.add(care);
        mData.add(mine);

    }
}
