package com.zdr.geeknews.fragmentdeom;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.zdr.geeknews.R;
import com.zdr.geeknews.WebViewItemActivity;
import com.zdr.geeknews.adapter.NetNewsAdapter;
import com.zdr.geeknews.entity.NetNews;

import java.util.ArrayList;
import java.util.List;

import dao.NetNewsDao;
import utils.CallBack;
import utils.Constants;
import utils.ParseJSON;
import utils.UtilsMethod;
import utils.VolleyUtil;

/**
 * A simple {@link Fragment} subclass.
 */
public class NetNewsFragment extends BaseFragment {
    private TextView view;
    private List<NetNews> mData;
    private PullToRefreshListView ptrLv;
    private NetNewsAdapter adapter;
    private View v;
    private int page = 1;
    private NetNewsDao newsDao;
    String url = "http://apis.baidu.com/txapi/keji/keji?num=10&page=";
    RequestQueue queue;
    private boolean isPrepared = false;
    private String newsType;
    //表明是否第一次加载，是否调用lazyInitData方法
    private boolean isFirst = true;

    public NetNewsFragment() {
        // Required empty public constructor

    }

    /**
     * 初始化fragment，实现懒加载，延时加载
     */
    @Override
    public void lazyInitData() {
        if (isPrepared && isVisible && isFirst) {
            mData.clear();
            mData.addAll(newsDao.findNewsByType(newsType));
            adapter.notifyDataSetChanged();
            ptrLv.onRefreshComplete();
            VolleyUtil.get(url + page)
                    .setCallBack(new NetCallback())
                    .buile()
                    .addHeaders("apikey", Constants.API_KEY)
                    .start();
            isFirst = false;
        }

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        queue = Volley.newRequestQueue(getActivity());
        url = getArguments().getString("api");
        newsDao = new NetNewsDao(getActivity());

        newsType = UtilsMethod.getUrlName(url);

    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if(v==null) {
            v = inflater.inflate(R.layout.fragment_net_news, container, false);
            ptrLv = (PullToRefreshListView) v.findViewById(R.id.ptr);
            mData = new ArrayList<>();
            adapter = new NetNewsAdapter(mData, getActivity());
            ptrLv.setAdapter(adapter);
            ptrLv.setMode(PullToRefreshBase.Mode.BOTH);

            ptrLv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
                @Override
                public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                    loadLatest();
                }

                @Override
                public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                    loadMore();
                }
            });
            ptrLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(getActivity(), WebViewItemActivity.class);
                    intent.putExtra("url", mData.get(position-1).getUrl());
                    startActivity(intent);
                }
            });
            isPrepared = true;
            lazyInitData();
        }
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public void loadLatest() {
        page = 1;
        VolleyUtil.get(url + page)
                .setMethod(Request.Method.GET)
                .setCallBack(new NetCallback())
                .buile()
                .addHeaders("apikey", "6b68bca2d6662442fb1103b2427b4fed")
                .start();
    }

    public void loadMore() {
        page++;
        VolleyUtil.get(url + page)
                .setMethod(Request.Method.GET)
                .setCallBack(new NetCallback2())
                .buile()//返回的是NetRequest
                .addHeaders("apikey", Constants.API_KEY)
                .start();

    }


    private class NetCallback implements CallBack {

        @Override
        public void onSuccess(String respone) {
            List<NetNews> temp = ParseJSON.parseJSON(respone,newsType);
            mData.clear();
            mData.addAll(temp);
            adapter.notifyDataSetChanged();
            ptrLv.onRefreshComplete();

            newsDao.delNewsByType(newsType);
            newsDao.addALLNetNews(temp);
        }

        @Override
        public void onError(String err) {
            List<NetNews> list = newsDao.findNewsByType(newsType);
            mData.clear();
            mData.addAll(list);
            adapter.notifyDataSetChanged();
            ptrLv.onRefreshComplete();

            Log.e("VOLLEY_ERR", err.toString());
        }
    }

    private class NetCallback2 implements CallBack {

        @Override
        public void onSuccess(String respone) {

            List<NetNews> temp = ParseJSON.parseJSON(respone,newsType);
            mData.addAll(temp);
            adapter.notifyDataSetChanged();
            ptrLv.onRefreshComplete();

            newsDao.addALLNetNews(temp);
        }

        @Override
        public void onError(String err) {

            adapter.notifyDataSetChanged();
            ptrLv.onRefreshComplete();

            Log.e("VOLLEY_ERR", err.toString());
        }
    }


}
