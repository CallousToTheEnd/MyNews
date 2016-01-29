package com.example.li.fragmenttabhosttest.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.li.fragmenttabhosttest.Adapter.TopicFragmentRecyclerViewAdapter;
import com.example.li.fragmenttabhosttest.Bean.TopicFragmentItemBean;
import com.example.li.fragmenttabhosttest.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr.li on 2016-01-07.
 */
public class TopicFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    public static final int TOPIC_ITEMTYPE_TECHNOLOGY = 1;
    public static final int TOPIC_ITEMTYPE_HUMANITY = 2;
    public static final int TOPIC_ITEMTYPE_FINANCE = 3;
    public static final int TOPIC_ITEMTYPE_HEALTHY = 4;
    public static final int TOPIC_ITEMTYPE_OTHER = 5;

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
        recyclerViewAdapter = new TopicFragmentRecyclerViewAdapter(getContext(), getTopics());
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
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    @Override
    public void onRefresh() {
        refreshLayout.setRefreshing(false);
        Toast.makeText(getContext(), "刷新成功", Toast.LENGTH_SHORT).show();
    }

    private List<TopicFragmentItemBean> getTopics() {
        TopicFragmentItemBean topic1 = new TopicFragmentItemBean("贾子冰"
                , R.drawable.test_readfragment, R.mipmap.ic_launcher
                , "我是从事气象工作的贾子冰，关于BOSS寒潮、天气以及“解冻”时间的问题问我吧！", 1940, 1, 1);
        TopicFragmentItemBean topic2 = new TopicFragmentItemBean("宁嫁雨"
                , R.drawable.test_readfragment, R.mipmap.ic_launcher
                , "我是南开大学博士生导师宁嫁雨，关于魏晋风度、世说新语、传统文化与文学，问我吧！", 1646, 2, 1);
        TopicFragmentItemBean topic3 = new TopicFragmentItemBean("一本道"
                , R.drawable.test_readfragment, R.mipmap.ic_launcher
                , "我是不正经小百科，擅长一本正经的胡说八道，承接任何问题，问我吧！", 47000, 2, 1);
        TopicFragmentItemBean topic4 = new TopicFragmentItemBean("宋清辉"
                , R.drawable.test_readfragment, R.mipmap.ic_launcher
                , "我是经济学家宋清辉，中央经济工作、楼市去库存，创业就业、新常态等问题，问我吧！", 7101, 3, 1);
        TopicFragmentItemBean topic5 = new TopicFragmentItemBean("网易跟帖"
                , R.drawable.test_readfragment, R.mipmap.ic_launcher
                , "我是网易跟帖的小编，关于跟帖的一切问题，问我吧！", 6243, 5, 1);
        topics.add(topic1);
        topics.add(topic2);
        topics.add(topic3);
        topics.add(topic4);
        topics.add(topic5);
        return topics;
    }

}
