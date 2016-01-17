package com.example.li.fragmenttabhosttest.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.example.li.fragmenttabhosttest.Activity.NewsContentActivity;
import com.example.li.fragmenttabhosttest.Adapter.NewsSportRecyclerViewAdapter;
import com.example.li.fragmenttabhosttest.Bean.NewsContentBean;
import com.example.li.fragmenttabhosttest.Bean.SportNewsSlideBean;
import com.example.li.fragmenttabhosttest.R;

import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 体育新闻界面
 * Created by Mr.li on 2016-01-08.
 */
public class SportNewsFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    //    private SliderLayout sliderShow;
    private View rootView;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;

    private NewsSportRecyclerViewAdapter recyclerViewAdapter;

    private List<SportNewsSlideBean> slide = new ArrayList<>();
    private List<NewsContentBean> news = new ArrayList<>();


    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            for (SportNewsSlideBean s : slide) {
                System.out.println("link:" + s.getLink());
            }

            recyclerViewAdapter.notifyDataSetChanged();
        }
    };


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        System.out.println("SportNewsFragment:onCreateView");

        rootView = inflater.inflate(R.layout.fragment_news_sport, container, false);


        initSwipeRefreshLayout();

        initRecyclerView();


        new Thread(new Runnable() {
            @Override
            public void run() {

                try {

                    //加载slider信息
                    loadSlider();
                    //加载新闻信息
                    loadNews();

                    mHandler.sendEmptyMessage(1);

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        }).start();


        return rootView;
    }


    /**
     * 初始化RecyclerView
     */
    private void initRecyclerView() {

        recyclerViewAdapter = new NewsSportRecyclerViewAdapter(news, slide, getContext());
        recyclerView = (RecyclerView) rootView.findViewById(R.id.RecyclerViewNewsSport);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(recyclerViewAdapter);

        recyclerViewAdapter.setOnItemClickListener(new NewsSportRecyclerViewAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                TextView ttt = (TextView) v.findViewById(R.id.tvNewsSportRvViewHolderTitle);
                Toast.makeText(getActivity(), "Title:" + ttt.getText() + "position:" + position, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), NewsContentActivity.class);

                intent.putExtra("title", news.get(position).getTitle());
                intent.putExtra("time", news.get(position).getTime());
                intent.putExtra("picUrl", news.get(position).getUrl());
                intent.putExtra("url", news.get(position).getUrl());

                startActivity(intent);
            }
        });

    }

    private void initSwipeRefreshLayout() {

        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.newsSportSwipeRefreshLayout);

        swipeRefreshLayout.setOnRefreshListener(this);

    }

    /**
     * 加载Slider信息
     */
    private void loadSlider() throws IOException {


        Document doc = Jsoup.connect("http://sports.ifeng.com/").get();
        Element slideElt = doc.getElementById("slide");
        Elements slideNewsPicElts = slideElt.getElementsByClass("pic");
        Elements slideNewsTxtElts = slideElt.getElementsByClass("txt");

        for (int i = 0; i < slideNewsPicElts.size(); i++) {
            String slidePicUrl = slideNewsPicElts.get(i).getElementsByTag("img").attr("src");
            String slideLink = slideNewsPicElts.get(i).getElementsByTag("a").attr("href") + "#p=1";
            String slideDesc = slideNewsTxtElts.get(i).text();
            SportNewsSlideBean slideBean = new SportNewsSlideBean(slidePicUrl, slideLink, slideDesc);
            slide.add(slideBean);
        }

    }

    /**
     * （apistore开放接口） 加载新闻，
     */
    private void loadNews() throws IOException {


        String apiUrl = "http://apis.baidu.com/txapi/world/world?&num=11&page=1";

        OkHttpClient okHttpClient = new OkHttpClient();

        Request request = new Request.Builder()
                .url(apiUrl)
                .addHeader("apikey", "ef3ff76a03a6c1b2e24d3a75fed55658")
                .build();

        Response response = okHttpClient.newCall(request).execute();

        if (response.isSuccessful()) {
            String json = response.body().string();

            if (!json.equals("")) {
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    System.out.println(jsonObject.toString());

                    for (int i = 0; i < jsonObject.length() - 2; i++) {
                        JSONObject jsonContent = jsonObject.getJSONObject(String.valueOf(i));
                        news.add(new NewsContentBean(jsonContent.getString("title"),
                                jsonContent.getString("picUrl"),
                                jsonContent.getString("time"),
                                jsonContent.getString("url")));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        }

    }

    @Override
    public void onStop() {

        super.onStop();
    }

    @Override
    public void onRefresh() {

        System.out.println("刷新");

        swipeRefreshLayout.setRefreshing(false);

    }

}
