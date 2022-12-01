package com.ns4a.dv.controller;

import com.ns4a.dv.entity.News;
import com.ns4a.dv.service.NewsService;
import com.ns4a.dv.utils.ResultJsonUtil;
import com.ns4a.dv.utils.akshare.NewsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

/**
 * @className: NewsController
 * @author: Yan Lang
 * @date: 2022/11/24
 * @description:
 **/
@Controller
@RequestMapping("/news")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @PostMapping("/add")
    public @ResponseBody
    ResultJsonUtil add() throws URISyntaxException, IOException, InterruptedException {
        List<News> newses = NewsUtil.getNewses();
        newsService.addTodayNewses(newses);

        return ResultJsonUtil.ok("已成功添加一批新闻资讯！");
    }

    @GetMapping("/find")
    public @ResponseBody
    ResultJsonUtil find(@PageableDefault(size = 20, sort = {"id"}, direction = Sort.Direction.DESC)Pageable pageable){

        System.out.println(pageable.toString());
        Page<News> newsPage = newsService.findNewsByPage(pageable);
        return ResultJsonUtil.ok(newsPage);
    }

}
