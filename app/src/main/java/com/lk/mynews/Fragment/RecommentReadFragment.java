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
        recyclerViewAdapter = new ReadFragmentRecyclerViewAdapter(getReads(), getContext(),getActivity());
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

    private List<ReadFragmentItemBean> getReads() {
        ReadFragmentItemBean read1 = new ReadFragmentItemBean(
                "http://img4.imgtn.bdimg.com/it/u=3981050270,171872606&fm=21&gp=0.jpg",
                "哒哒良品", "中国“第五大发明”横空出世？据说能让你撕逼所向披靡");
        ReadFragmentItemBean read2 = new ReadFragmentItemBean(
                "http://img2.imgtn.bdimg.com/it/u=914584203,2933819813&fm=21&gp=0.jpg",
                "中国日报网", "当零下11℃遇上孩子的脑洞各自操心的事距离有多远");
        ReadFragmentItemBean read3 = new ReadFragmentItemBean(
                "http://img2.imgtn.bdimg.com/it/u=1206262111,3132591149&fm=21&gp=0.jpg",
                "人民网", "小学生谈二孩：我是家里小皇帝 再生就是篡位");
        ReadFragmentItemBean read4 = new ReadFragmentItemBean(
                "http://img2.imgtn.bdimg.com/it/u=2160868544,206776260&fm=11&gp=0.jpg",
                "环球趣闻", "美国喵星人胸口有个超萌黑色大爱心 因太内向从没离开收容");
        ReadFragmentItemBean read5 = new ReadFragmentItemBean(
                "http://img4.imgtn.bdimg.com/it/u=3476130273,879846185&fm=11&gp=0.jpg",
                "哒哒", "键盘上藏着一排秘密 ASDFGHJKL代表爱情哲理？");
        ReadFragmentItemBean read6 = new ReadFragmentItemBean(
                "http://img0.imgtn.bdimg.com/it/u=4101061768,814675782&fm=11&gp=0.jpg",
                "哒哒" , "如果看不到这一点，你就白看了《太子妃》");
        ReadFragmentItemBean read7 = new ReadFragmentItemBean(
                "http://img3.imgtn.bdimg.com/it/u=2593334733,1222889682&fm=11&gp=0.jpg",
                "神秘的地球", "澳洲海岸惊现长达7米的巨无霸大白鲨");
        ReadFragmentItemBean read8 = new ReadFragmentItemBean(
                "http://img4.imgtn.bdimg.com/it/u=1588131021,3485757191&fm=11&gp=0.jpg",
                "华股财经", "揭秘网络主播：60万人观看换衣 土豪一晚砸百万");
        ReadFragmentItemBean read9 = new ReadFragmentItemBean(
                "http://img3.imgtn.bdimg.com/it/u=4131541063,3115516794&fm=11&gp=0.jpg",
                "环球网", "野狼群夜袭河北村庄 50多只羊惨死");
        ReadFragmentItemBean read10 = new ReadFragmentItemBean(
                "http://img1.imgtn.bdimg.com/it/u=3396739401,4195304750&fm=11&gp=0.jpg",
                "哒哒", "80岁奶奶的表演一度被评委叫停，但他接下来竟...");
        reads.add(read1);
        reads.add(read2);
        reads.add(read3);
        reads.add(read4);
        reads.add(read5);
        reads.add(read6);
        reads.add(read7);
        reads.add(read8);
        reads.add(read9);
        reads.add(read10);
        return reads;
    }
}
