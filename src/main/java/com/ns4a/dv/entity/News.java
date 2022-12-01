package com.ns4a.dv.entity;

import com.alibaba.fastjson.annotation.JSONField;
import jakarta.persistence.*;
import lombok.Data;


/**
 * @className: News
 * @author: Yan Lang
 * @date: 2022/11/24
 * @description: [@Data: lombok, @JSONField: fastJSON] 新闻资讯
 **/
@Data
@Entity
@Table(name = "t_news")
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JSONField(name = "标题")
    private String title;

    @JSONField(name = "内容")
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(columnDefinition="text")
    private String content;

    @JSONField(name = "发布时间")
    private String pubTime;

    @JSONField(name = "发布日期")
    private String pubDate;



}
