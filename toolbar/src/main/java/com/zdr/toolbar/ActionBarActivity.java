package com.zdr.toolbar;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class ActionBarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_bar);

        ActionBar actionBar = getSupportActionBar();

        //actionBar.hide(); //隐藏
        actionBar.show();//显示

        //显示返回指示器
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.mipmap.ic_launcher);
        //显示一个logo
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setLogo(R.mipmap.ic_launcher);

        //第二个区
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle("标题");
        actionBar.setSubtitle("子标题");

        //


    }

    /**
     * 处理返回按钮的事件
     *
     * @param item 返回指示器的id
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        //处理搜索按钮
        if(item.getItemId() == R.id.mSearch){

        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * 在actionbar中添加菜单
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_meun, menu);
        return true;
    }
}
