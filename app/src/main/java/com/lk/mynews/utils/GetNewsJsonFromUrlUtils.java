package com.lk.mynews.utils;

import android.app.Activity;

import com.lk.mynews.Bean.SportNewsSlideBean;
import com.lk.mynews.Config.Config;
import com.lk.mynews.Config.Constant;
import com.lk.mynews.MyApplication;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Mr.li on 2016/2/12.
 */
public class GetNewsJsonFromUrlUtils {

    /**
     * 获得凤凰体育首页的网页源码
     * @return  凤凰体育网页源码
     * @throws IOException
     */
    public String loadSlider() throws IOException {
        List<SportNewsSlideBean> slide = new ArrayList<>();
        Request request = new Request.Builder()
                .url(Constant.SPORTS_IFENG_URL)
                .build();

        Response response = Config.myApplication.okHttpClient.newCall(request).execute();
        if (response.isSuccessful()) {
            String json = response.body().string();
            return json;
        }
        return null;
    }

    public String loadSportNews() throws IOException {

        Request request = new Request.Builder()
                .url(Constant.BAIDU_API_URL)
                .addHeader(Constant.BAIDU_API_HEADER, Constant.BAIDU_API_KEY)
                .build();

        Response response = Config.myApplication.okHttpClient.newCall(request).execute();
        if (response.isSuccessful()) {
            String json = response.body().string();
            return json;
        }
        return null;
    }

    /**
     * 获取Json数据
     * @param channelId 频道名称，showapi可查询
     * @return
     * @throws IOException
     */
    public  String LoadNewsListJson(String channelId) throws IOException {
        String url = Constant.SHOWAPI_API_URL + "?channelId=" + channelId;
        Request request = new Request.Builder()
                .url(url)
                .addHeader(Constant.BAIDU_API_HEADER, Constant.BAIDU_API_KEY)
                .build();
        Response response = Config.myApplication.okHttpClient.newCall(request).execute();
        if (response.isSuccessful()) {
            String json = response.body().string();
            return json;
        }

        return null;
    }

}
