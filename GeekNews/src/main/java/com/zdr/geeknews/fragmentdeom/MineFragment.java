package com.zdr.geeknews.fragmentdeom;


import android.content.Intent;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.zdr.geeknews.FeedbackActivity;
import com.zdr.geeknews.R;
import com.zdr.geeknews.SettingsActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class MineFragment extends Fragment {

    RelativeLayout bt;
    RelativeLayout login;
    Button btSettings;

    public MineFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_mine, container, false);
        bt = (RelativeLayout) v.findViewById(R.id.rl_feedback);
        login = (RelativeLayout) v.findViewById(R.id.rl_login);
        btSettings = (Button) v.findViewById(R.id.bt_mine_settings);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), FeedbackActivity.class);
                startActivity(intent);
            }
        });
        btSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SettingsActivity.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = LayoutInflater.from(getActivity()).inflate(R.layout.mine_login, null, false);

                final PopupWindow popupWindow = new PopupWindow(
                        view,
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                );
                ImageView close = (ImageView) view.findViewById(R.id.mine_login_close);
                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });
                popupWindow.setFocusable(true);
                popupWindow.setOutsideTouchable(true);
                ColorDrawable colorDrawable = new ColorDrawable(0);
                popupWindow.setBackgroundDrawable(colorDrawable);

                WindowManager wm = (WindowManager) getContext().getSystemService(getContext().WINDOW_SERVICE);

                Display display = wm.getDefaultDisplay();

                Point p = new Point();
                display.getSize(p);
                popupWindow.setAnimationStyle(R.style.popuo_anim);

                popupWindow.showAtLocation(v, Gravity.NO_GRAVITY, 0, p.y - view.getHeight());
            }
        });
        return v;
    }

}
