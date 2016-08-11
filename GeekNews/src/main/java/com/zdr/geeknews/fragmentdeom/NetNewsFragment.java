package com.zdr.geeknews.fragmentdeom;


import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.zdr.geeknews.R;
import com.zdr.geeknews.adapter.NetNewsAdapter;
import com.zdr.geeknews.entity.NetNews;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

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
    String url = "http://apis.baidu.com/txapi/keji/keji?num=10&page=";
    RequestQueue queue;
    private boolean isPrepared = false;

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


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
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
            List<NetNews> temp = ParseJSON.parseJSON(respone);
            mData.clear();
            mData.addAll(temp);
            adapter.notifyDataSetChanged();
            ptrLv.onRefreshComplete();
        }

        @Override
        public void onError(String err) {
            Log.e("VOLLEY_ERR", err.toString());
        }
    }

    private class NetCallback2 implements CallBack {

        @Override
        public void onSuccess(String respone) {
            //缓存到SD卡
            writeSD(respone);

            List<NetNews> temp = ParseJSON.parseJSON(respone);
            mData.addAll(temp);

            adapter.notifyDataSetChanged();
            ptrLv.onRefreshComplete();
        }

        @Override
        public void onError(String err) {
            Log.e("VOLLEY_ERR", err.toString());
        }
    }
    public boolean writeSD(String json){
        if(!UtilsMethod.isGranExternalRW(getActivity())) {
            return false;
        }

        File file = new File(Environment.getExternalStorageDirectory(), Constants.CACHE_FILE_DIR);
        if(!file.exists()){
            file.mkdir();
        }

        File jsonFile = new File(file, getUrlName(url)+".json");
        Log.e("FILE:", getUrlName(url) + ".json");
        OutputStream fos = null;
        try {
            fos = new FileOutputStream(jsonFile);
            fos.write(json.getBytes());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(fos!=null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }

        return true;
    }
    public void readSD(){

    }

    private String getUrlName(String u){

        int start = u.lastIndexOf("/");
        int end = u.lastIndexOf("?");
        return u.substring(start, end);
    }
}
