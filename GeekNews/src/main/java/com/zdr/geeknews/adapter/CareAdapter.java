package com.zdr.geeknews.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zdr.geeknews.R;
import com.zdr.geeknews.entity.CareNews;

import java.util.List;

/**
 * 话题界面中的ListView的适配器，显示关注内容
 * Created by zdr on 16-8-18.
 */
public class CareAdapter extends BaseAdapter {
    List<CareNews> mData;
    Context mContext;

    public CareAdapter(List<CareNews> mData, Context mContext) {
        this.mData = mData;
        this.mContext = mContext;
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
                    .inflate(R.layout.fragment_care_item,null,false);

            holder.ic = (ImageView) convertView.findViewById(R.id.iv_ic);
            holder.name = (TextView) convertView.findViewById(R.id.tv_name);
            holder.lastMsg = (TextView) convertView.findViewById(R.id.tv_lastMsg);
            holder.time = (TextView) convertView.findViewById(R.id.tv_time);

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        holder.ic.setImageResource(mData.get(position).getIc());
        holder.name.setText(mData.get(position).getName());
        holder.time.setText(mData.get(position).getTime());
        holder.lastMsg.setText(mData.get(position).getLastMsg());

        return convertView;
    }

    class ViewHolder {
        ImageView ic;
        TextView name;
        TextView lastMsg;
        TextView time;
    }
}
