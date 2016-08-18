package com.zdr.geeknews.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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
 * 新闻适配器。
 * Created by zdr on 16-8-5.
 */
public class NetNewsAdapter extends BaseAdapter {
    private List<NetNews> mData;
    private Context mContext;
    private ImageLoader mImageLoader;
    public NetNewsAdapter(List<NetNews> mData, Context mContext) {
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
    public View getView(int position, View convertView, ViewGroup parent) {
        NetNews netNews = mData.get(position);
        LayoutInflater inflater = LayoutInflater.from(this.mContext);
        ViewHolder holder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.home_item_layout, null, false);

            holder = new ViewHolder();
            holder.title = (TextView) convertView.findViewById(R.id.tv_newsTitle);
            convertView.findViewById(R.id.tv_newsComm).setVisibility(View.GONE);
            holder.description = (TextView) convertView.findViewById(R.id.tv_newsSrc);
            holder.ctime = (TextView) convertView.findViewById(R.id.tv_newsTime);
            holder.picUrl = (ImageView) convertView.findViewById(R.id.iv_newsImg);

            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.title.setText(netNews.getTitle());

        holder.description.setText(netNews.getDescription());
        holder.ctime.setText(netNews.getCtime());

        Glide.with(mContext).load(mData.get(position).getPicUrl())
                .placeholder(R.mipmap.pic_load_failed)
                .error(R.mipmap.pic_load_failed)
                .crossFade(2000).into(holder.picUrl)
        ;
        return convertView;
    }

    private class ViewHolder {
        TextView ctime;
        TextView title;
        TextView description;
        ImageView picUrl;

    }
}
