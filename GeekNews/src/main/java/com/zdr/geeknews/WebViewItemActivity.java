package com.zdr.geeknews;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.zdr.geeknews.entity.NetNews;

import dao.NetNewsKeepDao;

public class WebViewItemActivity extends AppCompatActivity {
    WebView webView;
    ProgressBar proBar;
    ImageView keep;
    NetNewsKeepDao keepDao;
    NetNews nn;
    boolean isKeep;
    Bundle bundle;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_item);

        webView = (WebView) findViewById(R.id.wb_item);
        proBar = (ProgressBar) findViewById(R.id.progressBar);
        keep = (ImageView) findViewById(R.id.iv_keep);
        keepDao = new NetNewsKeepDao(this);

        proBar.setVisibility(View.GONE);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                webView.loadUrl(url);
                Log.e("URL", url);
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                proBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                proBar.setVisibility(View.GONE);
            }
        });
        //与请求相关
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                proBar.setProgress(newProgress);
            }
        });
        webView.getSettings().setDefaultTextEncodingName("utf-8");
        webView.getSettings().setAppCacheEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        Intent intent = getIntent();

        bundle = intent.getExtras();
        nn = (NetNews) bundle.get("NetNews");
        if(nn!=null){
            url = nn.getUrl();
        }

        if (!TextUtils.isEmpty(url))
            webView.loadUrl(url);
        isKeep = bundle.getBoolean("isKeep");
        if (isKeep) {
            keep.setImageResource(R.mipmap.b_film_stars);
        }
        keep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //将收藏的新闻添加到数据库
                if(!isKeep){
                    keepDao.addNetNews(nn);
                    Toast.makeText(WebViewItemActivity.this, "收藏成功", Toast.LENGTH_SHORT).show();
                    keep.setImageResource(R.mipmap.b_film_stars);
                    isKeep = true;

                }else {
                    keepDao.delNewsById(keepDao.getKeepIdByUrl(url));
                    Toast.makeText(WebViewItemActivity.this, "取消收藏", Toast.LENGTH_SHORT).show();
                    keep.setImageResource(R.mipmap.b_film_star_edge);
                    isKeep = false;
                }
            }
        });
    }

    @Override
    public void finish() {
        Intent intent = new Intent();
        intent.putExtra("url", url);
        setResult(1,intent);
        super.finish();
    }
}
