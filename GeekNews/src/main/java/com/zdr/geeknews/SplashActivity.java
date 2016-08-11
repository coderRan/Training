package com.zdr.geeknews;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import utils.Constants;

/**
 * 启动界面，跳转到GuideActivity
 */
public class SplashActivity extends AppCompatActivity {
    public Handler mHandle = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash);

        mHandle.postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences sp = getSharedPreferences(Constants.SP_FILE, MODE_PRIVATE);
                Intent intent;
                if(sp.getBoolean("first", true)){
                    intent= new Intent(SplashActivity.this,
                            GuideActivity.class);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putBoolean("first", false);
                    editor.apply();
                }else {
                    intent = new Intent(SplashActivity.this,
                            NewsMainActivity.class);
                }

                startActivity(intent);
                finish();
            }
        }, 1000 * 2);
    }
}
