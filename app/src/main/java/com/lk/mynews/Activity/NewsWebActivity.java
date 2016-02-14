package com.lk.mynews.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.lk.mynews.R;

/**
 * Created by Mr.li on 2016/2/14.
 */
public class NewsWebActivity extends BaseActivity implements View.OnClickListener {

    private WebView webView;
    private String link;
    private Toolbar toolbar;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_web);

        getBundles();
        initView();
    }

    private void getBundles() {
        link = getIntent().getStringExtra("link");
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbarNewsContentToolbar);
        toolbar.findViewById(R.id.ivNewsContentToolbarBack).setOnClickListener(this);
        progressBar = (ProgressBar) findViewById(R.id.progressBar_News_Web);
        initWebView();
    }

    private void initWebView() {
        webView = (WebView) findViewById(R.id.wv_News_Web);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);// 启用js
        settings.setJavaScriptCanOpenWindowsAutomatically(true);// js和android交互
        settings.setAllowFileAccess(true); // 允许访问文件
        settings.setAppCacheEnabled(true); // 设置H5的缓存打开,默认关闭
        settings.setUseWideViewPort(true);// 设置webview自适应屏幕大小
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);// 设置，可能的话使所有列的宽度不超过屏幕宽度
        settings.setLoadWithOverviewMode(true);// 设置webview自适应屏幕大小
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                progressBar.setProgress(newProgress);
                if (newProgress == 100) {
                    progressBar.setVisibility(View.GONE);
                }
                super.onProgressChanged(view, newProgress);
            }

        });
        webView.loadUrl(link);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivNewsContentToolbarBack:
                finish();
                break;
        }
    }
}
