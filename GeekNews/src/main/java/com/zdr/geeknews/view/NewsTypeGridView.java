package com.zdr.geeknews.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 *
 * Created by zdr on 16-8-16.
 */
public class NewsTypeGridView extends GridView{

    public NewsTypeGridView(Context context) {
        super(context);
    }

    public NewsTypeGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NewsTypeGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int size = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, size);
    }
}
