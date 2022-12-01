package com.ns4a.dv.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @className: OptionalStockVo
 * @author: Yan Lang
 * @date: 2022/11/28
 * @description: 自选股的部分信息类，含股票代码、股票名称、当前价、历史最高价、历史最低价、自选收益、行业、流通市值
 **/
@Data
public class OptionalStockVo {

    /**
     * sc、sn、industry、rmv由OptionalStock中的Stock获取
     */
    //股票代码
    private String sc;
    //股票名字
    private String sn;
    //行业
    private String industry;
    //流通市值
    private BigDecimal rmv;


    //当前价格：需调用StockInfoUtil.getRealTimeStock(sc);
    private float np;

    /**
     *
     */
    //历史最高价
    private float histHighest;
    //历史最低价
    private float histLowest;
    //relative position：相对位置 = (当前位置-历史最低价) / (历史最高价-历史最低价)
    //[0,1]，需得到百分比类型
    private float rp;

    /**
     * 收益
     * 由OptionalStock中的StockYield获取
     * 百分比类型
     */
    private float yield;

    public OptionalStockVo() {
    }

    public OptionalStockVo(String sc, String sn, String industry, BigDecimal rmv, float np, float histHighest, float histLowest, float rp, float yield) {
        this.sc = sc;
        this.sn = sn;
        this.industry = industry;
        this.rmv = rmv;
        this.np = np;
        //保留两位小数
        this.histHighest = Float.parseFloat(String.format("%.2f", histHighest)) ;
        this.histLowest = Float.parseFloat(String.format("%.2f", histLowest)) ;
        //保留四位小数
        this.rp = Float.parseFloat(String.format("%.2f", rp)) * 100;
        this.yield = Float.parseFloat(String.format("%.4f", yield)) * 100;
    }
}
