package com.zdr.geeknews;

import android.graphics.Color;
import android.os.AsyncTask;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.zdr.geeknews.adapter.FBLVAdapter;
import com.zdr.geeknews.adapter.FBVPAdapter;
import com.zdr.geeknews.entity.MyFeedBack;

import java.util.ArrayList;
import java.util.List;

public class FeedbackActivity extends AppCompatActivity {
    TabLayout tabs;
    ImageView ivBackMine;
    ViewPager vpFeedback;
    List<View> mVPData;
    List<MyFeedBack> myFeedBacks;
    FBLVAdapter lvAdapter;
    PullToRefreshListView prlv;
    List<String> mTabsText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_feedback);

        tabs = (TabLayout) findViewById(R.id.tl_feedback_tabs);
        ivBackMine = (ImageView) findViewById(R.id.iv_backmine);
        vpFeedback = (ViewPager) findViewById(R.id.vp_feedback);

        initViewPage();

        prlv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {

                new GetDataTask().execute();

            }
        });

    }

    public void initViewPage() {
        //ViewPage的数据
        mVPData = new ArrayList<>();
        //布局加载器
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.feedback_vp_my, null, false);
        mVPData.add(view);

        //布局加载器
        inflater = LayoutInflater.from(this);
        View view2 = inflater.inflate(R.layout.feedback_vp_my, null, false);
        mVPData.add(view2);

        //初始化ListView
        prlv = (PullToRefreshListView) view.findViewById(R.id.pf_context);
        ListView lv = prlv.getRefreshableView();
        initListViewData();
        lvAdapter = new FBLVAdapter(myFeedBacks, this);
        lv.setAdapter(lvAdapter);

        //设置tabLayout
        mTabsText = new ArrayList<>();
        mTabsText.add("我的意见");
        mTabsText.add("常见问题");

        tabs.setTabGravity(TabLayout.GRAVITY_FILL);
        tabs.setSelectedTabIndicatorColor(Color.BLUE);
        tabs.setTabTextColors(Color.GRAY, Color.BLUE);

        //设置ViewPage的adapter
        FBVPAdapter vpAdapter = new FBVPAdapter(mVPData, mTabsText);
        vpFeedback.setAdapter(vpAdapter);

        //绑定VewPage与tabLayout
        tabs.setupWithViewPager(vpFeedback);
    }

    //准备Listview的数据
    public void initListViewData() {
        myFeedBacks = new ArrayList<>();

        MyFeedBack fb = new MyFeedBack();
        fb.setContext("欢迎反馈，反馈时上传截图会更便于定位问题，购物相关问题请点击" +
                "右上角常见问题");
        fb.setTime("2016-08-04 24:00");
        myFeedBacks.add(fb);
    }

    private class GetDataTask extends AsyncTask<Void,Void,String[]>{

        @Override
        protected String[] doInBackground(Void... params) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return new String[0];
        }

        @Override
        protected void onPostExecute(String[] strings) {
            MyFeedBack fb = new MyFeedBack();
            fb.setContext("这是新添加的反馈！！！！！！！！！！");
            fb.setTime("3000-13-32 24:00");
            myFeedBacks.add(fb);

            lvAdapter.notifyDataSetChanged();
            prlv.onRefreshComplete();

        }
    }
}
