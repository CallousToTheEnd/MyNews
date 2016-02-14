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
import com.lk.mynews.Bean.SociologyJsonBean.Contentlist;
import com.lk.mynews.Bean.SportNewsSlideBean;
import com.lk.mynews.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageSize;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr.li on 2016/2/14.
 */
public class NewsSociologyItemAdapter extends BaseRecyclerViewAdapter implements View.OnClickListener {

    private List<SportNewsSlideBean> mSlideList = new ArrayList<>();
    private List<Contentlist> contentlists;

    private Context mContext;

    private ImageLoader imageLoader;

    public NewsSociologyItemAdapter(List<Contentlist> contentlists,
                                    List<SportNewsSlideBean> slideList,
                                    Context context) {
        this.contentlists = contentlists;
        this.mSlideList = slideList;
        this.mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == IS_HEADER) {
            View view = LayoutInflater.from(mContext).
                    inflate(R.layout.news_item_header, parent, false);
            return new MyViewHolder(view, IS_HEADER);
        } else {
            View view = LayoutInflater.from(mContext).
                    inflate(R.layout.news_item_layout, parent, false);
            view.setOnClickListener(this);
            return new MyViewHolder(view, IS_NORMAL);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder mvh = (MyViewHolder) holder;
        if (mvh.getItemType() == IS_HEADER) {
            mvh.getSliderLayout().removeAllSliders();
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
                mvh.getSliderLayout().addSlider(textSliderView);
            }
            mvh.getSliderLayout().setPresetIndicator(SliderLayout.PresetIndicators.Right_Bottom);
        } else {

            mvh.getTvTitle().setText(contentlists.get(position - 1).getTitle());
            mvh.getTvDesc().setText(contentlists.get(position - 1).getDesc());
            ImageSize imageSize = new ImageSize(100, 80);
            String url = "";
            if (contentlists.get(position - 1).getImageurls().size() > 0) {
                url = contentlists.get(position - 1).getImageurls().get(0).getUrl();
            }
            imageLoader.getInstance().displayImage(url, mvh.getIvMainImage(), imageSize);
            mvh.itemView.setTag(position - 1);

        }

    }

    @Override
    public int getItemCount() {
        return contentlists.size() + 1;
    }

    @Override
    public void onClick(View v) {
        getItemListener().onItemClick(v, (int) (v.getTag()));
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private View rootView;
        private TextView tvTitle;
        private TextView tvDesc;
        private ImageView ivMainImage;
        private SliderLayout sliderLayout;

        private int itemType;

        public MyViewHolder(View itemView, int itemType) {
            super(itemView);
            rootView = itemView;
            this.itemType = itemType;
            if (itemType == IS_HEADER) {
                sliderLayout = (SliderLayout) rootView.findViewById(R.id.slider);
            } else {
                tvTitle = (TextView) rootView.findViewById(R.id.tvtvSociologyItemTitle);
                tvDesc = (TextView) rootView.findViewById(R.id.tvSociologyItemDesc);
                ivMainImage = (ImageView) rootView.findViewById(R.id.ivSociologyItemMainImage);
            }
        }

        public TextView getTvTitle() {
            return tvTitle;
        }

        public TextView getTvDesc() {
            return tvDesc;
        }

        public ImageView getIvMainImage() {
            return ivMainImage;
        }

        public SliderLayout getSliderLayout() {
            return sliderLayout;
        }

        public int getItemType() {
            return itemType;
        }
    }

}
