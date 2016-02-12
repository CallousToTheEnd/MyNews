package com.lk.mynews.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by Mr.li on 2016/2/12.
 */
public class JsoupUtils {

    /**
     * 通过Jsoup解析新闻详情网页的内容
     *
     * @param url
     * @return 返回Resource和Content的数组，resourceAndContent[0]代表Content，resourceAndContent[1]代表Resource
     */
    public String[] parseSportNewsSourceAndContent(String url) {
        String[] resourceAndContent = new String[2];
        try {
            if (url != null && !url.equals("")) {
                Document doc = Jsoup.connect(url).get();
                //                    正文内容
                Elements newsContentElt = doc.getElementsByClass("para");
                if (!newsContentElt.isEmpty()) {
                    resourceAndContent[0] = "";
                    for (Element e : newsContentElt) {
                        resourceAndContent[0] += e.text() + "\n\n" + "\u3000\u3000";
                    }
                } else {
                    resourceAndContent[0] = "加载失败";
                }
//                    来源
                Elements newsSourceElt = doc.getElementsByClass("ori");
                if (!newsSourceElt.isEmpty()) {
                    resourceAndContent[1] = newsSourceElt.text();
                } else {
                    resourceAndContent[1] = "未知";
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resourceAndContent;
    }

}
