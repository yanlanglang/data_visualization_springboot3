package com.ns4a.dv.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

/**
 * @className: StockYield
 * @author: Yan Lang
 * @date: 2022/11/28
 * @description: 股票收益（自选的股票optional_stock、购买的股票purchased_stock）
 **/
@Data
@Entity
@Table(name = "t_stock_yield")
public class StockYield {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //以当日开盘价为基准
    @Temporal(TemporalType.DATE)
    private Date createDate;

    //添加时的价格
    private float createPrice;


    public StockYield() {
    }

    public StockYield(Date createDate, float createPrice) {
        this.createDate = createDate;
        this.createPrice = createPrice;
    }
}
