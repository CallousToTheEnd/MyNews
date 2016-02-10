package com.lk.mynews.Fragment;

import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.lk.mynews.Config.Constant;
import com.lk.mynews.R;


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
    private View rootView;
    private Toolbar toolbar;
    private ImageView ivNewsToolBar;

    //    这个Adapter传入的FragmentManager要注意是getChildFragmentManager
//    否则切换到其他页面再切换回来列表不显示数据
    private NewsViewPagerAdapter viewPagerAdapter;

    //    viewpager当前选中的页数
    private int current_index = 0;
    //    线的实际宽度
    private static int lineWidth;
    private static int offset;
    //    从新闻页切换到其它页再切换回来时线的位置
    private int padding = 0;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        System.out.println("onCreateView");
        if (rootView != null) {
            ViewGroup parent = (ViewGroup) rootView.getParent();
            if (parent != null) {
                parent.removeView(rootView);
            }
            initIvCursor();
            return rootView;
        }
        rootView = inflater.inflate(R.layout.fragment_news, container, false);

        initView();

        return rootView;

    }

    private void initView() {

        toolbar = (Toolbar) rootView.findViewById(R.id.toolbarNewsFragment);
        toolbar.inflateMenu(R.menu.menu_main);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.main_menu_search:
                        Toast.makeText(getContext(), getString(R.string.main_menu__search),
                                Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.main_menu_info:
                        Toast.makeText(getContext(), getString(R.string.main_menu_info),
                                Toast.LENGTH_SHORT).show();
                        break;
                }
                return false;
            }
        });
        ivNewsToolBar = (ImageView) rootView.findViewById(R.id.ivNewsToolBar);
        ivNewsToolBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "返回顶部", Toast.LENGTH_SHORT).show();
            }
        });

        initIvCursor();

        rgTitle = (RadioGroup) rootView.findViewById(R.id.rgTitle);
        rbTitleSports = (RadioButton) rootView.findViewById(R.id.rbTitleSport);
        rbTitleApple = (RadioButton) rootView.findViewById(R.id.rbTitleApple);
        rbTitleInternational = (RadioButton) rootView.findViewById(R.id.rbTitleInternational);
        rbTitleSociology = (RadioButton) rootView.findViewById(R.id.rbTitleSociology);
        rbTitleTechnology = (RadioButton) rootView.findViewById(R.id.rbTitleTechnology);

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
        ivCursor = (ImageView) rootView.findViewById(R.id.ivCursor);
        // 获取图片宽度
        lineWidth = BitmapFactory.decodeResource(getResources(),
                R.drawable.cursor).getWidth();
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        // 获取屏幕宽度
        int screenWidth = dm.widthPixels;
        Matrix matrix = new Matrix();
        offset = (int) ((screenWidth / (float) 5 - lineWidth) / 2);
        matrix.postTranslate(offset, 0);
        // 设置初始位置
        padding = current_index * screenWidth / 5;
        ivCursor.setPadding(padding, 0, 0, 0);
        ivCursor.setImageMatrix(matrix);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("onDestroy");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        System.out.println("onDestroyView");
    }

    /**
     * 初始化ViewPager
     */
    private void initViewPager() {

        viewPager = (ViewPager) rootView.findViewById(R.id.viewPager);

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
                        * current_index - padding, one * position - padding, 0, 0);
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
            return Constant.NEWS_TYPE_COUNT;
        }

        @Override
        public int getItemPosition(Object object) {
            return PagerAdapter.POSITION_NONE;
        }
    }

}
