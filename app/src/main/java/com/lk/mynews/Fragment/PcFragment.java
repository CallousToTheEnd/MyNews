package com.lk.mynews.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lk.mynews.Activity.SettingActivity;
import com.lk.mynews.Adapter.PcFragmentRecyclerViewAdapter;
import com.lk.mynews.Bean.PcFragmentItemBean;
import com.lk.mynews.Config.Constant;
import com.lk.mynews.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr.li on 2016-01-07.
 */
public class PcFragment extends Fragment implements View.OnClickListener, PcFragmentRecyclerViewAdapter.OnRecyclerViewItemClickListener, Toolbar.OnMenuItemClickListener {

    private View rootView;

    private TextView tvLogin;
    private ImageView ivHeadImage;
    private RecyclerView recyclerView;
    private Toolbar toolbar;

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
        toolbar = (Toolbar) rootView.findViewById(R.id.toolbarPcFragment);
        toolbar.inflateMenu(R.menu.menu_pc);
        toolbar.setOnMenuItemClickListener(this);
        tvLogin = (TextView) rootView.findViewById(R.id.tvPcLogin);
        ivHeadImage = (ImageView) rootView.findViewById(R.id.ivPcHeadImage);
        llPcRead = (LinearLayout) rootView.findViewById(R.id.llPcRead);
        llPcFav = (LinearLayout) rootView.findViewById(R.id.llPcFav);
        llPcTie = (LinearLayout) rootView.findViewById(R.id.llPcTie);
        llPcGolden = (LinearLayout) rootView.findViewById(R.id.llPcGolden);

        tvLogin.setOnClickListener(this);
        ivHeadImage.setOnClickListener(this);
        llPcRead.setOnClickListener(this);
        llPcFav.setOnClickListener(this);
        llPcTie.setOnClickListener(this);
        llPcGolden.setOnClickListener(this);
        recyclerViewAdapter = new PcFragmentRecyclerViewAdapter(Constant.getPcItems(), getContext());
        recyclerViewAdapter.setOnItemClickListener(this);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerViewPcFragment);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.tvPcLogin:
                Toast.makeText(getContext(), "立即登陆", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ivPcHeadImage:
                Toast.makeText(getContext(), "立即登陆", Toast.LENGTH_SHORT).show();
                break;
            case R.id.llPcRead:
                Toast.makeText(getContext(), "阅读", Toast.LENGTH_SHORT).show();
                break;
            case R.id.llPcFav:
                Toast.makeText(getContext(), "收藏", Toast.LENGTH_SHORT).show();
                break;
            case R.id.llPcTie:
                Toast.makeText(getContext(), "跟帖", Toast.LENGTH_SHORT).show();
                break;
            case R.id.llPcGolden:
                Toast.makeText(getContext(), "金币", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onItemClick(View v, int position) {
        TextView tvFirname = (TextView) v.findViewById(R.id.tvPcRvViewHolderFirText);
        Toast.makeText(getContext(), "position:" + position
                + "/nitem:" + tvFirname.getText(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.pc_menu:
                Intent intent = new Intent(getActivity(), SettingActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.slide_toleft, R.anim.empty);
                break;
        }
        return false;
    }
}
