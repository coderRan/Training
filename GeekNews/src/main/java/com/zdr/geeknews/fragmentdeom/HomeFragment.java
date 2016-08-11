package com.zdr.geeknews.fragmentdeom;


import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zdr.geeknews.R;
import com.zdr.geeknews.adapter.HomeFragmengAdapter;
import com.zdr.geeknews.entity.NewsType;

import java.util.ArrayList;
import java.util.List;

import utils.DataManager;
import utils.FragmentItemFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    List<Fragment> mData;

    TabLayout tl;
    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        tl = (TabLayout) v.findViewById(R.id.tabs);
        ViewPager vp = (ViewPager) v.findViewById(R.id.vp_home);
        mData = new ArrayList<>();
        initFragment();

        HomeFragmengAdapter vpAdapter = new HomeFragmengAdapter(getChildFragmentManager(), mData, DataManager.initData());

        vp.setAdapter(vpAdapter);
        //vp.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tl));
        tl.setupWithViewPager(vp);
        //tl.addTab(tl.newTab().setText("dsada"));
        //设置底部下滑线的颜色
        tl.setSelectedTabIndicatorColor(Color.RED);
        //设置底部下滑线的高度
        tl.setSelectedTabIndicatorHeight(2);
        //设置是否可滑动
        tl.setTabMode(TabLayout.MODE_SCROLLABLE);
        //设置未选中，选中的颜色
        tl.setTabTextColors(Color.BLACK,Color.RED);
        //tl.setScrollPosition(2, (float) 0.5, true);

        tl.setTabGravity(TabLayout.GRAVITY_FILL);
        //设置点击监听后， tl.setupWithViewPager(vp);有些效果会失效，当点击tab时，切换
        //vp需要手动实现，
//
        return v;
    }
    private void initFragment() {
        List<NewsType> types = DataManager.initData();
        for (int i = 0; i < types.size(); i++) {
            Fragment item = FragmentItemFactory.getFragment(types.get(i).getFragmetType());
            Bundle bundle = new Bundle();
            bundle.putString("api",types.get(i).getUri());
            item.setArguments(bundle);
            mData.add(item);
        }

    }
}
