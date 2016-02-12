package com.lk.mynews.Fragment;

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
import android.widget.TextView;
import android.widget.Toast;

import com.lk.mynews.Adapter.ReadFragmentRecyclerViewAdapter;
import com.lk.mynews.Bean.ReadFragmentItemBean;
import com.lk.mynews.Config.Constant;
import com.lk.mynews.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 阅读页——推荐阅读
 * Created by Mr.li on 2016/1/29.
 */
public class RecommentReadFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private View rootView;

    private SwipeRefreshLayout refreshLayout;

    private RecyclerView recyclerView;

    private ReadFragmentRecyclerViewAdapter recyclerViewAdapter;

    private List<ReadFragmentItemBean> reads = new ArrayList<>();


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
        rootView = inflater.inflate(R.layout.fragment_read_recommendread, container, false);
        initView();
        return rootView;
    }

    private void initView() {
        refreshLayout = (SwipeRefreshLayout) rootView
                .findViewById(R.id.swipeRefreshLayoutRecommendReadFragment);
        refreshLayout.setOnRefreshListener(this);
        recyclerViewAdapter = new ReadFragmentRecyclerViewAdapter(Constant.getReads(), getContext(),getActivity());
        recyclerViewAdapter.setOnItemClickListener(new ReadFragmentRecyclerViewAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                TextView tvDesc = (TextView) v.findViewById(R.id.tvReadRvViewHolderDesc);
                TextView tvSource = (TextView) v.findViewById(R.id.tvReadRvViewHolderResource);
                String toast = "desc:" + tvDesc.getText() + "\n"
                        + "source" + tvSource.getText() + "\n"
                        + "position:" + position;
                Toast.makeText(getContext(), toast, Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerViewRecommendReadFragment);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    @Override
    public void onRefresh() {
        Toast.makeText(getContext(), "刷新成功", Toast.LENGTH_SHORT).show();
        refreshLayout.setRefreshing(false);
    }

}
