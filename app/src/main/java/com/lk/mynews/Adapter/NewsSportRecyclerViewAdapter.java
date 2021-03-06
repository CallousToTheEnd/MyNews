package com.lk.mynews.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.lk.mynews.Bean.NewsContentBean;
import com.lk.mynews.Bean.SportNewsSlideBean;
import com.lk.mynews.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageSize;

import java.util.ArrayList;
import java.util.List;

/**
 * 实现了Item的点击事件，参考资料：http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2015/0327/2647.html
 * <p/>
 * Created by Mr.li on 2016/1/11.
 */
public class NewsSportRecyclerViewAdapter extends BaseRecyclerViewAdapter implements View.OnClickListener {

    private Context mContext;

    private MyViewHolder mViewHolder;

    private List<NewsContentBean> mNewsList = new ArrayList<>();
    private List<SportNewsSlideBean> mSlideList = new ArrayList<>();

    private ImageLoader imageLoader;


    public NewsSportRecyclerViewAdapter(List<NewsContentBean> Data
            , List<SportNewsSlideBean> slideData, Context context) {
        mNewsList = Data;
        mContext = context;
        mSlideList = slideData;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //因为要在第一个位置上放一个SliderLayout，所以对不同的flag创建不同的Holder，这里的flag是重写getItemViewType方法中返回的数值
        if (viewType == IS_NORMAL) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.sport_news_item_layout, parent, false);
            mViewHolder = new MyViewHolder(view, IS_NORMAL);
            //将创建的View注册点击事件
            view.setOnClickListener(this);
            return mViewHolder;
        } else {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.news_item_header, parent, false);
            mViewHolder = new MyViewHolder(view, IS_HEADER);
            return mViewHolder;
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        MyViewHolder myh = (MyViewHolder) holder;

        //这里的itemType是创建MyViewHoldler时传入的参数；
        if (myh.itemType == IS_NORMAL) {
//            因为position=0的时候是SliderView
            myh.getTvTime().setText(mNewsList.get(position - 1).getTime());
            myh.getTvTitle().setText(mNewsList.get(position - 1).getTitle());
//            ImageLoader加载网络图片
            ImageSize imageSize = new ImageSize(1000, 85);
            imageLoader.getInstance().displayImage(mNewsList.get(position - 1).getPicUrl()
                    , myh.getIvImage(), imageSize);

            //将位置保存在itemView的Tag中，以便点击时进行获取
            myh.itemView.setTag(position - 1);
        } else if (myh.itemType == IS_HEADER) {

            myh.getSliderLayout().removeAllSliders();
            for (int i = 0; i < mSlideList.size(); i++) {

                TextSliderView textSliderView = new TextSliderView(mContext);

                final int finalI = i;
                textSliderView.description(mSlideList.get(i).getDesc())
                        .image(mSlideList.get(i).getPicUrl())
                        .setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                            @Override
                            public void onSliderClick(BaseSliderView slider) {
                                Toast.makeText(mContext, mSlideList.get(finalI).getLink()
                                        , Toast.LENGTH_SHORT).show();
                            }
                        });

                myh.getSliderLayout().addSlider(textSliderView);

            }


            myh.getSliderLayout().setPresetIndicator(SliderLayout.PresetIndicators.Right_Bottom);

        }


    }

    @Override
    public int getItemCount() {
        return mNewsList.size() + 1;
    }

    @Override
    public void onClick(View v) {
        if (getItemListener() != null) {
            //注意这里使用getTag方法获取数据
            getItemListener().onItemClick(v, (int) v.getTag());
        }
    }



    class MyViewHolder extends RecyclerView.ViewHolder {


        private TextView tvTitle;
        private TextView tvTime;
        private ImageView ivImage;
        private SliderLayout sliderLayout;
        private View rootView;

        private int itemType;


        public MyViewHolder(View itemView, int itemType) {
            super(itemView);
            rootView = itemView;

            this.itemType = itemType;

            if (itemType == IS_HEADER) {
                sliderLayout = (SliderLayout) rootView.findViewById(R.id.slider);
            } else if (itemType == IS_NORMAL) {
                tvTitle = (TextView) rootView.findViewById(R.id.tvNewsSportRvViewHolderTitle);
                tvTime = (TextView) rootView.findViewById(R.id.tvNewsSportRvViewHolderTime);
                ivImage = (ImageView) rootView.findViewById(R.id.ivNewsSportRvViewHolderImage);
            }

        }

        public TextView getTvTitle() {
            return tvTitle;
        }

        public TextView getTvTime() {
            return tvTime;
        }

        public ImageView getIvImage() {
            return ivImage;
        }

        public SliderLayout getSliderLayout() {
            return sliderLayout;
        }

    }

}
