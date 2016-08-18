package com.zdr.geeknews.fragmentdeom;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.zdr.geeknews.R;
import com.zdr.geeknews.WebViewItemActivity;
import com.zdr.geeknews.adapter.NetNewsAdapter;
import com.zdr.geeknews.adapter.VideoAdapter;
import com.zdr.geeknews.entity.NetNews;
import com.zdr.geeknews.entity.VideoNews;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;

import dao.NetNewsDao;
import dao.NetNewsKeepDao;
import utils.CallBack;
import utils.Constants;
import utils.ParseJSON;
import utils.UtilsMethod;
import utils.VolleyUtil;

/**
 * 视频界面的Fragment
 * A simple {@link Fragment} subclass.
 */
public class VideoFragment extends BaseFragment {

    Toolbar toolbar;
    PullToRefreshListView videoList;
    List<VideoNews> mData;
    View v;
    VideoAdapter adapter;
    boolean isPrepared;
    boolean isFirst;
    String url = "http://apis.baidu.com/txapi/mvtp/meinv?num=10&page=";
    int page = 1;
    Random rand;
    RequestQueue queue;
    TabLayout tl;

    public VideoFragment() {
        // Required empty public constructor
        rand = new Random();
    }

    /**
     * 初始化fragment，实现懒加载，延时加载
     */
    @Override
    public void lazyInitData() {

        if (isPrepared && isVisible && isFirst) {
            mData.clear();
            adapter.notifyDataSetChanged();
            videoList.onRefreshComplete();
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
        loadLatest();

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (v == null) {
            v = inflater.inflate(R.layout.fragment_video, container, false);

            toolbar = (Toolbar) v.findViewById(R.id.tl_video);

            toolbar.setTitle("视频");//设置主标题
            toolbar.setTitleTextColor(Color.WHITE);
            toolbar.inflateMenu(R.menu.toolbar_menu);//设置右上角的填充菜单

            videoList = (PullToRefreshListView) v.findViewById(R.id.ptl_video);
            mData = new ArrayList<>();
            adapter = new VideoAdapter(getActivity(), mData);

            videoList.setAdapter(adapter);
            videoList.setMode(PullToRefreshBase.Mode.BOTH);

            videoList.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
                @Override
                public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                    loadLatest();
                }

                @Override
                public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                    loadMore();
                }
            });

            isPrepared = true;
            lazyInitData();
        }
        return v;
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
            List<NetNews> temp = ParseJSON.parseJSON(respone, "美女");
            mData.clear();
            for (int i = 0; i < temp.size(); i++) {
                VideoNews vn = new VideoNews();
                vn.setTitle(temp.get(i).getTitle());
                vn.setCommentCount(rand.nextInt(10000) + "次平论");
                vn.setPlayCount(rand.nextInt(10000) + "次播放");

                vn.setVideoUrl(temp.get(i).getPicUrl());
                vn.setSrcName(temp.get(i).getDescription());
                vn.setVideoTime(temp.get(i).getCtime().substring(10));
                mData.add(vn);
            }
            adapter.notifyDataSetChanged();
            videoList.onRefreshComplete();

        }

        @Override
        public void onError(String err) {
            Log.e("VOLLEY_ERR", err);
        }
    }

    private class NetCallback2 implements CallBack {

        @Override
        public void onSuccess(String respone) {

            List<NetNews> temp = ParseJSON.parseJSON(respone, "美女");
            for (int i = 0; i < temp.size(); i++) {
                VideoNews vn = new VideoNews();
                vn.setTitle(temp.get(i).getTitle());
                vn.setCommentCount(rand.nextInt(10000) + "次平论");
                vn.setPlayCount(rand.nextInt(10000) + "次播放");
                vn.setVideoUrl(temp.get(i).getPicUrl());
                vn.setSrcName(temp.get(i).getDescription());
                vn.setVideoTime(temp.get(i).getCtime().substring(10));
                mData.add(vn);
            }
            adapter.notifyDataSetChanged();
            videoList.onRefreshComplete();
        }

        @Override
        public void onError(String err) {
            Log.e("VOLLEY_ERR", err);
        }
    }

}

