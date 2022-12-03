package com.ns4a.dv.entity;

import com.alibaba.fastjson.annotation.JSONField;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @className: Stock
 * @author: Yan Lang
 * @date: 2022/11/23
 * @description: 股票基本信息
 **/
@Data
@Entity
@Table(name = "t_stock")
public class Stock implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //aggregate market value：总市值
    private BigDecimal amv;

    //traded_market_value：流通市值
    private BigDecimal rmv;

    //行业
    private String industry;

    //time to market：上市时间
    @JSONField(format = "yyyy-MM-dd")
    private Date ttm;

    //stock code：股票代码
    private String sc;

    //stock name：股票简称
    private String sn;

    //Total stock issue：总股本
    private BigDecimal tsi;

    //circulation stock：流通股
    private BigDecimal cs;

    /**
     * 一只股票含很多天的历史数据信息
     * 在查询股票的基本信息时，需要查找股票的历史数据信息
     */
    @OneToMany(mappedBy = "stock")
    private List<StockHist> stockHists;



    public Stock() {
    }

    public Stock(BigDecimal amv, BigDecimal rmv, String industry, Date ttm, String sc, String sn, BigDecimal tsi, BigDecimal cs) {
        this.amv = amv;
        this.rmv = rmv;
        this.industry = industry;
        this.ttm = ttm;
        this.sc = sc;
        this.sn = sn;
        this.tsi = tsi;
        this.cs = cs;
    }

    public Stock(Long id, BigDecimal amv, BigDecimal rmv, String industry, Date ttm, String sc, String sn, BigDecimal tsi, BigDecimal cs) {
        this.id = id;
        this.amv = amv;
        this.rmv = rmv;
        this.industry = industry;
        this.ttm = ttm;
        this.sc = sc;
        this.sn = sn;
        this.tsi = tsi;
        this.cs = cs;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "id=" + id +
                ", amv=" + amv +
                ", rmv=" + rmv +
                ", industry='" + industry + '\'' +
                ", ttm=" + ttm +
                ", sc='" + sc + '\'' +
                ", sn='" + sn + '\'' +
                ", tsi=" + tsi +
                ", cs=" + cs +
                '}';
    }
}
