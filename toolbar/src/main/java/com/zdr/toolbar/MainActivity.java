package com.zdr.toolbar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.tl_toolBar);
    }

    public void onClick(View view) {
        toolbar.setNavigationIcon(R.mipmap.ic_launcher);
        startActivity(new Intent(this,MainActivity.class));
    }
}
