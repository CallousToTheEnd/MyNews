package com.example.li.fragmenttabhosttest.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.li.fragmenttabhosttest.Bean.NewsContentBean;
import com.example.li.fragmenttabhosttest.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 实现了Item的点击事件，参考资料：http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2015/0327/2647.html
 *
 * Created by Mr.li on 2016/1/11.
 */
public class NewsSportRecyclerViewAdapter extends RecyclerView.Adapter implements View.OnClickListener {


    private MyViewHolder mViewHolder;

    private List<NewsContentBean> mDataList = new ArrayList<>();

    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    int timecount;
    int titlecount;


    /**
     * 自定义实现RecyclerView的Item点击事件接口
     */
    public static interface OnRecyclerViewItemClickListener {
        void onItemClick(View v, int position);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sportnews_recyclerview_viewhodler,parent,false);
        mViewHolder = new MyViewHolder(view);

        //将创建的View注册点击事件
        view.setOnClickListener(this);

        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        MyViewHolder myh = (MyViewHolder) holder;


        myh.getTvTime().setText("Time" + timecount);
        myh.getTvTitle().setText("title" + titlecount);

        //将位置保存在itemView的Tag中，以便点击时进行获取
        myh.itemView.setTag(position);

        timecount++;
        titlecount++;

    }

    @Override
    public int getItemCount() {
        return 3;
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取数据
            mOnItemClickListener.onItemClick(v, (int)v.getTag());
        }
    }

    /**
     * 为RecyclerView注册Item的点击事件
     * @param listener
     */
    public void setOnItemClickListener (OnRecyclerViewItemClickListener listener){
        this.mOnItemClickListener = listener;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {


        private TextView tvTitle;
        private TextView tvTime;
        private View rootView;


        public MyViewHolder(View itemView) {
            super(itemView);
            rootView = itemView;
            tvTitle = (TextView) rootView.findViewById(R.id.tvNewsSportRvViewHolderTitle);
            tvTime = (TextView) rootView.findViewById(R.id.tvNewsSportRvViewHolderTime);
        }

        public TextView getTvTitle() {
            return tvTitle;
        }

        public TextView getTvTime() {
            return  tvTime;
        }

    }

}
