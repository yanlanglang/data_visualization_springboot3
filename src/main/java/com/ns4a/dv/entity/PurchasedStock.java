package com.ns4a.dv.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

/**
 * @className: PurchasedStock
 * @author: Yan Lang
 * @date: 2022/11/28
 * @description: 购买的股票
 **/
@Data
@Entity
@Table(name = "t_purchased_stock")
public class PurchasedStock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnore
    private Consumer consumer;

    /*
        一对一中，PurchasedStock类来维护，则另外一个则不用写了;
        @cascade:添加级联关系
        @JoinColumn:
            name--->当前所映射的数据库表中，用什么字段名保存这个字段
            referencedColumnName--->在Stock映射类中，关联的属性名
     */
    //购买的股票篮
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "stock_id", referencedColumnName = "id")
    private Stock stock;

    //以当日开盘价为基准的收益信息
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "stock_yield_id", referencedColumnName = "id")
    private StockYield stockYield;
}
