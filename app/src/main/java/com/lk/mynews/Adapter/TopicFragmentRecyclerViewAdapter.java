package com.lk.mynews.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lk.mynews.Bean.TopicFragmentItemBean;
import com.lk.mynews.Config.Constant;
import com.lk.mynews.Fragment.TopicFragment;
import com.lk.mynews.MyApplication;
import com.lk.mynews.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr.li on 2016/1/23.
 */
public class TopicFragmentRecyclerViewAdapter extends RecyclerView.Adapter implements
        View.OnClickListener{

    private MyViewHolder viewHolder;

    private Context mContext;

    private List<TopicFragmentItemBean> mTopics = new ArrayList<>();

    private OnRecyclerViewItemClickListener mItemClickListener = null;

    private ImageLoader imageLoader;

    public TopicFragmentRecyclerViewAdapter(Context mContext, List<TopicFragmentItemBean> mTopics) {
        this.mContext = mContext;
        this.mTopics = mTopics;
    }

    public static interface OnRecyclerViewItemClickListener {
        void onItemClick(View v, int position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.topic_recyclerview_viewholder,parent,false);
        viewHolder = new MyViewHolder(view);
        view.setOnClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder mvh = (MyViewHolder) holder;
        mvh.getTvName().setText(mTopics.get(position).getName());
        mvh.getTvDesc().setText(mTopics.get(position).getDesc());
        if (mTopics.get(position).getInterest() >= 10000) {
            float interest = (float) (mTopics.get(position).getInterest() / 10000.0);
            mvh.getTvInterest().setText(interest + "万关注");
        } else {
            mvh.getTvInterest().setText(mTopics.get(position).getInterest() + "关注");
        }
        switch (mTopics.get(position).getState()) {
            case TopicFragment.TOPIC_STATE_ONGOING:
                mvh.getTvState().setText(mContext.getString(R.string.topic_state_ongoing));
                break;
            case TopicFragment.TOPIC_STATE_END:
                mvh.getTvState().setText(mContext.getString(R.string.topic_state_end));
                break;
        }
        switch (mTopics.get(position).getType()) {
            case Constant.TOPIC_ITEM_TYPE_TECHNOLOGY:
                mvh.getTvType().setText(mContext.getString(R.string.topic_itemtype_technology));
                break;
            case Constant.TOPIC_ITEM_TYPE_HUMANITY:
                mvh.getTvType().setText(mContext.getString(R.string.topic_itemtype_humanity));
                break;
            case Constant.TOPIC_ITEM_TYPE_FINANCE:
                mvh.getTvType().setText(mContext.getString(R.string.topic_itemtype_finance));
                break;
            case Constant.TOPIC_ITEM_TYPE_HEALTHY:
                mvh.getTvType().setText(mContext.getString(R.string.topic_itemtype_healthy));
                break;
            case Constant.TOPIC_ITEM_TYPE_OTHER:
                mvh.getTvType().setText(mContext.getString(R.string.topic_itemtype_other));
                break;
        }
        imageLoader.getInstance().displayImage(mTopics.get(position).getBgImage(), mvh.getIvBg());
        imageLoader.getInstance().displayImage(mTopics.get(position).getBgImage(), mvh.getIvHeadImage());
        mvh.getBtnInterest().setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    buttonView.setTextColor(Color.parseColor("#bebebe"));
                    Toast.makeText(mContext, "已关注", Toast.LENGTH_SHORT).show();
                } else {
                    buttonView.setTextColor(Color.parseColor("#ff4444"));
                    Toast.makeText(mContext, "取消关注", Toast.LENGTH_SHORT).show();
                }
            }
        });
        mvh.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return mTopics.size();
    }

    @Override
    public void onClick(View v) {
        mItemClickListener.onItemClick(v, (int)v.getTag());
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        mItemClickListener = listener;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        View rootView;
        TextView tvName, tvDesc,tvInterest, tvType, tvState;
        ImageView ivBg, ivHeadImage;
        CheckBox btnInterest;

        public MyViewHolder(View itemView) {
            super(itemView);
            rootView = itemView;
            tvName = (TextView) rootView.findViewById(R.id.tvTopicRvViewHolderName);
            tvDesc = (TextView) rootView.findViewById(R.id.tvTopicRvViewHolderDesc);
            tvInterest = (TextView) rootView.findViewById(R.id.tvTopicRvViewHolderInterest);
            tvType = (TextView) rootView.findViewById(R.id.tvTopicRvViewHolderType);
            tvState = (TextView) rootView.findViewById(R.id.tvTopicRvViewHolderState);
            ivBg = (ImageView) rootView.findViewById(R.id.ivTopicRvViewHolderBg);
            ivHeadImage = (ImageView) rootView.findViewById(R.id.ivTopicRvViewHolderHeadImage);
            btnInterest = (CheckBox) rootView.findViewById(R.id.btnTopicRvViewHolderInterest);
        }

        public TextView getTvName() {
            return tvName;
        }

        public TextView getTvDesc() {
            return tvDesc;
        }

        public TextView getTvInterest() {
            return tvInterest;
        }

        public TextView getTvType() {
            return tvType;
        }

        public TextView getTvState() {
            return tvState;
        }

        public ImageView getIvBg() {
            return ivBg;
        }

        public ImageView getIvHeadImage() {
            return ivHeadImage;
        }

        public CheckBox getBtnInterest() {
            return btnInterest;
        }

    }

}
