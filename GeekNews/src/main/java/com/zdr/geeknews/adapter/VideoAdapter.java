package com.zdr.geeknews.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zdr.geeknews.R;
import com.zdr.geeknews.entity.VideoNews;

import java.util.List;

import circleImageViewlib.CircleImageView;

/**
 * 视频界面中的adapter
 * Created by zdr on 16-8-18.
 */
public class VideoAdapter extends BaseAdapter {
    List<VideoNews> mData;
    Context mContext;

    public VideoAdapter(Context mContext, List<VideoNews> mData) {
        this.mContext = mContext;
        this.mData = mData;
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
        ViewHolder holder;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext)
                    .inflate(R.layout.fragment_video_item,null,false);

            holder.title = (TextView) convertView.findViewById(R.id.tv_video_title);
            holder.video = (ImageView) convertView.findViewById(R.id.iv_video);
            holder.header = (CircleImageView) convertView.findViewById(R.id.civ_video_head);
            holder.name = (TextView) convertView.findViewById(R.id.tv_video_name);
            holder.playCount = (TextView) convertView.findViewById(R.id.tv_video_play_count);
            holder.time = (TextView) convertView.findViewById(R.id.tv_video_time);
            holder.commentCount = (TextView) convertView.findViewById(R.id.tv_video_comment_count);

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        holder.title.setText(mData.get(position).getTitle());
        Glide.with(mContext).load(mData.get(position).getVideoUrl()).placeholder(R.mipmap.pic_load_failed)
                .error(R.mipmap.pic_load_failed)
                .crossFade(200).into(holder.video);
        //holder.header.setImageResource(mData.get(position).getScrIc());
        holder.name.setText(mData.get(position).getSrcName());
        holder.time.setText(mData.get(position).getVideoTime());
        holder.playCount.setText(mData.get(position).getPlayCount());
        holder.commentCount.setText(mData.get(position).getCommentCount());

        return convertView;
    }

    class ViewHolder{
        TextView title;
        ImageView video;
        CircleImageView header;
        TextView name;
        TextView time;
        TextView playCount;
        TextView commentCount;
    }
}
