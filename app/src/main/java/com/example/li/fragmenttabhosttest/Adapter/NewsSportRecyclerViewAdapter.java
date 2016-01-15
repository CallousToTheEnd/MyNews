package com.example.li.fragmenttabhosttest.Adapter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.example.li.fragmenttabhosttest.Bean.NewsContentBean;
import com.example.li.fragmenttabhosttest.Fragment.SportNewsFragment;
import com.example.li.fragmenttabhosttest.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 实现了Item的点击事件，参考资料：http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2015/0327/2647.html
 *
 * Created by Mr.li on 2016/1/11.
 */
public class NewsSportRecyclerViewAdapter extends RecyclerView.Adapter implements View.OnClickListener {


    private static final int IS_HEADER = 2;
    private static final int IS_NORMAL = 1;
    private static final int SLIDER_COUNT = 5;

    private Context mContext;
    private Activity mActivity;

    private MyViewHolder mViewHolder;

    private List<NewsContentBean> mDataList = new ArrayList<>();

    private OnRecyclerViewItemClickListener mOnItemClickListener = null;


    public NewsSportRecyclerViewAdapter(List<NewsContentBean> Data, Context context, Activity activity) {
        mDataList = Data;
        mContext = context;
        mActivity = activity;
    }

    /**
     * 自定义实现RecyclerView的Item点击事件接口
     */
    public static interface OnRecyclerViewItemClickListener {
        void onItemClick(View v, int position);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == IS_NORMAL) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sportnews_recyclerview_viewhodler,parent,false);
            mViewHolder = new MyViewHolder(view,IS_NORMAL);
            //将创建的View注册点击事件
            view.setOnClickListener(this);
            return mViewHolder;
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_sport_sliderview, parent, false);
            mViewHolder = new MyViewHolder(view,IS_HEADER);
            return mViewHolder;
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        MyViewHolder myh = (MyViewHolder) holder;

        if (myh.itemType == IS_NORMAL) {
            myh.getTvTime().setText(mDataList.get(position).getTime());
            myh.getTvTitle().setText(mDataList.get(position).getTitle());
            //将位置保存在itemView的Tag中，以便点击时进行获取
            myh.itemView.setTag(position - 1);
        } else if (myh.itemType == IS_HEADER){
            for (int i = 0; i < SLIDER_COUNT; i++) {

                TextSliderView textSliderView = new TextSliderView(mContext);

                textSliderView.description("")
                        .image(R.mipmap.ic_launcher)
                        .bundle(new Bundle())
                        .setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                            @Override
                            public void onSliderClick(BaseSliderView slider) {
                                Toast.makeText(mContext, slider.getBundle().getString("link", "-1"), Toast.LENGTH_SHORT).show();
                            }
                        });

                myh.getSliderLayout().addSlider(textSliderView);

            }


            myh.getSliderLayout().setPresetIndicator(SliderLayout.PresetIndicators.Right_Bottom);

        }




    }

    @Override
    public int getItemCount() {
        return mDataList.size() + 1;
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取数据
            mOnItemClickListener.onItemClick(v, (int)v.getTag());
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return IS_HEADER;
        } else {
            return IS_NORMAL;
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
        private SliderLayout sliderLayout;
        private View rootView;

        private int itemType;


        public MyViewHolder(View itemView, int itemType) {
            super(itemView);
            rootView = itemView;

            this.itemType = itemType;


            if (itemType == IS_HEADER) {
                sliderLayout = (SliderLayout) rootView.findViewById(R.id.slider);
            } else if (itemType == IS_NORMAL){
                tvTitle = (TextView) rootView.findViewById(R.id.tvNewsSportRvViewHolderTitle);
                tvTime = (TextView) rootView.findViewById(R.id.tvNewsSportRvViewHolderTime);
            }

        }

        public TextView getTvTitle() {
            return tvTitle;
        }

        public TextView getTvTime() {
            return  tvTime;
        }

        public SliderLayout getSliderLayout() {
            return sliderLayout;
        }

    }

}
