package com.example.li.fragmenttabhosttest.Fragment;

import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.li.fragmenttabhosttest.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 新闻页面
 * Created by Mr.li on 2016-01-07.
 */
public class NewsFragment extends Fragment {

    private TextView tvTitleSports, tvTitleInternational, tvTitleTechnology,
            tvTitleSociology, tvTitleApple;
    private ImageView ivCursor;
    private ViewPager viewPager;
    private View view;

    //    这个Adapter传入的FragmentManager要注意是getChildFragmentManager
//    否则切换到其他页面再切换回来列表不显示数据
    private NewsViewPagerAdapter viewPagerAdapter;

    private List<TextView> tvTitles = new ArrayList<>();

    private static final int TITLE_COUNT = 5;
    private int current_index = 0;
    private static int lineWidth;
    private static int offset;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_news, container, false);

        initView();

        return view;

    }

    private void initView() {

        initIvCursor();

        tvTitleApple = (TextView) view.findViewById(R.id.tvTitleApple);
        tvTitleInternational = (TextView) view.findViewById(R.id.tvTitleInternational);
        tvTitleSociology = (TextView) view.findViewById(R.id.tvTitleSociology);
        tvTitleSports = (TextView) view.findViewById(R.id.tvTitleSport);
        tvTitleTechnology = (TextView) view.findViewById(R.id.tvTitleTechnology);
        tvTitles.add(tvTitleSports);
        tvTitles.add(tvTitleApple);
        tvTitles.add(tvTitleSociology);
        tvTitles.add(tvTitleTechnology);
        tvTitles.add(tvTitleInternational);

        initViewPager();

    }

    /**
     * 游标的初始设置
     */
    private void initIvCursor() {
        ivCursor = (ImageView) view.findViewById(R.id.ivCursor);
        // 获取图片宽度
        lineWidth = BitmapFactory.decodeResource(getResources(),
                R.mipmap.cursor).getWidth();
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        // 获取屏幕宽度
        int screenWidth = dm.widthPixels;
        Matrix matrix = new Matrix();
        offset = (int) ((screenWidth / (float) 5 - lineWidth) / 2);
        matrix.postTranslate(offset, 0);
        // 设置初始位置
        ivCursor.setImageMatrix(matrix);
    }

    /**
     * 初始化ViewPager
     */
    private void initViewPager() {

        viewPager = (ViewPager) view.findViewById(R.id.viewPager);

        viewPagerAdapter = new NewsViewPagerAdapter(getChildFragmentManager());

        viewPager.setAdapter(viewPagerAdapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            int one = offset * 2 + lineWidth;

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //游标的移动和标题颜色的改变
                Animation animation = new TranslateAnimation(one
                        * current_index, one * position, 0, 0);
                animation.setFillAfter(true);
                animation.setDuration(300);
                ivCursor.startAnimation(animation);
                tvTitles.get(current_index).setTextColor(getResources().getColor(android.R.color.tertiary_text_dark));
                tvTitles.get(position).setTextColor(Color.RED);
                current_index = position;
                System.out.println(tvTitles.get(position).getText());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }

        });
    }

    public class NewsViewPagerAdapter extends FragmentStatePagerAdapter {
        public NewsViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            System.out.println("instantiateItem");
            return super.instantiateItem(container, position);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new SportNewsFragment();
                case 1:
                    return new SportNewsFragment();
                case 2:
                    return new SportNewsFragment();
                case 3:
                    return new SportNewsFragment();
                case 4:
                    return new SportNewsFragment();
            }
            return null;
        }

        @Override
        public int getCount() {
            return TITLE_COUNT;
        }

        @Override
        public int getItemPosition(Object object) {
            return PagerAdapter.POSITION_NONE;
        }
    }

}
