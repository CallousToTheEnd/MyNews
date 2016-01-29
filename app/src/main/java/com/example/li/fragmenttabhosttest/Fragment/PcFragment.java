package com.example.li.fragmenttabhosttest.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.li.fragmenttabhosttest.Adapter.PcFragmentRecyclerViewAdapter;
import com.example.li.fragmenttabhosttest.Bean.PcFragmentItemBean;
import com.example.li.fragmenttabhosttest.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Mr.li on 2016-01-07.
 */
public class PcFragment extends Fragment {

    private View rootView;

    private TextView tvLogin;
    private ImageView ivHeadImage;
    private RecyclerView recyclerView;

    private LinearLayout llPcRead, llPcFav, llPcTie, llPcGolden;

    private List<PcFragmentItemBean> items = new ArrayList<>();

    private PcFragmentRecyclerViewAdapter recyclerViewAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView != null) {
            ViewGroup parent = (ViewGroup) rootView.getParent();
            if (parent != null) {
                parent.removeView(rootView);
            }
            return rootView;
        }
        rootView = inflater.inflate(R.layout.fragment_pc, container, false);
        initView();
        return rootView;
    }

    private void initView() {
        tvLogin = (TextView) rootView.findViewById(R.id.tvPcLogin);
        ivHeadImage = (ImageView) rootView.findViewById(R.id.ivPcHeadImage);
        llPcRead = (LinearLayout) rootView.findViewById(R.id.llPcRead);
        llPcFav = (LinearLayout) rootView.findViewById(R.id.llPcFav);
        llPcTie = (LinearLayout) rootView.findViewById(R.id.llPcTie);
        llPcGolden = (LinearLayout) rootView.findViewById(R.id.llPcGolden);

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "立即登陆", Toast.LENGTH_SHORT).show();
            }
        });
        ivHeadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "立即登陆", Toast.LENGTH_SHORT).show();
            }
        });
        llPcRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "阅读", Toast.LENGTH_SHORT).show();
            }
        });
        llPcFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "收藏", Toast.LENGTH_SHORT).show();
            }
        });
        llPcTie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "跟帖", Toast.LENGTH_SHORT).show();
            }
        });
        llPcGolden.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "金币", Toast.LENGTH_SHORT).show();
            }
        });
        recyclerViewAdapter = new PcFragmentRecyclerViewAdapter(getItems(), getContext());
        recyclerViewAdapter.setOnItemClickListener(new PcFragmentRecyclerViewAdapter
                .OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                TextView tvFirname = (TextView) v.findViewById(R.id.tvPcRvViewHolderFirText);
                Toast.makeText(getContext(), "position:" + position
                        + "/nitem:" + tvFirname.getText(), Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerViewPcFragment);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    private List<PcFragmentItemBean> getItems() {
        PcFragmentItemBean item1 = new PcFragmentItemBean(R.drawable.biz_pc_main_message
                , "我的消息", "");
        PcFragmentItemBean item2 = new PcFragmentItemBean(R.drawable.biz_pc_main_goldmall
                , "金币商城", "");
        PcFragmentItemBean item3 = new PcFragmentItemBean(R.drawable.biz_pc_main_task
                , "我的任务", "");
        PcFragmentItemBean item4 = new PcFragmentItemBean(R.drawable.biz_pc_main_wallet
                , "我的钱包", "");
        PcFragmentItemBean item5 = new PcFragmentItemBean(R.drawable.biz_pc_main_offline
                , "离线阅读", "");
        PcFragmentItemBean item6 = new PcFragmentItemBean(R.drawable.biz_pc_main_promo
                , "活动广场", "");
        PcFragmentItemBean item7 = new PcFragmentItemBean(R.drawable.biz_pc_main_gamecenter
                , "游戏中心", "网易游戏·年度大杂烩");
        PcFragmentItemBean item8 = new PcFragmentItemBean(R.drawable.biz_pc_main_mail
                , "我的邮箱", "");
        PcFragmentItemBean item9 = new PcFragmentItemBean(R.drawable.biz_pc_main_invitefriends
                , "邀请好友", "邀请好友送百兆流量");
        items.add(item1);
        items.add(item2);
        items.add(item3);
        items.add(item4);
        items.add(item5);
        items.add(item6);
        items.add(item7);
        items.add(item8);
        items.add(item9);
        return items;
    }

}
