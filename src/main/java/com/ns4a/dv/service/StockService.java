package com.ns4a.dv.service;



import com.ns4a.dv.entity.Stock;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;

/**
 * @className: StockService
 * @author: Yan Lang
 * @date: 2022/11/25
 * @description:
 **/
public interface StockService {

    /**
     * 添加股票的基本信息
     * @param symbol 股票代码
     */
    Stock addStockAllInfo(String symbol) throws IOException, URISyntaxException, ParseException, InterruptedException;

}
