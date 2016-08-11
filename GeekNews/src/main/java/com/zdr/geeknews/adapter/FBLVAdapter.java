package com.zdr.geeknews.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zdr.geeknews.R;
import com.zdr.geeknews.entity.MyFeedBack;

import java.util.List;

/**
 * 这个类的适配器是适配在反馈界面中，’我的意见‘中的ListView
 * Created by zdr on 16-8-3.
 */
public class FBLVAdapter extends BaseAdapter {
    private List<MyFeedBack> mData;
    private Context mContext;

    public FBLVAdapter(List<MyFeedBack> mData, Context mContext) {
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
        if (convertView == null) {

            //布局加载器
            LayoutInflater inflater = LayoutInflater.from(this.mContext);
            convertView = inflater.inflate(R.layout.feedback_vp_my_item, null, false);

            holder = new ViewHolder();

            holder.ivHead =
                    (ImageView) convertView.findViewById(R.id.civ_feedback_head);
            holder.tvContext =
                    (TextView) convertView.findViewById(R.id.tv_my_feedback);
            holder.tvTime =
                    (TextView) convertView.findViewById(R.id.tv_my_feedback_time);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if(mData.get(position).getHeadId()!=0) {
            holder.ivHead.setImageResource(mData.get(position).getHeadId());
        }
        holder.tvContext.setText(mData.get(position).getContext());
        holder.tvTime.setText(mData.get(position).getTime());
        return convertView;
    }

    class ViewHolder {
        ImageView ivHead;
        TextView tvContext;
        TextView tvTime;
    }
}
