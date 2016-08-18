package com.zdr.geeknews;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.zdr.geeknews.adapter.KeepNewsAdapter;
import com.zdr.geeknews.entity.NetNews;

import java.util.ArrayList;
import java.util.List;

import dao.NetNewsKeepDao;

public class KeepActivity extends AppCompatActivity{
    private PullToRefreshListView ptlKeep;
    List<NetNews> keepNews;
    NetNewsKeepDao keepDao;
    KeepNewsAdapter keepAdapter;
    TextView tvEdit;
    ImageView ivDel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keep);

        ptlKeep = (PullToRefreshListView) findViewById(R.id.ptl_keep);
        tvEdit = (TextView) findViewById(R.id.tv_edit_keep);
        ivDel = (ImageView) findViewById(R.id.iv_keep_del);

        keepDao = new NetNewsKeepDao(this);
        keepNews = new ArrayList<>(keepDao.findNewsByType("keep"));
        keepAdapter = new KeepNewsAdapter(keepNews, this);

        keepAdapter.setDelItem(new KeepNewsAdapter.DelItem() {
            @Override
            public void delItem(int position) {
                keepDao.delNewsById(keepDao.newsAt(keepNews.get(position).getUrl()));
                keepNews.remove(position);
                keepAdapter.notifyDataSetChanged();
            }
        });
        ptlKeep.setAdapter(keepAdapter);
        ptlKeep.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(KeepActivity.this, WebViewItemActivity.class);
                intent.putExtra("NetNews", keepNews.get(position - 1));

                intent.putExtra("isKeep", keepDao.isKeep(keepNews.get(position - 1).getUrl()));
                startActivityForResult(intent,1);

            }
        });
        tvEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(keepAdapter.changeVisible() == View.GONE){
                    tvEdit.setText("编辑");
                }else {
                    tvEdit.setText("取消");
                }
                keepAdapter.notifyDataSetChanged();
            }
        });
        ivDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == 1&& resultCode == 1){
            for (int i = 0; i < keepNews.size(); i++) {
                if (keepNews.get(i).getUrl().equals(data.getStringExtra("url"))) {
                    keepNews.remove(i);
                    keepAdapter.notifyDataSetChanged();
                    break;
                }
            }
        }
    }
}
