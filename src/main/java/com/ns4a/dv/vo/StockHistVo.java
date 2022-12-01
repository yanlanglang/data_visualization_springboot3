package com.ns4a.dv.vo;

import lombok.Data;

/**
 * @className: StockHistVo
 * @author: Yan Lang
 * @date: 2022/11/24
 * @description: 封装请求StockHist接口的参数
 **/
@Data
public class StockHistVo {

    /**
     * 股票代码
     */
    private String symbol;

    /**
     * + daily：日线级别
     * + weekly：周线级别
     * + monthly：月线级别
     */
    private String period;

    /**
     * 即上市的时间 yyyyMMdd 注意，是字符串类型 && 不带短横线的伪日期
     */
    private String startDate;

    /**
     * 即当天的时间 yyyyMMdd 注意，是字符串类型 && 不带短横线的伪日期
     */
    private String endDate;

    /**
     * + qfq：前复权
     * + hfq：后复权
     * + ""：不复权
     */
    private String adjust;

    public StockHistVo(String symbol, String period, String startDate, String endDate, String adjust) {
        this.symbol = symbol;
        this.period = period;
        this.startDate = startDate;
        this.endDate = endDate;
        this.adjust = adjust;
    }

    /**
     * 重写toString()
     * @return
     */
    @Override
    public String toString() {
        return "symbol=" + symbol + "&"
                + "period=" + period + "&"
                + "start_date=" + startDate + "&"
                + "end_date=" + endDate + "&"
                + "adjust=" + adjust;
    }
}
