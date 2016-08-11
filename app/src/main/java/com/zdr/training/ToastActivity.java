package com.zdr.training;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.PopupWindow;
import android.widget.Toast;

import static android.view.Gravity.*;

public class ToastActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toast);
    }

    /**
     * button 的点击事件
     * @param view
     */
    public void shwoPopup(View view) {
        //showToast();
        showDialog();
        //showPopupWidow(view);
//        View v = LayoutInflater.from(this).inflate(R.layout.toast_layout,null,false);
//        final PopupWindow popupWindow = new PopupWindow(
//                v,
//                ViewGroup.LayoutParams.MATCH_PARENT,
//                ViewGroup.LayoutParams.WRAP_CONTENT
//        );
//
//        popupWindow.setFocusable(true);
//        popupWindow.setOutsideTouchable(true);
//        ColorDrawable colorDrawable = new ColorDrawable(0);
//        popupWindow.setBackgroundDrawable(colorDrawable);
//
//        v.findViewById(R.id.tv_show).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                popupWindow.dismiss();
//            }
//        });
//
//        DisplayMetrics metric = new DisplayMetrics();
//        getWindowManager().getDefaultDisplay().getMetrics(metric);
//        int width = metric.widthPixels;     // 屏幕宽度（像素）
//        int height = metric.heightPixels;   // 屏幕高度（像素）
//
//        WindowManager wm = (WindowManager) getSystemService(this.WINDOW_SERVICE);
//
//        Display display = wm.getDefaultDisplay();
//
//
//        popupWindow.setAnimationStyle(R.style.popuo_anim);
//        popupWindow.showAtLocation(view, Gravity.NO_GRAVITY, 0, (display.getHeight() - v.getHeight()));


    }

    public void showToast() {
        Toast t = new Toast(this);
        View v = LayoutInflater.from(this)
                .inflate(R.layout.toast_layout, null, false);

        t.setView(v);
        t.setGravity(TOP, 0, 0);
        t.show();
    }

    public void showDialog() {


        AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
        builder2.setTitle("这是标题")
                .setSingleChoiceItems(R.array.xingzuo2, 2, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(ToastActivity.this, "setSingleChoiceItems", Toast.LENGTH_SHORT).show();
                    }
                }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showToast();
            }
        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(ToastActivity.this, "点击取消", Toast.LENGTH_SHORT).show();
            }
        });
        builder2.create().show();
    }

    public void showDialog3() {
        AlertDialog builder = new AlertDialog.Builder(this).create();
        builder.show();
        builder.setContentView(R.layout.toast_layout);

    }
    public void showPopupWidow(View v){

        View view = LayoutInflater.from(this).inflate(R.layout.toast_layout,null);
        final PopupWindow popupWindow = new PopupWindow(
                view,
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );

        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);

        ColorDrawable colorDrawable = new ColorDrawable(0);
        popupWindow.setBackgroundDrawable(colorDrawable);

        view.findViewById(R.id.tv_show).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        //popupWindow.showAsDropDown(v);
//        int[] location = new int[2];
//        v.getLocationOnScreen(location);
//        Log.e("LOCALTION", location[0] + " " + location[1]);
//        v.getLocationInWindow(location);
//        Log.e("LOCALTION", location[0] + " " + location[1]);
        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        int width = metric.widthPixels;     // 屏幕宽度（像素）
        int height = metric.heightPixels;   // 屏幕高度（像素）
        popupWindow.showAtLocation(v, Gravity.NO_GRAVITY, 0, (height - view.getHeight()));
        Log.e("LOCALTION", view.getHeight()+"");
        popupWindow.setAnimationStyle(R.style.popuo_anim);
    }
}
