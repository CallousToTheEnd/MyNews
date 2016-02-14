package com.lk.mynews.utils;

import com.google.gson.Gson;
import com.lk.mynews.Bean.NewsContentBean;
import com.lk.mynews.Bean.SociologyJsonBean.Contentlist;
import com.lk.mynews.Bean.SociologyJsonBean.SociologyJsonBean;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr.li on 2016/2/12.
 */
public class ParseJsonUtils {

    public List<NewsContentBean> parseNewsContentJson(String json) {
        List<NewsContentBean> news = new ArrayList<>();
        if (!json.equals("")) {
            try {
                JSONObject jsonObject = new JSONObject(json);

                System.out.println(jsonObject.toString());

                for (int i = 0; i < jsonObject.length() - 2; i++) {
                    JSONObject jsonContent = jsonObject.getJSONObject(String.valueOf(i));
                    news.add(new NewsContentBean(jsonContent.getString("title"),
                            jsonContent.getString("picUrl"),
                            jsonContent.getString("time"),
                            jsonContent.getString("url")));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return news;
    }

    public List<Contentlist> parseSociologyJson(String json) {
        Gson gson = new Gson();
        SociologyJsonBean sociologyJsonBean = gson.fromJson(json, SociologyJsonBean.class);
        List<Contentlist> contentlists = sociologyJsonBean
                .getShowapi_res_body()
                .getPagebean()
                .getContentlist();
        return contentlists;
    }

}
