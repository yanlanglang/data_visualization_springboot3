package com.ns4a.dv.vo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @className: RealTimeStock
 * @author: Yan Lang
 * @date: 2022/11/28
 * @description: http://127.0.0.1:8888/api/public/stock_zh_a_spot_em 接口返回的实例
 **/
@Data
public class RealTimeStockVo {

    //序号
    @JSONField(name = "序号")
    private Long num;

    //stock code：代码
    @JSONField(name = "代码")
    private String sc;

    //stock name：名称
    @JSONField(name = "名称")
    private String sn;

    //new price：最新价
    @JSONField(name = "最新价")
    private float np;

    //change percent：涨跌幅
    @JSONField(name = "涨跌幅")
    private float cp;

    //change amount：涨跌额
    @JSONField(name = "涨跌额")
    private float ca;

    //成交量
    @JSONField(name = "成交量")
    private Long volume;

    //成交额
    @JSONField(name = "成交额")
    private BigDecimal turnover;

    //振幅
    @JSONField(name = "振幅")
    private float amplitude;

    //最高
    @JSONField(name = "最高")
    private float highest;

    //最低
    @JSONField(name = "最低")
    private float lowest;

    //今开
    @JSONField(name = "今开")
    private float open;

    //昨收
    @JSONField(name = "昨收")
    private float close;

    //量比quantity relative ratio
    @JSONField(name = "量比")
    private float qrr;

    //换手率
    @JSONField(name = "换手率")
    private float tun;

    //市盈率-动态
    @JSONField(name = "市盈率-动态")
    private float ttm;

    //市净率
    @JSONField(name = "市净率")
    private float pb;

    //aggregate market value：总市值
    @JSONField(name = "总市值")
    private BigDecimal amv;

    //traded_market_value：流通市值
    @JSONField(name = "流通市值")
    private BigDecimal rmv;

    //涨速
    @JSONField(name = "涨速")
    private float accer;

    //5分钟涨跌 5 minutes up and down
    @JSONField(name = "5分钟涨跌")
    private float fmuad;

    //60日涨跌幅 Sixty days amplitude
    @JSONField(name = "60日涨跌幅")
    private float sda;

    //年初至今涨跌幅 this year amplitude
    @JSONField(name = "年初至今涨跌幅")
    private float tya;

}
