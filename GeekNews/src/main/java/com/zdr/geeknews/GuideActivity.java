package com.zdr.geeknews;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.zdr.geeknews.adapter.GuideAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 引导界面，跳转到NewsMainActivity
 */
public class GuideActivity extends AppCompatActivity {

    private int[] imgRes = {
            R.mipmap.guide_1,
            R.drawable.wallpaper_profile,
            R.mipmap.b_newcare_tabbar_press,
    };
    private List<View> mData;
    RadioGroup rg;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        ViewPager vp = (ViewPager) findViewById(R.id.vp_guide);
        initData();
        GuideAdapter adapter = new GuideAdapter(mData);

        //设置下边的小点
        rg = (RadioGroup) findViewById(R.id.rg_guide);
        for (int i = 0; i < imgRes.length; i++) {
            RadioButton rb = (RadioButton) LayoutInflater.from(this)
                    .inflate(R.layout.guide_radio_gutton,null,false);

            RadioGroup.LayoutParams lp = new RadioGroup.LayoutParams(30,30);
            lp.setMargins(10,10,10,10);
            rb.setLayoutParams(lp);

            rg.addView(rb);

        }
        ((RadioButton) rg.getChildAt(0)).setChecked(true);

        vp.setAdapter(adapter);
        //设置最后一个item的监听，如果是最后一个进行跳转
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {

                ((RadioButton)rg.getChildAt(position)).setChecked(true);

                if (position == mData.size() - 1) {
                    mData.get(position).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Intent intent = new Intent(GuideActivity.this,NewsMainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    });
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    public void initData() {
        mData = new ArrayList<>();
        for (int i = 0; i < imgRes.length; i++) {
            ImageView imageView = (ImageView) LayoutInflater.from(this)
                    .inflate(R.layout.guide_viewpage_item, null, false);

            imageView.setImageResource(imgRes[i]);

            mData.add(imageView);
        }
    }
}
