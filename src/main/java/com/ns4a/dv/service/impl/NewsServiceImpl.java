package com.ns4a.dv.service.impl;

import com.ns4a.dv.entity.News;
import com.ns4a.dv.repository.NewsRepository;
import com.ns4a.dv.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @className: NewsServiceImpl
 * @author: Yan Lang
 * @date: 2022/11/24
 * @description:
 **/
@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsRepository newsRepository;

    @Override
    public void addTodayNewses(List<News> newses) {
        newsRepository.saveAllAndFlush(newses);
    }

    @Override
    public Page<News> findNewsByPage(Pageable pageable) {
        return newsRepository.findAll(pageable);
    }
}
