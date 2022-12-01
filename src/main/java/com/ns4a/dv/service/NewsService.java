package com.ns4a.dv.service;


import com.ns4a.dv.entity.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @className: NewsService
 * @author: Yan Lang
 * @date: 2022/11/24
 * @description:
 **/
public interface NewsService {

    /**
     * 添加当天的新闻快讯
     * @param newses
     * @return
     */
    void addTodayNewses(List<News> newses);

    /**
     * 获取分页之后的新闻快讯
     * @param pageable
     * @return
     */
    Page<News> findNewsByPage(Pageable pageable);
}
