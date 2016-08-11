package com.zdr.geeknews.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zdr.geeknews.R;
import com.zdr.geeknews.entity.News;

import java.util.List;

/**
 * Created by zdr on 16-7-27.
 */
public class NewsAdapter extends BaseAdapter {

    List<News> mData;
    Context mContext;

    public NewsAdapter(List<News> mData, Context mContext) {
        this.mData = mData;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return this.mData.size();
    }

    /**
     * 获得点击位置的数据
     *
     * @param position
     * @return
     */
    @Override
    public Object getItem(int position) {
        return this.mData.get(position);
    }

    /**
     * 返回每一行的索引
     *
     * @param position
     * @return
     */
    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        News news = this.mData.get(position);
        ViewHolder viewHolder;
        if (convertView == null) {
            //布局加载器
            LayoutInflater inflater = LayoutInflater.from(this.mContext);
            convertView = inflater.inflate(R.layout.home_item_layout, null, false);

            viewHolder = new ViewHolder();
            viewHolder.title = (TextView) convertView.findViewById(R.id.tv_newsTitle);
            viewHolder.newsComm = (TextView) convertView.findViewById(R.id.tv_newsComm);
            viewHolder.newsSrc = (TextView) convertView.findViewById(R.id.tv_newsSrc);
            viewHolder.newsDate = (TextView) convertView.findViewById(R.id.tv_newsTime);
            viewHolder.newsImg = (ImageView) convertView.findViewById(R.id.iv_newsImg);

            convertView.setTag(viewHolder);

        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.title.setText(news.getNewsTitle());
        viewHolder.newsImg.setImageResource(news.getNewsImg());
        viewHolder.newsComm.setText(news.getNewsComm());
        viewHolder.newsSrc.setText(news.getNewsSrc());
        viewHolder.newsDate.setText(news.getNewsData());

        return convertView;
    }
    class ViewHolder{
        TextView title;
        TextView newsSrc;
        TextView newsComm;
        TextView newsDate;
        ImageView newsImg ;
    }
}