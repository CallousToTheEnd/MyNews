package com.lk.mynews.Activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.lk.mynews.Config.Constant;
import com.lk.mynews.R;
import com.lk.mynews.utils.AnimationControl;
import com.lk.mynews.utils.JsoupUtils;

import org.jsoup.Jsoup;

/**
 * Created by Mr.li on 2016/1/13.
 */
public class NewsContentActivity extends BaseActivity implements View.OnClickListener,
        Toolbar.OnMenuItemClickListener {

    private String title = "";
    private String time = "";
    private String picUrl = "";
    private String url = "";
    private String content = "\u3000\u3000";
    private String source = "";


    private TextView tvNewsContentTitle, tvNewsContentSource, tvNewsContentTime,
            tvNewsContentContent;
    private ImageView ivLoading;

    private AnimationControl animationControl;
    private JsoupUtils jsoupUtils = new JsoupUtils();;

    private PopupWindow popupWindow;

    public Toolbar toolbar;

    private MyHandler mHandler;
    private AsyncLoadContent asyncLoadContent = new AsyncLoadContent();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_content);

        mHandler = new MyHandler(NewsContentActivity.this);
        getBundles();
        initView();
        showLoadingAnimation();
        loadNews();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
        if (!asyncLoadContent.isCancelled()) {
            asyncLoadContent.cancel(true);
        }
    }

    private void getBundles() {
        this.title = getIntent().getStringExtra("title");
        this.time = getIntent().getStringExtra("time");
        this.picUrl = getIntent().getStringExtra("picUrl");
        this.url = getIntent().getStringExtra("url");
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbarNewsContentToolbar);
        toolbar.inflateMenu(R.menu.menu_news_content);
        toolbar.setOnMenuItemClickListener(this);
        toolbar.findViewById(R.id.ivNewsContentToolbarBack).setOnClickListener(this);
        tvNewsContentTitle = (TextView) findViewById(R.id.tvNewsContentTitle);
        tvNewsContentSource = (TextView) findViewById(R.id.tvNewsContentSource);
        tvNewsContentTime = (TextView) findViewById(R.id.tvNewsContentTime);
        tvNewsContentContent = (TextView) findViewById(R.id.tvNewsContentContent);
        ivLoading = (ImageView) findViewById(R.id.ivNewsContentLoading);
    }

    private void showLoadingAnimation() {
        animationControl = new AnimationControl();
        animationControl.initLoadingAnimation(ivLoading);
        animationControl.showLoadingAnimation();
    }

    private void loadNews() {
        asyncLoadContent.execute(url);
    }

    private void setNewsContent() {
        tvNewsContentTitle.setText(title);
        tvNewsContentTime.setText(time);
        tvNewsContentContent.setText(content);
        tvNewsContentSource.setText(source);
    }

    /**
     * 更多菜单
     */
    private void initPopupWindow() {
        View view = getLayoutInflater().inflate(R.layout.menu_news_content, null);
        view.findViewById(R.id.llNewsContentMenuItemCollect).setOnClickListener(this);
        view.findViewById(R.id.llNewsContentMenuItemScreenshot).setOnClickListener(this);
        view.findViewById(R.id.llNewsContentMenuItemFont).setOnClickListener(this);
        popupWindow = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow.setWidth(Constant.screen_width / 2);
        popupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(getResources()
                .getDrawable(R.drawable.popup_background_mtrl_mult));
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.update();

    }

    private void showPopupWindowMenu() {
        initPopupWindow();
        popupWindow.showAtLocation(tvNewsContentTitle,
                Gravity.RIGHT | Gravity.TOP, 0,
                Constant.app_statebar_height);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.llNewsContentMenuItemCollect:
                Toast.makeText(NewsContentActivity.this, "收藏", Toast.LENGTH_SHORT).show();
                popupWindow.dismiss();
                break;
            case R.id.llNewsContentMenuItemScreenshot:
                Toast.makeText(NewsContentActivity.this, "截图", Toast.LENGTH_SHORT).show();
                popupWindow.dismiss();
                break;
            case R.id.llNewsContentMenuItemFont:
                Toast.makeText(NewsContentActivity.this, "字号大小", Toast.LENGTH_SHORT).show();
                popupWindow.dismiss();
                break;
            case R.id.ivNewsContentToolbarBack:
                NewsContentActivity.this.finish();
                break;
        }
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.newsContentMenuMore:
                showPopupWindowMenu();
                break;
        }
        return false;
    }

    class AsyncLoadContent extends AsyncTask{

        @Override
        protected Object doInBackground(Object[] params) {
            String url = (String) params[0];
            String[] sourceAndContent = jsoupUtils.parseSportNewsSourceAndContent(url);
            content += sourceAndContent[0];
            source = sourceAndContent[1];
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            mHandler.sendEmptyMessage(1);
            super.onPostExecute(o);
        }
    }

    static class MyHandler extends Handler {

        NewsContentActivity mNewsContentActivity;

        public MyHandler(NewsContentActivity newsContentActivity) {
            mNewsContentActivity = newsContentActivity;
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    mNewsContentActivity.setNewsContent();
                    mNewsContentActivity.animationControl.cancelLoadingAnimation();
                    break;
            }
        }
    }

}
