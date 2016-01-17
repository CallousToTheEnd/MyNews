package com.example.li.fragmenttabhosttest.Activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.li.fragmenttabhosttest.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by Mr.li on 2016/1/13.
 */
public class NewsContentActivity extends AppCompatActivity {

    private String title = "";
    private String time = "";
    private String picUrl = "";
    private String url = "";
    private String content = "";
    private String source = "";

    private TextView tvNewsContentTitle, tvNewsContentSource, tvNewsContentTime,
            tvNewsContentContent;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    tvNewsContentContent.setText(content);
                    tvNewsContentSource.setText("搜狐实时快报");
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_content);

        getBundles();
        initView();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Document doc = Jsoup.connect(url).get();
                    Elements newsContentElt = doc.getElementsByClass("finCnt");
                    for (Element e : newsContentElt) {
                        Elements elt1 = e.getElementsByTag("p");
                        elt1.remove(0);     //原标题
                        for (Element elt2 : elt1) {
                            content += elt2.text() + "\n" + "\u3000\u3000";
                        }
                    }
                    mHandler.sendEmptyMessage(1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    private void getBundles() {
        this.title = getIntent().getStringExtra("title");
        this.time = getIntent().getStringExtra("time");
        this.picUrl = getIntent().getStringExtra("picUrl");
        this.url = getIntent().getStringExtra("url");
    }

    private void initView() {
        tvNewsContentTitle = (TextView) findViewById(R.id.tvNewsContentTitle);
        tvNewsContentSource = (TextView) findViewById(R.id.tvNewsContentSource);
        tvNewsContentTime = (TextView) findViewById(R.id.tvNewsContentTime);
        tvNewsContentContent = (TextView) findViewById(R.id.tvNewsContentContent);

        tvNewsContentTitle.setText(title);
        tvNewsContentTime.setText(time);

    }

}
