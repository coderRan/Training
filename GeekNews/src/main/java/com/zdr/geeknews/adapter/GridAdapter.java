package com.zdr.geeknews.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zdr.geeknews.R;
import com.zdr.geeknews.entity.NewsType;

import java.util.List;

import dao.NewsTypeDao;

/**
 *
 * Created by zdr on 16-8-16.
 */
public class GridAdapter extends BaseAdapter {
    List<NewsType> mData;


    boolean isVisibleDel = false;
    Context context;
    NewsTypeDao typeDao;

    public GridAdapter(Context context, List<NewsType> mData) {
        this.context = context;
        this.mData = mData;
        typeDao = new NewsTypeDao(context);
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
            convertView = LayoutInflater.from(context).inflate(R.layout.grid_item, null, false);
            holder = new ViewHolder();
            holder.item = (TextView) convertView.findViewById(R.id.tv_grid_item);
            holder.del = (ImageView) convertView.findViewById(R.id.iv_grid_del);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.item.setText(mData.get(position).getTypeName());


        if (isVisibleDel &&position!=0) {
            holder.del.setVisibility(View.VISIBLE);
        } else {
            holder.del.setVisibility(View.GONE);
        }
        return convertView;
    }

    class ViewHolder {
        TextView item;
        ImageView del;
    }

    public boolean isVisibleDel() {
        return isVisibleDel;
    }

    public void setVisibleDel(boolean visibleDel) {
        isVisibleDel = visibleDel;
    }

}
