package com.lk.mynews.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.lk.mynews.Adapter.TopicFragmentRecyclerViewAdapter;
import com.lk.mynews.Bean.TopicFragmentItemBean;
import com.lk.mynews.Config.Constant;
import com.lk.mynews.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr.li on 2016-01-07.
 */
public class TopicFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    public static final int TOPIC_STATE_ONGOING = 1;
    public static final int TOPIC_STATE_END = 2;

    private View rootView;
    private Toolbar toolbar;
    private TextView tvTopicToolbar;

    private RecyclerView recyclerView;
    private TopicFragmentRecyclerViewAdapter recyclerViewAdapter;

    private SwipeRefreshLayout refreshLayout;

    private List<TopicFragmentItemBean> topics = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container
            , Bundle savedInstanceState) {
        if (rootView != null) {
            ViewGroup parent = (ViewGroup) rootView.getParent();
            if (parent != null) {
                parent.removeView(rootView);
            }
            return rootView;
        }
        rootView = inflater.inflate(R.layout.fragment_topic, container, false);
        initView();
        return rootView;
    }

    private void initView() {
        tvTopicToolbar = (TextView) rootView.findViewById(R.id.tvTopicToolbar);
        tvTopicToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "置顶", Toast.LENGTH_SHORT).show();
            }
        });
        toolbar = (Toolbar) rootView.findViewById(R.id.toolbarTopicFragment);
        toolbar.inflateMenu(R.menu.menu_topic);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.topic_menu:
                        Toast.makeText(getContext(), "我关注的", Toast.LENGTH_SHORT).show();
                        break;
                }
                return false;
            }
        });
        refreshLayout = (SwipeRefreshLayout) rootView
                .findViewById(R.id.swipeRefreshLayoutTopicFragment);
        refreshLayout.setOnRefreshListener(this);
        recyclerViewAdapter = new TopicFragmentRecyclerViewAdapter(getContext(), Constant.getTopics());
        recyclerViewAdapter.setOnItemClickListener(new TopicFragmentRecyclerViewAdapter
                .OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                TextView tvName = (TextView) v.findViewById(R.id.tvTopicRvViewHolderName);
                Toast.makeText(getContext(), "position:" + position + "\n专家："
                        + tvName.getText(), Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerViewTopicFragment);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    @Override
    public void onRefresh() {
        refreshLayout.setRefreshing(false);
        Toast.makeText(getContext(), "刷新成功", Toast.LENGTH_SHORT).show();
    }

}
