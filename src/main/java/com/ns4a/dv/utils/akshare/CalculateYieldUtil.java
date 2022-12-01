package com.ns4a.dv.utils.akshare;


import com.ns4a.dv.vo.RealTimeStockVo;

/**
 * @className: CalculateYieldUtil
 * @author: Yan Lang
 * @date: 2022/11/28
 * @description:    计算收益的工具包
 **/
public class CalculateYieldUtil {

    /**
     * 获取收益
     * @param sc 股票代码
     * @return
     */
    public static float getYield(String sc, float createPrice) throws Exception {
        //当前的股票的所有信息
        RealTimeStockVo realTimeStockVo = StockInfoUtil.getRealTimeStock(sc);
        //当前的价格
        float nowPrice = realTimeStockVo.getNp();
        //返回收益百分比
        return (nowPrice-createPrice)/100 ;
    }



}
