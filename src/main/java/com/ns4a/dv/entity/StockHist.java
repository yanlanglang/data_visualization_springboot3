package com.ns4a.dv.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @className: StockHist
 * @author: Yan Lang
 * @date: 2022/11/24
 * @description: Stock History 股票历史信息
 **/
@Data
@Entity
@Table(name = "t_stock_hist")
public class StockHist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    //数据的时间
    @JSONField(format = "yyyy-MM-dd", name = "日期")
    private Date date;

    //收盘价
    @JSONField(name = "开盘")
    private float open;

    //收盘价
    @JSONField(name = "收盘")
    private float close;

    //最高价
    @JSONField(name = "最高")
    private float highest;

    //最低价
    @JSONField(name = "最低")
    private float lowest;

    //成交量
    @JSONField(name = "成交量")
    private Long volume;

    //成交额
    @JSONField(name = "成交额")
    private BigDecimal turnover;

    //振幅
    @JSONField(name = "振幅")
    private float amplitude;

    //change percent:涨跌幅
    @JSONField(name = "涨跌幅")
    private float cp;

    //change amount:涨跌额
    @JSONField(name = "涨跌额")
    private float ca;

    //换手率
    @JSONField(name = "换手率")
    private float tun;

    //在多的一方中，忽略一的一方
    @ManyToOne
    @JsonIgnore
    private Stock stock;

    public StockHist() {
    }

    public StockHist(Date date, float open, float close, float highest, float lowest, Long volume, BigDecimal turnover, float amplitude, float cp, float ca, float tun) {
        this.date = date;
        this.open = open;
        this.close = close;
        this.highest = highest;
        this.lowest = lowest;
        this.volume = volume;
        this.turnover = turnover;
        this.amplitude = amplitude;
        this.cp = cp;
        this.ca = ca;
        this.tun = tun;
    }

    public StockHist(Long id, Date date, float open, float close, float highest, float lowest, Long volume, BigDecimal turnover, float amplitude, float cp, float ca, float tun) {
        this.id = id;
        this.date = date;
        this.open = open;
        this.close = close;
        this.highest = highest;
        this.lowest = lowest;
        this.volume = volume;
        this.turnover = turnover;
        this.amplitude = amplitude;
        this.cp = cp;
        this.ca = ca;
        this.tun = tun;
    }

    @Override
    public String toString() {
        return "StockHist{" +
                "id=" + id +
                ", date=" + date +
                ", open=" + open +
                ", close=" + close +
                ", highest=" + highest +
                ", lowest=" + lowest +
                ", volume=" + volume +
                ", turnover=" + turnover +
                ", amplitude=" + amplitude +
                ", cp=" + cp +
                ", ca=" + ca +
                ", tun=" + tun +
                '}';
    }
}
