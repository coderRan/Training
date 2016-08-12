package com.zdr.geeknews;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class WebViewItemActivity extends AppCompatActivity {
    WebView webView;
    ProgressBar proBar;

    /**
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_item);

        webView = (WebView) findViewById(R.id.wb_item);
        proBar = (ProgressBar) findViewById(R.id.progressBar);
        proBar.setVisibility(View.GONE);
        webView.setWebViewClient(new WebViewClient(){
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
        //请求相关
        webView.setWebChromeClient(new WebChromeClient(){
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

        String url = intent.getStringExtra("url");
        if(!TextUtils.isEmpty(url))
            webView.loadUrl(url);

    }
}
