package com.example.li.fragmenttabhosttest.Activity;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.li.fragmenttabhosttest.R;
import com.example.li.fragmenttabhosttest.utils.AnimationControl;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by Mr.li on 2016/1/13.
 */
public class NewsContentActivity extends AppCompatActivity implements View.OnClickListener, Toolbar.OnMenuItemClickListener {

    private String title = "";
    private String time = "";
    private String picUrl = "";
    private String url = "";
    private String content = "\u3000\u3000";
    private String source = "";

    private int stateBar_height;
    private int screen_width;

    private TextView tvNewsContentTitle, tvNewsContentSource, tvNewsContentTime,
            tvNewsContentContent;
    private ImageView ivLoading;
    private AnimationControl animationControl;

    private PopupWindow popupWindow;

    private Toolbar toolbar;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    setNewsContent();
                    animationControl.cancelLoadingAnimation();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_content);

        getBundles();
        initView();
        showLoadingAnimation();
        loadNews();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler=null;
        if(animationControl.getLoadingAnimator().isRunning()) {
            animationControl.cancelLoadingAnimation();
        }
        animationControl = null;
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.empty, R.anim.slide_toright);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if (hasFocus) {
            Rect rect = new Rect();
            getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
            stateBar_height = rect.top;
            screen_width = rect.right;
        }
        super.onWindowFocusChanged(hasFocus);
    }

    private void loadNews() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Document doc = Jsoup.connect(url).get();
//                    正文内容
                    Elements newsContentElt = doc.getElementsByClass("para");
                    for (Element e : newsContentElt) {
                        content += e.text() + "\n\n" + "\u3000\u3000";
                    }
//                    来源
                    Elements newsSourceElt = doc.getElementsByClass("ori");
                    source = newsSourceElt.text();

                    mHandler.sendEmptyMessage(1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void showLoadingAnimation() {
        animationControl = new AnimationControl();
        animationControl.initLoadingAnimation(ivLoading);
        animationControl.showLoadingAnimation();
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
        popupWindow.setWidth(screen_width / 2);
        popupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(getResources()
                .getDrawable(R.drawable.abc_popup_background_mtrl_mult));
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.update();

    }

    private void showpopupWindowMenu() {
        initPopupWindow();
        popupWindow.showAtLocation(tvNewsContentTitle,
                Gravity.RIGHT | Gravity.TOP, 0,
                stateBar_height);
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
                showpopupWindowMenu();
                break;
        }
        return false;
    }
}
