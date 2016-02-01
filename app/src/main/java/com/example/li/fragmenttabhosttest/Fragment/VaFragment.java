package com.example.li.fragmenttabhosttest.Fragment;

import android.opengl.Visibility;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.li.fragmenttabhosttest.Adapter.VaFragmentRecyclerViewAdapter;
import com.example.li.fragmenttabhosttest.Bean.VaFragmentItemBean;
import com.example.li.fragmenttabhosttest.R;
import com.example.li.fragmenttabhosttest.utils.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr.li on 2016-01-07.
 */
public class VaFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    View rootView;
//    private SurfaceView surfaceView;
//    private Button btnPlay, btnPlayOrPause, btnClose;

    private SwipeRefreshLayout refreshLayout;
    private RecyclerView recyclerView;

    private VaFragmentRecyclerViewAdapter recyclerViewAdapter;

    private List<VaFragmentItemBean> videos = new ArrayList<>();

//    private Player player;

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
        rootView = inflater.inflate(R.layout.fragment_va, container, false);
        initView();
        return rootView;
    }

    private void initView() {
        refreshLayout = (SwipeRefreshLayout) rootView
                .findViewById(R.id.swipeRefreshLayoutVaFragment);
        refreshLayout.setOnRefreshListener(this);
        recyclerViewAdapter = new VaFragmentRecyclerViewAdapter(getVideos(), getContext());
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerViewVaFragment);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerViewAdapter.setOnItemClickListener(new VaFragmentRecyclerViewAdapter
                .OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                TextView tvv = (TextView) v.findViewById(R.id.tvVaRvViewHolderTitle);
                Toast.makeText(getContext(), "title:" + tvv.getText() +
                        "\n position:" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onRefresh() {
        Toast.makeText(getContext(), "刷新成功", Toast.LENGTH_SHORT).show();
        refreshLayout.setRefreshing(false);
    }

    private List<VaFragmentItemBean> getVideos() {
        VaFragmentItemBean video1 = new VaFragmentItemBean("大柯基教小柯基如何坐下",
                "萌化了", 32, 9437, 37);
        VaFragmentItemBean video2 = new VaFragmentItemBean("荷兰青年街头向华人撒奶粉",
                "疑不满华人抢购奶粉", 120, 11000, 558);
        VaFragmentItemBean video3 = new VaFragmentItemBean("笑死！爸爸喝多了个儿子做游戏",
                "当爹的够萌", 18, 9143, 19);
        videos.add(video1);
        videos.add(video2);
        videos.add(video3);
        return videos;
    }

}
