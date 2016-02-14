package com.lk.mynews.Fragment;

import android.content.Intent;
import android.os.AsyncTask;
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
import com.lk.mynews.Config.Constant;
import com.lk.mynews.custom.DividerItemDecoration;
import com.lk.mynews.R;
import com.lk.mynews.utils.AnimationControl;
import com.lk.mynews.utils.GetNewsJsonFromUrlUtils;
import com.lk.mynews.utils.JsoupUtils;
import com.lk.mynews.utils.ParseJsonUtils;

import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 体育新闻界面
 * Created by Mr.li on 2016-01-08.
 */
public class SportNewsFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private View rootView;
    private ImageView ivLoading;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;

    private NewsSportRecyclerViewAdapter recyclerViewAdapter;

    private List<SportNewsSlideBean> slide = new ArrayList<>();
    private List<NewsContentBean> news = new ArrayList<>();

    private AnimationControl animationControl;
    private GetNewsJsonFromUrlUtils getNewsJsonFromUrlUtils = new GetNewsJsonFromUrlUtils();
    private ParseJsonUtils parseJsonUtils = new ParseJsonUtils();

    private MyHandler mHandler;
    private AsyncLoadNewsData asyncLoadNewsData;

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
        loadNewsData();

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

    private void loadNewsData() {
        slide.clear();
        news.clear();
        asyncLoadNewsData = new AsyncLoadNewsData();
        asyncLoadNewsData.execute();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
        if (!asyncLoadNewsData.isCancelled()) {
            asyncLoadNewsData.cancel(true);
        }
    }

    @Override
    public void onRefresh() {
        loadNewsData();
    }

    class AsyncLoadNewsData extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] params) {
            try {
                JsoupUtils jsoupUtils = new JsoupUtils();
                slide.addAll(jsoupUtils.parseSlider(getNewsJsonFromUrlUtils.loadSlider()));
                news.addAll(parseJsonUtils.parseNewsContentJson(
                        getNewsJsonFromUrlUtils.loadSportNews()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            mHandler.sendEmptyMessage(1);
            swipeRefreshLayout.setRefreshing(false);
            super.onPostExecute(o);
        }

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
