package com.zdr.geeknews.fragmentdeom;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.zdr.geeknews.R;
import com.zdr.geeknews.adapter.NewsAdapter;
import com.zdr.geeknews.entity.News;

import java.util.ArrayList;
import java.util.List;

/**
 * 这个类是在HomeFragment内部的fragment，内部包含ListView
 * A simple {@link Fragment} subclass.
 */
public class HomeListViewFragment extends Fragment{

    NewsAdapter adapter;
    ListView listView;
    List<News> mData;
    View v;

    public HomeListViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        v = inflater.inflate(R.layout.fragment_home_listview, container, false);


        listView = (ListView) v.findViewById(R.id.lv);
        mData = new ArrayList<>();

        mData.addAll(initListNews());
        adapter = new NewsAdapter(mData, getActivity());
        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);

        TextView tv_head = (TextView) v.findViewById(R.id.tv_fm_head);
        Bundle bundle = getArguments();
        tv_head.setText(bundle.get("key").toString());

        return v;
    }


    private List<News> initListNews(){
        List<News> list = new ArrayList<>();
        News n = new News();

        n.setNewsTitle("公安部：面对群众围观，不影响执法不得驱逐");
        n.setNewsImg(R.mipmap.ic_launcher);
        n.setNewsComm("45615评论");
        n.setNewsSrc("财经网");
        n.setNewsData("1小时之前");

        list.add(n);
        list.add(n);
        list.add(n);
        list.add(n);
        list.add(n);
        list.add(n);
        list.add(n);
        list.add(n);
        list.add(n);
        list.add(n);
        list.add(n);
        list.add(n);
        list.add(n);

        return list;
    }





}