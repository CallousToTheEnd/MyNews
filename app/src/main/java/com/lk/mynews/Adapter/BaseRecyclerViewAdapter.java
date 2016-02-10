package com.lk.mynews.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Mr.li on 2016/2/6.
 */
public class BaseRecyclerViewAdapter extends RecyclerView.Adapter {

    public static final int IS_HEADER = 2;
    public static final int IS_NORMAL = 1;

    private OnRecyclerViewItemClickListener mItemListener = null;

    /**
     * 为RecyclerView定义Item的点击事件接口
     *
     */
    public static interface OnRecyclerViewItemClickListener {
        void onItemClick(View v, int position);
    }

    /**
     * 为RecyclerView注册Item的点击事件
     *
     * @param listener
     */
    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener){
        mItemListener = listener;
    }

    protected OnRecyclerViewItemClickListener getItemListener() {
        if (mItemListener == null) {
            System.out.println("itemListener is null, you should call setOnItemClickListener");
        }
        return mItemListener;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return IS_HEADER;
        } else {
            return IS_NORMAL;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
