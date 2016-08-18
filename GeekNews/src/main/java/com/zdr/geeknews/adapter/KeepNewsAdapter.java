package com.zdr.geeknews.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.zdr.geeknews.R;
import com.zdr.geeknews.entity.NetNews;

import java.util.List;

import utils.NetImageCache;

/**
 * 收藏列表的适配器，包含隐藏的删除按钮
 * Created by zdr on 16-8-13.
 */
public class KeepNewsAdapter extends BaseAdapter{
    private List<NetNews> mData;
    private Context mContext;
    private ImageLoader mImageLoader;

    private DelItem delItem;
    private int isVisibleDel = View.GONE;
    public KeepNewsAdapter(List<NetNews> mData, Context mContext) {
        this.mData = mData;
        this.mContext = mContext;
        this.mImageLoader = new ImageLoader(
                Volley.newRequestQueue(mContext),
                new NetImageCache()
        );
    }

    @Override
    public int getCount() {
        return this.mData.size();
    }

    @Override
    public Object getItem(int position) {
        return this.mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        NetNews netNews = mData.get(position);
        LayoutInflater inflater = LayoutInflater.from(this.mContext);
        ViewHolder holder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.keep_news_adapter_item, null, false);

            holder = new ViewHolder();
            holder.title = (TextView) convertView.findViewById(R.id.tv_newsTitle);
            convertView.findViewById(R.id.tv_newsComm).setVisibility(View.GONE);
            holder.description = (TextView) convertView.findViewById(R.id.tv_newsSrc);
            holder.ctime = (TextView) convertView.findViewById(R.id.tv_newsTime);
            holder.picUrl = (ImageView) convertView.findViewById(R.id.iv_newsImg);
            holder.keepDel = (Button) convertView.findViewById(R.id.bt_keep_del);

            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.title.setText(netNews.getTitle());

        holder.description.setText(netNews.getDescription());
        holder.ctime.setText(netNews.getCtime());

        Glide.with(mContext).load(mData.get(position).getPicUrl())
                .crossFade(2000).into(holder.picUrl);

        holder.keepDel.setVisibility(isVisibleDel);
        holder.keepDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delItem.delItem(position);
            }
        });
        return convertView;
    }

    private class ViewHolder {
        TextView ctime;
        TextView title;
        TextView description;
        ImageView picUrl;
        Button keepDel;

    }

    /**
     * 设置删除按钮是否可见
     *
     */
    public int changeVisible(){
        if(isVisibleDel == View.GONE)
            isVisibleDel = View.VISIBLE;
        else
            isVisibleDel = View.GONE;

        return isVisibleDel;
    }

    /**
     * 点击删除按钮后的监听
     */
    public interface DelItem{
         void delItem(int position);
    }

    public void setDelItem(DelItem delItem) {
        this.delItem = delItem;
    }
}
