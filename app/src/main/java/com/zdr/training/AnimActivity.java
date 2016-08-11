package com.zdr.training;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class AnimActivity extends AppCompatActivity {
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim);
        imageView = (ImageView) findViewById(R.id.img);

    }

    public void start(View view) {
        //alpha();
        activity();
    }
    public void alpha(){
        Animation animation
                = AnimationUtils.loadAnimation(this, R.anim.alpha);
        imageView.startAnimation(animation);
    }
    public void activity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        //第一个是创建的activity的动画，第二个参数是当前的activity动画
        overridePendingTransition(R.anim.left_in,R.anim.left_out);
    }
}
