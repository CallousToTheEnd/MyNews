package com.lk.mynews.Fragment;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
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

import com.lk.mynews.Activity.NewsWebActivity;
import com.lk.mynews.Adapter.BaseRecyclerViewAdapter;
import com.lk.mynews.Adapter.NewsSociologyItemAdapter;
import com.lk.mynews.Bean.SociologyJsonBean.Contentlist;
import com.lk.mynews.Bean.SportNewsSlideBean;
import com.lk.mynews.Config.Constant;
import com.lk.mynews.R;
import com.lk.mynews.custom.DividerItemDecoration;
import com.lk.mynews.utils.AnimationControl;
import com.lk.mynews.utils.GetNewsJsonFromUrlUtils;
import com.lk.mynews.utils.JsoupUtils;
import com.lk.mynews.utils.ParseJsonUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr.li on 2016/1/17.
 */
public class NewsListFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener {

    private View rootView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private ImageView ivLoading;

    private NewsSociologyItemAdapter recyclerViewAdapter;

    private AnimationControl animationControl;

    private AsyncLoadNewsData asyncLoadNewsData;

    private List<Contentlist> contentList = new ArrayList<>();
    private List<SportNewsSlideBean> slide = new ArrayList<>();

    private String newsType;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (rootView != null) {
            ViewGroup parent = (ViewGroup) rootView.getParent();
            if (parent != null) {
                parent.removeView(rootView);
            }
            return rootView;
        }
        rootView = inflater.inflate(R.layout.fragment_news_list, container, false);

        getNewsType();
        initView();
        showLoadingAnimation();
        loadNewsData();

        return rootView;
    }

    private void getNewsType() {
        this.newsType = getArguments()
                .getString("newsType", Constant.SHOWAPI_API_CHANNALID_SOCIOLOGY);
    }

    private void showLoadingAnimation() {
        animationControl = new AnimationControl();
        animationControl.initLoadingAnimation(ivLoading);
        animationControl.showLoadingAnimation();
    }

    private void initView() {
        ivLoading = (ImageView) rootView.findViewById(R.id.ivNewsSociologyLoadImage);
        initRefreshLayout();
        initRecyclerView();
    }

    private void initRefreshLayout() {
        swipeRefreshLayout = (SwipeRefreshLayout) rootView.
                findViewById(R.id.swipeRefreshLayoutNewsSociology);
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    private void initRecyclerView() {
        recyclerViewAdapter = new NewsSociologyItemAdapter(contentList, slide, getContext());
        recyclerView = (RecyclerView) rootView.findViewById(R.id.RecyclerViewNewsSociology);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(recyclerViewAdapter);
//        添加分隔线
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity()
                , DividerItemDecoration.VERTICAL_LIST));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerViewAdapter.setOnItemClickListener(this);
    }

    private void loadNewsData() {
        slide.clear();
        contentList.clear();
        asyncLoadNewsData = new AsyncLoadNewsData();
        asyncLoadNewsData.execute();
    }

    @Override
    public void onRefresh() {
        loadNewsData();
    }

    @Override
    public void onItemClick(View v, int position) {
        String link = contentList.get(position).getLink();
        Intent intent = new Intent(getActivity(), NewsWebActivity.class);
        intent.putExtra("link", link);
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.slide_toleft, R.anim.empty);
    }

    @Override
    public void onDestroy() {
        if (asyncLoadNewsData != null &&
                asyncLoadNewsData.getStatus() != AsyncTask.Status.FINISHED) {
            asyncLoadNewsData.cancel(true);
        }
        super.onDestroy();
    }

    class AsyncLoadNewsData extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] params) {
            try {
                GetNewsJsonFromUrlUtils getNewsJsonFromUrlUtils = new GetNewsJsonFromUrlUtils();
                ParseJsonUtils parseJsonUtils = new ParseJsonUtils();
                JsoupUtils jsoupUtils = new JsoupUtils();
                slide.addAll(jsoupUtils.parseSlider(getNewsJsonFromUrlUtils.loadSlider()));
                String json = getNewsJsonFromUrlUtils.LoadNewsListJson(newsType);
                contentList.addAll(parseJsonUtils.parseSociologyJson(json));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            recyclerViewAdapter.notifyDataSetChanged();
            animationControl.cancelLoadingAnimation();
            swipeRefreshLayout.setRefreshing(false);
        }
    }

}
