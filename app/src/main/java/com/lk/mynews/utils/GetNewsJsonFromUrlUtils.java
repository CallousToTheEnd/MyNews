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
import okhttp3.Response;

/**
 * Created by Mr.li on 2016/2/12.
 */
public class GetNewsJsonFromUrlUtils {

    public List<SportNewsSlideBean> loadSlider() throws IOException {
        List<SportNewsSlideBean> slide = new ArrayList<>();
        Document doc = Jsoup.connect(Constant.SPORTS_IFENG_URL).get();
        Element slideElt = doc.getElementById("slide");
        Elements slideNewsPicElts = slideElt.getElementsByClass("pic");
        Elements slideNewsTxtElts = slideElt.getElementsByClass("txt");
        int sliderSize = slideNewsPicElts.size();
        for (int i = 0; i < sliderSize; i++) {
            String slidePicUrl = slideNewsPicElts.get(i).getElementsByTag("img").attr("src");
            String slideLink = slideNewsPicElts.get(i).getElementsByTag("a").attr("href") + "#p=1";
            String slideDesc = slideNewsTxtElts.get(i).text();
            SportNewsSlideBean slideBean = new SportNewsSlideBean(slidePicUrl, slideLink, slideDesc);
            slide.add(slideBean);
        }
        return slide;
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

}
