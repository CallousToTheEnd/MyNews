package com.lk.mynews.Config;

import com.lk.mynews.Bean.PcFragmentItemBean;
import com.lk.mynews.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr.li on 2016/2/3.
 */
public class Constant {

    public static int app_statebar_height;
    public static int screen_width;
    public static final int NEWS_TYPE_COUNT = 5;

    public static final int TOPIC_ITEM_TYPE_TECHNOLOGY = 1;
    public static final int TOPIC_ITEM_TYPE_HUMANITY = 2;
    public static final int TOPIC_ITEM_TYPE_FINANCE = 3;
    public static final int TOPIC_ITEM_TYPE_HEALTHY = 4;
    public static final int TOPIC_ITEM_TYPE_OTHER = 5;

    private static List<PcFragmentItemBean> pcItems = new ArrayList<>();

    public static List<PcFragmentItemBean> getPcItems() {
        if (pcItems.size() == 0) {
            pcItems = new ArrayList<>();
            PcFragmentItemBean item1 = new PcFragmentItemBean(R.drawable.biz_pc_main_message
                    , R.string.main_message, "");
            PcFragmentItemBean item2 = new PcFragmentItemBean(R.drawable.biz_pc_main_goldmall
                    , R.string.main_goldmall, "");
            PcFragmentItemBean item3 = new PcFragmentItemBean(R.drawable.biz_pc_main_task
                    , R.string.main_task, "");
            PcFragmentItemBean item4 = new PcFragmentItemBean(R.drawable.biz_pc_main_wallet
                    , R.string.main_wallet, "");
            PcFragmentItemBean item5 = new PcFragmentItemBean(R.drawable.biz_pc_main_offline
                    , R.string.application_offline, "");
            PcFragmentItemBean item6 = new PcFragmentItemBean(R.drawable.biz_pc_main_promo
                    , R.string.main_promo, "");
            PcFragmentItemBean item7 = new PcFragmentItemBean(R.drawable.biz_pc_main_gamecenter
                    , R.string.main_gamecenter, "网易游戏·年度大杂烩");
            PcFragmentItemBean item8 = new PcFragmentItemBean(R.drawable.biz_pc_main_mail
                    , R.string.main_mail, "");
            PcFragmentItemBean item9 = new PcFragmentItemBean(R.drawable.biz_pc_main_invitefriends
                    , R.string.main_invitefriends, "邀请好友送百兆流量");
            pcItems.add(item1);
            pcItems.add(item2);
            pcItems.add(item3);
            pcItems.add(item4);
            pcItems.add(item5);
            pcItems.add(item6);
            pcItems.add(item7);
            pcItems.add(item8);
            pcItems.add(item9);
        } else {
            return pcItems;
        }

        return pcItems;
    }

}
