package com.zdr.geeknews.fragmentdeom;


import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.zdr.geeknews.R;
import com.zdr.geeknews.adapter.GridAdapter;
import com.zdr.geeknews.adapter.HomeFragmengAdapter;
import com.zdr.geeknews.entity.NewsType;

import java.util.ArrayList;
import java.util.List;

import dao.NewsTypeDao;
import utils.DataManager;
import utils.FragmentItemFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment{

    List<Fragment> mData;
    NewsTypeDao typeDao;
    TabLayout tl;
    ImageView ivClose;
    ScrollView llShow;
    List<NewsType> selectedNews;
    List<NewsType> unSelectedNews;
    GridAdapter selectedAdapter;
    GridAdapter unSelectedAdapter;
    GridView selected;
    GridView unSelected;
    TextView edit;
    ViewPager vp;
    HomeFragmengAdapter vpAdapter;
    Toolbar toolbar;
    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        tl = (TabLayout) v.findViewById(R.id.tabs);
        vp = (ViewPager) v.findViewById(R.id.vp_home);
        ivClose = (ImageView) v.findViewById(R.id.iv_del);
        llShow = (ScrollView) v.findViewById(R.id.ll_shwoType);
        selected = (GridView) v.findViewById(R.id.gv_type);
        unSelected = (GridView) v.findViewById(R.id.gv_type2);
        edit = (TextView) v.findViewById(R.id.tv_edit);

        toolbar = (Toolbar) v.findViewById(R.id.tl_main);

        toolbar.setNavigationIcon(R.mipmap.ic_launcher);//设置导航栏图标
        toolbar.setTitle("GeekNews");//设置主标题
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.inflateMenu(R.menu.toolbar_menu);//设置右上角的填充菜单


        mData = new ArrayList<>();

        typeDao = new NewsTypeDao(getActivity());

        selectedNews = typeDao.findTypeByIschecked(1);
        unSelectedNews = typeDao.findTypeByIschecked(0);

        if((selectedNews.size()+unSelectedNews.size())==0){
            DataManager.initData(getActivity());
            selectedNews = typeDao.findTypeByIschecked(1);
            unSelectedNews = typeDao.findTypeByIschecked(0);
        }
        GridView gv = (GridView) v.findViewById(R.id.gv_type);
        selectedAdapter = new GridAdapter(getContext(), selectedNews);
        gv.setAdapter(selectedAdapter);

        GridView gv2 = (GridView) v.findViewById(R.id.gv_type2);
        unSelectedAdapter = new GridAdapter(getContext(),unSelectedNews );
        gv2.setAdapter(unSelectedAdapter);

        initFragment();
        vpAdapter = new HomeFragmengAdapter(getChildFragmentManager(), mData, selectedNews);

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
        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(llShow.getVisibility() == View.GONE){
                    ivClose.setImageResource(R.mipmap.up_ic);
                    llShow.setVisibility(View.VISIBLE);
                    tl.setVisibility(View.INVISIBLE);
                }
                else {
                    ivClose.setImageResource(R.mipmap.type_ic_add);
                    llShow.setVisibility(View.GONE);
                    tl.setVisibility(View.VISIBLE);

                    selectedAdapter.setVisibleDel(false);
                    unSelectedAdapter.setVisibleDel(false);
                    selectedAdapter.notifyDataSetChanged();
                    unSelectedAdapter.notifyDataSetChanged();

                    initFragment();

                    vpAdapter.notifyDataSetChanged();
                    vp.setAdapter(vpAdapter);
                    tl.setupWithViewPager(vp);

                }
            }
        });
        selected.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0){
                    return;
                }
                if(!selectedAdapter.isVisibleDel()){
                    return;
                }
                int result = typeDao.upChecked(selectedNews.get(position).getUrl(), 0);

                Log.e("RESULT",result+":"+selectedNews.get(position).getUrl());

                unSelectedNews.add(selectedNews.remove(position));

                Log.e("SIZE", selectedNews.size()+"");
                Log.e("unSIZE", unSelectedNews.size()+"");

                selectedAdapter.notifyDataSetChanged();
                unSelectedAdapter.notifyDataSetChanged();


            }
        });
        unSelected.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(!selectedAdapter.isVisibleDel()){
                    return;
                }
                int result = typeDao.upChecked(unSelectedNews.get(position).getUrl(), 1);
                Log.e("RESULT",result+":"+unSelectedNews.get(position).getTypeName());

                selectedNews.add(unSelectedNews.remove(position));

                Log.e("SIZE", selectedNews.size()+"");
                Log.e("unSIZE", unSelectedNews.size()+"");

                selectedAdapter.notifyDataSetChanged();
                unSelectedAdapter.notifyDataSetChanged();


            }
        });


        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selectedAdapter.setVisibleDel(!selectedAdapter.isVisibleDel());
                selectedAdapter.notifyDataSetChanged();

                unSelectedAdapter.setVisibleDel(!unSelectedAdapter.isVisibleDel());
                unSelectedAdapter.notifyDataSetChanged();
            }
        });
        return v;
    }
    private void initFragment() {

        mData.clear();
        for (int i = 0; i < selectedNews.size(); i++) {
            Fragment item = FragmentItemFactory.getFragment(selectedNews.get(i).getFragmetType());
            Log.e("API_URL", selectedNews.get(i).getUrl());
            Bundle bundle = new Bundle();
            bundle.putString("api", selectedNews.get(i).getUrl());

            item.setArguments(bundle);
            mData.add(item);
        }

    }

}
