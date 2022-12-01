package com.ns4a.dv.service;



import com.ns4a.dv.entity.Stock;
import com.ns4a.dv.entity.StockHist;

import java.util.List;

/**
 * @className: StockHistService
 * @author: Yan Lang
 * @date: 2022/11/26
 * @description: 股票历史信息
 **/
public interface StockHistService {

    /**
     * 根据股票代码查找股票历史信息
     * @param stock 股票基本信息
     * @return
     */
    List<StockHist> findStockHistInfo(Stock stock);

}
