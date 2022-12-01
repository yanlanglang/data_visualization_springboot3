package com.ns4a.dv.repository;


import com.ns4a.dv.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @className: NewsRepository
 * @author: Yan Lang
 * @date: 2022/11/24
 * @description: 新闻资讯
 **/
public interface NewsRepository extends JpaRepository<News, Long> {


}
