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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.li.fragmenttabhosttest.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 新闻页面
 * Created by Mr.li on 2016-01-07.
 */
public class NewsFragment extends Fragment {

    private RadioGroup rgTitle;
    private RadioButton rbTitleSports, rbTitleInternational, rbTitleTechnology,
            rbTitleSociology, rbTitleApple;
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

        rgTitle = (RadioGroup) view.findViewById(R.id.rgTitle);
        rbTitleSports = (RadioButton) view.findViewById(R.id.rbTitleSport);
        rbTitleApple = (RadioButton) view.findViewById(R.id.rbTitleApple);
        rbTitleInternational = (RadioButton) view.findViewById(R.id.rbTitleInternational);
        rbTitleSociology = (RadioButton) view.findViewById(R.id.rbTitleSociology);
        rbTitleTechnology = (RadioButton) view.findViewById(R.id.rbTitleTechnology);

        initViewPager();

        rgTitle.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rbTitleSport:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.rbTitleApple:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.rbTitleSociology:
                        viewPager.setCurrentItem(2);
                        break;
                    case R.id.rbTitleTechnology:
                        viewPager.setCurrentItem(3);
                        break;
                    case R.id.rbTitleInternational:
                        viewPager.setCurrentItem(4);
                        break;
                }
            }
        });


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
//                RadioButton的设置
                switch (position) {
                    case 0:
                        rbTitleSports.setChecked(true);
                        break;
                    case 1:
                        rbTitleApple.setChecked(true);
                        break;
                    case 2:
                        rbTitleSociology.setChecked(true);
                        break;
                    case 3:
                        rbTitleTechnology.setChecked(true);
                        break;
                    case 4:
                        rbTitleInternational.setChecked(true);
                        break;
                }
                current_index = position;
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
            return super.instantiateItem(container, position);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new SportNewsFragment();
                case 1:
                    return new AppleNewsFragment();
                case 2:
                    return new SociologyNewsFragment();
                case 3:
                    return new TechnologyNewsFragment();
                case 4:
                    return new InternationalNewsFragment();
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
