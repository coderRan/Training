package com.zdr.geeknews.fragmentdeom;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.zdr.geeknews.R;
import com.zdr.geeknews.adapter.CareAdapter;
import com.zdr.geeknews.entity.CareNews;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CareFragment extends Fragment {

    PullToRefreshListView lvCare;
    Toolbar toolbar;
    public CareFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_care, container, false);
        lvCare = (PullToRefreshListView) v.findViewById(R.id.lv_care);
        toolbar = (Toolbar) v.findViewById(R.id.tl_main);

        toolbar.setTitle("我的关注");//设置主标题
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.inflateMenu(R.menu.toolbar_menu);//设置右上角的填充菜单

        CareAdapter adapter = new CareAdapter(initData(),getActivity());
        lvCare.setAdapter(adapter);

        return v;
    }

    private List<CareNews> initData(){
        List<CareNews> data = new ArrayList<>();

        CareNews item = new CareNews();
        item.setIc(R.mipmap.ic_2);
        item.setName("IT技术分享");
        item.setLastMsg("android自定义折线圈");
        item.setTime("2小时前");
        data.add(item);

        item = new CareNews();
        item.setIc(R.mipmap.ic_3);
        item.setName("互联网开发网事");
        item.setLastMsg("互联网人员生存法则");
        item.setTime("1天前");
        data.add(item);

        item = new CareNews();
        item.setIc(R.mipmap.ic_4);
        item.setName("HTML");
        item.setLastMsg("2008人关注");
        item.setTime("3天前");
        data.add(item);

        item = new CareNews();
        item.setIc(R.mipmap.ic_5);
        item.setName("Android");
        item.setLastMsg("你能说android系统不强大吗");
        item.setTime("3天前");
        data.add(item);

        item = new CareNews();
        item.setIc(R.mipmap.ic_6);
        item.setName("程序员");
        item.setLastMsg("程序员的交流地，进来聊聊");
        item.setTime("3天前");
        data.add(item);

        item = new CareNews();
        item.setIc(R.mipmap.ic_7);
        item.setName("电影");
        item.setLastMsg("说一说程序员爱看的电影");
        item.setTime("14天前");
        data.add(item);


        item = new CareNews();
        item.setIc(R.mipmap.ic_3);
        item.setName("互联网开发网事");
        item.setLastMsg("互联网人员生存法则");
        item.setTime("1天前");
        data.add(item);

        item = new CareNews();
        item.setIc(R.mipmap.ic_4);
        item.setName("HTML");
        item.setLastMsg("2008人关注");
        item.setTime("3天前");
        data.add(item);

        item = new CareNews();
        item.setIc(R.mipmap.ic_5);
        item.setName("Android");
        item.setLastMsg("你能说android系统不强大吗");
        item.setTime("3天前");
        data.add(item);

        item = new CareNews();
        item.setIc(R.mipmap.ic_6);
        item.setName("程序员");
        item.setLastMsg("程序员的交流地，进来聊聊");
        item.setTime("3天前");
        data.add(item);

        item = new CareNews();
        item.setIc(R.mipmap.ic_7);
        item.setName("电影");
        item.setLastMsg("说一说程序员爱看的电影");
        item.setTime("14天前");
        data.add(item);
        return data;
    }

}
