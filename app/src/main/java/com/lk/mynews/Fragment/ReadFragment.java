package com.lk.mynews.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.lk.mynews.R;

/**
 * Created by Mr.li on 2016-01-07.
 */
public class ReadFragment extends Fragment {

    private View rootView;
    private ViewPager viewPager;
    private RadioGroup radioGroup;
    private RadioButton rbRecommendRead, rbMyBook;

    private Toolbar toolbar;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container
            , Bundle savedInstanceState) {
        if (rootView != null) {
            ViewGroup parent = (ViewGroup) rootView.getParent();
            if (parent != null) {
                parent.removeView(rootView);
            }
            return rootView;
        }
        rootView = inflater.inflate(R.layout.fragment_read, container, false);
        initView();
        return rootView;
    }

    private void initView() {
        toolbar = (Toolbar) rootView.findViewById(R.id.toolbarReadFragment);
        toolbar.inflateMenu(R.menu.menu_read);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.read_menu_add:
                        Toast.makeText(getContext(), getString(R.string.read_menu_add), Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });
        radioGroup = (RadioGroup) rootView.findViewById(R.id.rgReadToolbar);
        rbRecommendRead = (RadioButton) rootView.findViewById(R.id.rbReadToolbarRecommendRead);
        rbMyBook = (RadioButton) rootView.findViewById(R.id.rbReadToolbarMyBook);
        viewPager = (ViewPager) rootView.findViewById(R.id.viewPagerReadFragment);
        viewPager.setAdapter(new MyViewPagerAdapter(getChildFragmentManager()));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        rbRecommendRead.setChecked(true);
                        break;
                    case 1:
                        rbMyBook.setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rbReadToolbarRecommendRead:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.rbReadToolbarMyBook:
                        viewPager.setCurrentItem(1);
                        break;
                }
            }
        });
    }

    class MyViewPagerAdapter extends FragmentStatePagerAdapter {

        public MyViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new RecommentReadFragment();
                case 1:
                    return new RecommentReadFragment();
            }
            return null;
        }

        @Override
        public int getCount() {
            return 2;
        }
    }

}
