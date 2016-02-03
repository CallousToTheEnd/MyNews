package com.example.li.fragmenttabhosttest.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.li.fragmenttabhosttest.Adapter.ReadFragmentRecyclerViewAdapter;
import com.example.li.fragmenttabhosttest.Bean.ReadFragmentItemBean;
import com.example.li.fragmenttabhosttest.R;
import com.example.li.fragmenttabhosttest.utils.AnimationControl;

import java.util.ArrayList;
import java.util.List;

/**
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
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    @Override
    public void onRefresh() {
        Toast.makeText(getContext(), "刷新成功", Toast.LENGTH_SHORT).show();
        refreshLayout.setRefreshing(false);
    }

    private List<ReadFragmentItemBean> getReads() {
        ReadFragmentItemBean read1 = new ReadFragmentItemBean(R.drawable.read_test_image1,
                "哒哒良品", "中国“第五大发明”横空出世？据说能让你撕逼所向披靡");
        ReadFragmentItemBean read2 = new ReadFragmentItemBean(R.drawable.read_test_image2,
                "中国日报网", "当零下11℃遇上孩子的脑洞各自操心的事距离有多远");
        ReadFragmentItemBean read3 = new ReadFragmentItemBean(R.drawable.read_test_image3,
                "人民网", "小学生谈二孩：我是家里小皇帝 再生就是篡位");
        ReadFragmentItemBean read4 = new ReadFragmentItemBean(R.drawable.read_test_image4,
                "环球趣闻", "美国喵星人胸口有个超萌黑色大爱心 因太内向从没离开收容");
        ReadFragmentItemBean read5 = new ReadFragmentItemBean(R.drawable.read_test_image5,
                "哒哒", "键盘上藏着一排秘密 ASDFGHJKL代表爱情哲理？");
        ReadFragmentItemBean read6 = new ReadFragmentItemBean(R.drawable.read_test_image6,
                "哒哒" , "如果看不到这一点，你就白看了《太子妃》");
        ReadFragmentItemBean read7 = new ReadFragmentItemBean(R.drawable.read_test_image7,
                "神秘的地球", "澳洲海岸惊现长达7米的巨无霸大白鲨");
        ReadFragmentItemBean read8 = new ReadFragmentItemBean(R.drawable.read_test_image8,
                "华股财经", "揭秘网络主播：60万人观看换衣 土豪一晚砸百万");
        ReadFragmentItemBean read9 = new ReadFragmentItemBean(R.drawable.read_test_image9,
                "环球网", "野狼群夜袭河北村庄 50多只羊惨死");
        ReadFragmentItemBean read10 = new ReadFragmentItemBean(R.drawable.read_test_image10,
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
