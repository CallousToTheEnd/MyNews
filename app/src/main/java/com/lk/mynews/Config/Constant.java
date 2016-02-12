package com.lk.mynews.Config;

import com.lk.mynews.Bean.NewsContentBean;
import com.lk.mynews.Bean.PcFragmentItemBean;
import com.lk.mynews.Bean.ReadFragmentItemBean;
import com.lk.mynews.Bean.TopicFragmentItemBean;
import com.lk.mynews.Bean.VaFragmentItemBean;
import com.lk.mynews.MyApplication;
import com.lk.mynews.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Request;
import okhttp3.Response;

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

    public static final String BAIDU_API_KEY = "ef3ff76a03a6c1b2e24d3a75fed55658";
    public static final String BAIDU_API_URL = "http://apis.baidu.com/txapi/world/world?&num=11&page=1";
    public static final String BAIDU_API_HEADER = "apikey";

    public static final String SPORTS_IFENG_URL = "http://sports.ifeng.com/";

    private static List<PcFragmentItemBean> pcItems = new ArrayList<>();
    private static List<ReadFragmentItemBean> readItems = new ArrayList<>();
    private static List<TopicFragmentItemBean> topicItems = new ArrayList<>();
    private static List<VaFragmentItemBean> videoItems = new ArrayList<>();

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

    public static List<ReadFragmentItemBean> getReads() {
        if (readItems.size() == 0) {
            ReadFragmentItemBean read1 = new ReadFragmentItemBean(
                    "http://img4.imgtn.bdimg.com/it/u=3981050270,171872606&fm=21&gp=0.jpg",
                    "哒哒良品", "中国“第五大发明”横空出世？据说能让你撕逼所向披靡");
            ReadFragmentItemBean read2 = new ReadFragmentItemBean(
                    "http://img2.imgtn.bdimg.com/it/u=914584203,2933819813&fm=21&gp=0.jpg",
                    "中国日报网", "当零下11℃遇上孩子的脑洞各自操心的事距离有多远");
            ReadFragmentItemBean read3 = new ReadFragmentItemBean(
                    "http://img2.imgtn.bdimg.com/it/u=1206262111,3132591149&fm=21&gp=0.jpg",
                    "人民网", "小学生谈二孩：我是家里小皇帝 再生就是篡位");
            ReadFragmentItemBean read4 = new ReadFragmentItemBean(
                    "http://img2.imgtn.bdimg.com/it/u=2160868544,206776260&fm=11&gp=0.jpg",
                    "环球趣闻", "美国喵星人胸口有个超萌黑色大爱心 因太内向从没离开收容");
            ReadFragmentItemBean read5 = new ReadFragmentItemBean(
                    "http://img4.imgtn.bdimg.com/it/u=3476130273,879846185&fm=11&gp=0.jpg",
                    "哒哒", "键盘上藏着一排秘密 ASDFGHJKL代表爱情哲理？");
            ReadFragmentItemBean read6 = new ReadFragmentItemBean(
                    "http://img0.imgtn.bdimg.com/it/u=4101061768,814675782&fm=11&gp=0.jpg",
                    "哒哒", "如果看不到这一点，你就白看了《太子妃》");
            ReadFragmentItemBean read7 = new ReadFragmentItemBean(
                    "http://img3.imgtn.bdimg.com/it/u=2593334733,1222889682&fm=11&gp=0.jpg",
                    "神秘的地球", "澳洲海岸惊现长达7米的巨无霸大白鲨");
            ReadFragmentItemBean read8 = new ReadFragmentItemBean(
                    "http://img4.imgtn.bdimg.com/it/u=1588131021,3485757191&fm=11&gp=0.jpg",
                    "华股财经", "揭秘网络主播：60万人观看换衣 土豪一晚砸百万");
            ReadFragmentItemBean read9 = new ReadFragmentItemBean(
                    "http://img3.imgtn.bdimg.com/it/u=4131541063,3115516794&fm=11&gp=0.jpg",
                    "环球网", "野狼群夜袭河北村庄 50多只羊惨死");
            ReadFragmentItemBean read10 = new ReadFragmentItemBean(
                    "http://img1.imgtn.bdimg.com/it/u=3396739401,4195304750&fm=11&gp=0.jpg",
                    "哒哒", "80岁奶奶的表演一度被评委叫停，但他接下来竟...");
            readItems.add(read1);
            readItems.add(read2);
            readItems.add(read3);
            readItems.add(read4);
            readItems.add(read5);
            readItems.add(read6);
            readItems.add(read7);
            readItems.add(read8);
            readItems.add(read9);
            readItems.add(read10);
            return readItems;
        } else {
            return readItems;
        }
    }

    public static List<TopicFragmentItemBean> getTopics() {
        if (topicItems.size() == 0) {
            TopicFragmentItemBean topic1 = new TopicFragmentItemBean("贾子冰"
                    , "http://img2.imgtn.bdimg.com/it/u=423990693,1862147663&fm=21&gp=0.jpg",
                    "http://img0.imgtn.bdimg.com/it/u=1820862732,266445318&fm=21&gp=0.jpg",
                    "我是从事气象工作的贾子冰，关于BOSS寒潮、天气以及“解冻”时间的问题问我吧！",
                    1940, 1, 1);
            TopicFragmentItemBean topic2 = new TopicFragmentItemBean("宁嫁雨"
                    , "http://img2.imgtn.bdimg.com/it/u=3279850656,3255133830&fm=21&gp=0.jpg",
                    "http://img3.imgtn.bdimg.com/it/u=1485766533,1166258880&fm=21&gp=0.jpg",
                    "我是南开大学博士生导师宁嫁雨，关于魏晋风度、世说新语、传统文化与文学，问我吧！",
                    1646, 2, 1);
            TopicFragmentItemBean topic3 = new TopicFragmentItemBean("一本道"
                    , "http://img2.imgtn.bdimg.com/it/u=1185953656,3727390904&fm=21&gp=0.jpg",
                    "http://img2.imgtn.bdimg.com/it/u=2425526525,1316991795&fm=21&gp=0.jpg",
                    "我是不正经小百科，擅长一本正经的胡说八道，承接任何问题，问我吧！",
                    47000, 2, 1);
            TopicFragmentItemBean topic4 = new TopicFragmentItemBean("宋清辉"
                    , "http://img4.imgtn.bdimg.com/it/u=1992888496,2050263948&fm=21&gp=0.jpg",
                    "http://img5.imgtn.bdimg.com/it/u=1363224529,3265910996&fm=15&gp=0.jpg",
                    "我是经济学家宋清辉，中央经济工作、楼市去库存，创业就业、新常态等问题，问我吧！",
                    7101, 3, 1);
            TopicFragmentItemBean topic5 = new TopicFragmentItemBean("网易跟帖"
                    , "http://img2.imgtn.bdimg.com/it/u=3676054554,3220982082&fm=21&gp=0.jpg",
                    "http://img4.imgtn.bdimg.com/it/u=1129330031,636755483&fm=21&gp=0.jpg",
                    "我是网易跟帖的小编，关于跟帖的一切问题，问我吧！",
                    6243, 5, 1);
            topicItems.add(topic1);
            topicItems.add(topic2);
            topicItems.add(topic3);
            topicItems.add(topic4);
            topicItems.add(topic5);
            return topicItems;
        } else {
            return topicItems;
        }

    }

    public static List<VaFragmentItemBean> getVideos() {
        if (videoItems.size() == 0) {
            VaFragmentItemBean video1 = new VaFragmentItemBean("大柯基教小柯基如何坐下",
                    "萌化了", 32, 9437, 37);
            VaFragmentItemBean video2 = new VaFragmentItemBean("荷兰青年街头向华人撒奶粉",
                    "疑不满华人抢购奶粉", 120, 11000, 558);
            VaFragmentItemBean video3 = new VaFragmentItemBean("笑死！爸爸喝多了个儿子做游戏",
                    "当爹的够萌", 18, 9143, 19);
            videoItems.add(video1);
            videoItems.add(video2);
            videoItems.add(video3);
            return videoItems;
        } else {
            return videoItems;
        }

    }

}
