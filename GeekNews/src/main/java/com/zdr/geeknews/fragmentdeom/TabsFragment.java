package com.zdr.geeknews.fragmentdeom;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.zdr.geeknews.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TabsFragment extends Fragment {
    OnTabSelectChangeListener onTabSelectChangeListener;
    RadioGroup rgTabs;
    public TabsFragment() {
        // Required empty public constructor
    }

    public void setOnTabSelectChangeListener(OnTabSelectChangeListener onTabSelectChangeListener) {
        this.onTabSelectChangeListener = onTabSelectChangeListener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_tabs, container, false);

        rgTabs = (RadioGroup) v.findViewById(R.id.rg_tabs);

        rgTabs.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(onTabSelectChangeListener!=null){
                    for (int i = 0; i < rgTabs.getChildCount(); i++) {
                        if(rgTabs.getChildAt(i).getId() == checkedId){
                            onTabSelectChangeListener.tabChange(i);
                            break;
                        }
                    }
                }
            }

        });

        return v;
    }

    public interface OnTabSelectChangeListener {
        void tabChange(int position);
    }
    public void setRgTabs(int position){
        rgTabs.check(rgTabs.getChildAt(position).getId());
    }

}
