package com.ns4a.dv.utils.akshare;

import com.alibaba.fastjson2.JSON;
import com.ns4a.dv.entity.News;
import com.ns4a.dv.utils.HttpClientUtil;


import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpResponse;
import java.util.List;

/**
 * @className: NewsUtil
 * @author: Yan Lang
 * @date: 2022/11/24
 * @description: 请求 News--->stock_telegraph_cls 接口，并获取数据
 **/
public class NewsUtil {

    private static final String API_NEWS = "http://127.0.0.1:8888/api/public/stock_telegraph_cls";

    /**
     * 获取List<News>
     * @return news实例的集合
     * @throws URISyntaxException
     * @throws IOException
     * @throws InterruptedException
     */
    public static List<News> getNewses() throws URISyntaxException, IOException, InterruptedException {
        //获取Response实例
        HttpResponse<String> response = new HttpClientUtil().getResponse(API_NEWS);
        //利用fastJSON，返回News实例集合的数据
        List<News> newses = JSON.parseArray(response.body(), News.class);
        //格式化日期pubDate
        for (News news : newses) {
            //格式化pubData
            String s = news.getPubDate().split("T")[0];
            news.setPubDate(s);
        }
        //返回数据
        return newses;
    }

    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
        List<News> newses = getNewses();
        for (News news : newses) {
            System.out.println(news);
        }
    }


}
