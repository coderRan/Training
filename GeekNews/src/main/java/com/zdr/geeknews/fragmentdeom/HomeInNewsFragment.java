package com.zdr.geeknews.fragmentdeom;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.zdr.geeknews.R;
import com.zdr.geeknews.adapter.NewsAdapter;
import com.zdr.geeknews.entity.News;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeInNewsFragment extends Fragment {
    public static final int DATA_LOADED = 1;
    private List<News> mData;
    private NewsAdapter newsAdapter;
    private PullToRefreshListView ptrListView;
    public Handler mHandle = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case DATA_LOADED:
                    mData.clear();
                    mData.addAll((List<News>) msg.obj);
                    newsAdapter.notifyDataSetChanged();
                    ptrListView.onRefreshComplete();
                    break;
            }
        }
    };

    public HomeInNewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home_in_news, container, false);
        ptrListView =
                (PullToRefreshListView) v.findViewById(R.id.ptr_home_news);
        mData = new ArrayList<>();
        newsAdapter = new NewsAdapter(mData, getActivity());
        ptrListView.setAdapter(newsAdapter);
        ptrListView.setMode(PullToRefreshBase.Mode.BOTH);
        ptrListView.setScrollingWhileRefreshingEnabled(false);

        ptrListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                loadLatast();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                loadMore();
            }
        });

        return v;
    }

    /**
     * 加载更多，上拉加载
     */
    private void loadMore() {

    }

    /**
     * 加载最新数据，下拉刷新
     */
    private void loadLatast() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<News> data = initListNews();
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Message msg = mHandle.obtainMessage();
                msg.what = DATA_LOADED;
                msg.obj = data;
                mHandle.sendMessage(msg);


            }
        }).start();
    }

    private List<News> initListNews() {
        List<News> list = new ArrayList<>();
        News n;

        for (int i = 0; i < 20; i++) {
            n = new News();

            n.setNewsTitle("公安部：面对群众围观，不影响执法不得驱逐");
            n.setNewsImg(R.mipmap.ic_launcher);
            n.setNewsComm("45615评论");
            n.setNewsSrc("财经网");
            n.setNewsData("1小时之前");
            list.add(n);
        }

        return list;
    }
}
