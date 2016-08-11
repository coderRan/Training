package com.zdr.geeknews.fragmentdeom;

import android.support.v4.app.Fragment;

/**
 * Created by zdr on 16-8-8.
 */
public abstract class BaseFragment extends Fragment {
    protected boolean isVisible = false;
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if(isVisibleToUser){
            isVisible = true;
            lazyInitData();
        }else{
            isVisible = false;
        }
    }

    public abstract void lazyInitData();
}
