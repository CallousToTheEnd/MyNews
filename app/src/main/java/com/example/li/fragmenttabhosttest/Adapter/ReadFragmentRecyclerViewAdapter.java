package com.example.li.fragmenttabhosttest.Adapter;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.li.fragmenttabhosttest.Bean.ReadFragmentItemBean;
import com.example.li.fragmenttabhosttest.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr.li on 2016/1/22.
 */
public class ReadFragmentRecyclerViewAdapter extends RecyclerView.Adapter implements View.OnClickListener {

    private static final int IS_HEADER = 2;
    private static final int IS_NORMAL = 1;

    private MyViewHolder viewHolder;
    private List<ReadFragmentItemBean> mReads = new ArrayList<>();

    private Context mContext;

    private OnRecyclerViewItemClickListener mItemClickListener = null;

    public ReadFragmentRecyclerViewAdapter(List<ReadFragmentItemBean> mReads, Context mContext) {
        this.mReads = mReads;
        this.mContext = mContext;
    }

    @Override
    public void onClick(View v) {
        if (mItemClickListener != null) {
            mItemClickListener.onItemClick(v,(int)v.getTag());
        }
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener){
        mItemClickListener = listener;
    }

    public static interface OnRecyclerViewItemClickListener{
        void onItemClick(View v, int position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == IS_HEADER) {
            View view = LayoutInflater.from(mContext)
                    .inflate(R.layout.read_recyclerview_header,parent, false);
            viewHolder = new MyViewHolder(view, viewType);
            return viewHolder;
        } else {
            View view = LayoutInflater.from(mContext)
                    .inflate(R.layout.read_recyclerview_viewholder,parent,false);
            viewHolder = new MyViewHolder(view,viewType);
            view.setOnClickListener(this);
            return viewHolder;
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myh = (MyViewHolder) holder;
        if (myh.getItemType() == IS_HEADER) {
            myh.getBtnHead().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "立即登陆", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            myh.getImage().setImageResource(mReads.get(position - 1).getImage());
            myh.getTvDesc().setText(mReads.get(position - 1).getDesc());
            myh.getTvSource().setText(mReads.get(position -1 ).getSource());
            myh.getBtnUnfun().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext,"Click", Toast.LENGTH_SHORT).show();
                }
            });
            myh.itemView.setTag(position - 1);
        }

    }

    @Override
    public int getItemCount() {
        return mReads.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return IS_HEADER;
        } else {
            return IS_NORMAL;
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private View rootView;
        private ImageView image;
        private TextView tvDesc, tvSource;
        private Button btnUnfun, btnHead;

        private int itemType;

        public MyViewHolder(View itemView, int itemType) {
            super(itemView);
            this.rootView = itemView;
            this.itemType = itemType;
            if (itemType == IS_HEADER) {
                btnHead = (Button) rootView.findViewById(R.id.btnReadRvHead);
            } else {
                image = (ImageView) rootView.findViewById(R.id.ivReadRvViewHolderImage);
                tvDesc = (TextView) rootView.findViewById(R.id.tvReadRvViewHolderDesc);
                tvSource = (TextView) rootView.findViewById(R.id.tvReadRvViewHolderResource);
                btnUnfun = (Button) rootView.findViewById(R.id.btnReadRvViewHolderUnfun);
            }

        }

        public ImageView getImage() {
            return this.image;
        }

        public TextView getTvDesc() {
            return this.tvDesc;
        }

        public TextView getTvSource() {
            return this.tvSource;
        }

        public Button getBtnUnfun() {
            return this.btnUnfun;
        }

        public Button getBtnHead() {
            return this.btnHead;
        }

        public int getItemType() { return this.itemType; }

    }

}
