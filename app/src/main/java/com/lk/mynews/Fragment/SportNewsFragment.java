package com.lk.mynews.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lk.mynews.Activity.NewsContentActivity;
import com.lk.mynews.Adapter.NewsSportRecyclerViewAdapter;
import com.lk.mynews.Bean.NewsContentBean;
import com.lk.mynews.Bean.SportNewsSlideBean;
import com.lk.mynews.custom.DividerItemDecoration;
import com.lk.mynews.R;
import com.lk.mynews.utils.AnimationControl;

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
    private ImageView ivLoading;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;

    private NewsSportRecyclerViewAdapter recyclerViewAdapter;

    private List<SportNewsSlideBean> slide = new ArrayList<>();
    private List<NewsContentBean> news = new ArrayList<>();

    private AnimationControl animationControl;


     private MyHandler mHandler;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container
            , Bundle savedInstanceState) {

        //这个判断是因为 如果不是第一次进入这个Fragment的话，就不再进行数据的初始化，
//        而是直接把第一次进入时的rootView先从父View删除，然后再return这个rootView返回
        if (rootView != null) {
            ViewGroup parent = (ViewGroup) rootView.getParent();
            if (parent != null) {
                parent.removeView(rootView);
            }
            return rootView;
        }
        rootView = inflater.inflate(R.layout.fragment_news_sport, container, false);
        mHandler = new MyHandler(SportNewsFragment.this);

        initView();
        showLoadingAnimation();

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

    private void showLoadingAnimation() {
        animationControl = new AnimationControl();
        animationControl.initLoadingAnimation(ivLoading);
        animationControl.showLoadingAnimation();
    }

    private void initView() {
        ivLoading = (ImageView) rootView.findViewById(R.id.ivNewsSportLoadImage);
        initSwipeRefreshLayout();
        initRecyclerView();
    }

    /**
     * 初始化RecyclerView
     */
    private void initRecyclerView() {

        recyclerViewAdapter = new NewsSportRecyclerViewAdapter(news, slide, getContext());
        recyclerView = (RecyclerView) rootView.findViewById(R.id.RecyclerViewNewsSport);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(recyclerViewAdapter);
//        添加分隔线
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity()
                , DividerItemDecoration.VERTICAL_LIST));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerViewAdapter.setOnItemClickListener(new NewsSportRecyclerViewAdapter
                .OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                TextView ttt = (TextView) v.findViewById(R.id.tvNewsSportRvViewHolderTitle);
                Toast.makeText(getActivity(), "Title:" + ttt.getText() + "position:"
                        + position, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), NewsContentActivity.class);

                intent.putExtra("title", news.get(position).getTitle());
                intent.putExtra("time", news.get(position).getTime());
                intent.putExtra("picUrl", news.get(position).getUrl());
                intent.putExtra("url", news.get(position).getUrl());

                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.slide_toleft, R.anim.empty);
            }
        });

    }

    private void initSwipeRefreshLayout() {

        swipeRefreshLayout = (SwipeRefreshLayout) rootView
                .findViewById(R.id.swipeRefreshLayoutNewsSport);

        swipeRefreshLayout.setOnRefreshListener(this);

    }

    /**
     * 加载Slider信息
     */
    private void loadSlider() throws IOException {

        slide.clear();

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

            } else {
                Toast.makeText(getContext(),"数据出问题了", Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(getContext(),"网络出问题了", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(false);
    }

    static class MyHandler extends Handler {

        SportNewsFragment mSportNewsFragment;

        public MyHandler(SportNewsFragment sportNewsFragment) {
            mSportNewsFragment = sportNewsFragment;
        }

        @Override
        public void handleMessage(Message msg) {
            mSportNewsFragment.recyclerViewAdapter.notifyDataSetChanged();
            mSportNewsFragment.animationControl.cancelLoadingAnimation();
        }
    }

}
