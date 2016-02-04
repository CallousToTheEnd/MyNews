package com.lk.mynews.Activity;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;

import com.lk.mynews.Bean.BottomTabBean;
import com.lk.mynews.Config.Constant;
import com.lk.mynews.Fragment.NewsFragment;
import com.lk.mynews.Fragment.PcFragment;
import com.lk.mynews.Fragment.ReadFragment;
import com.lk.mynews.Fragment.TopicFragment;
import com.lk.mynews.Fragment.VaFragment;
import com.lk.mynews.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FragmentTabHost tabHost;
    private LayoutInflater mInflater;
    private List<BottomTabBean> tabList = new ArrayList<>(4);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if (hasFocus) {
            if (Constant.app_statebar_height == 0 &&
                    Constant.screen_width == 0) {
                Rect rect = new Rect();
                getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
                Constant.app_statebar_height = rect.top;
                Constant.screen_width= rect.right;
            }
        }
        super.onWindowFocusChanged(hasFocus);
    }

    private void initView() {

        tabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        tabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
        mInflater = LayoutInflater.from(this);

        BottomTabBean tabNews = new BottomTabBean(R.string.tab_title_news, R.drawable.selector_tab_news, NewsFragment.class);
        BottomTabBean tabRead = new BottomTabBean(R.string.tab_title_read, R.drawable.select_tab_read, ReadFragment.class);
        BottomTabBean tabVa = new BottomTabBean(R.string.tab_title_va, R.drawable.select_tab_va, VaFragment.class);
        BottomTabBean tabTopic = new BottomTabBean(R.string.tab_title_topic, R.drawable.select_tab_topic, TopicFragment.class);
        BottomTabBean tabPc = new BottomTabBean(R.string.tab_title_pc, R.drawable.select_tab_pc, PcFragment.class);

        tabList.add(tabNews);
        tabList.add(tabRead);
        tabList.add(tabVa);
        tabList.add(tabTopic);
        tabList.add(tabPc);


        // Add TabSpec to TabHost
        for (BottomTabBean tab : tabList) {

            TabHost.TabSpec tabSpec = tabHost.newTabSpec(getString(tab.getTitle()));

            tabSpec.setIndicator(buildIndicator(tab));

            tabHost.addTab(tabSpec, tab.getFragment(), null);

        }

        tabHost.getTabWidget().setShowDividers(LinearLayout.SHOW_DIVIDER_NONE);
        tabHost.setCurrentTab(0);

    }

    /**
     * Build indicator required TabSpec
     *
     * @param tab
     * @return
     */
    private View buildIndicator(BottomTabBean tab) {

        View view = mInflater.inflate(R.layout.bottom_tab_indicator, null);
        TextView tvTabTitle = (TextView) view.findViewById(R.id.tvTabTitle);
        ImageView ivTabIcon = (ImageView) view.findViewById(R.id.ivTab);

        tvTabTitle.setText(tab.getTitle());
        ivTabIcon.setBackgroundResource(tab.getIcon());

        return view;
    }

}
