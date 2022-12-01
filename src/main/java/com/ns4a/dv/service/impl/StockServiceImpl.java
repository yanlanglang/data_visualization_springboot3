package com.ns4a.dv.service.impl;


import com.ns4a.dv.entity.Stock;
import com.ns4a.dv.entity.StockHist;
import com.ns4a.dv.repository.StockHistRepository;
import com.ns4a.dv.repository.StockRepository;
import com.ns4a.dv.service.StockService;
import com.ns4a.dv.utils.DateTimeUtil;
import com.ns4a.dv.utils.akshare.StockInfoUtil;
import org.apache.commons.collections4.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @className: StockService
 * @author: Yan Lang
 * @date: 2022/11/25
 * @description:
 **/
@Service
public class StockServiceImpl implements StockService {

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private StockHistRepository stockHistRepository;


    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Stock addStockAllInfo(String symbol) throws IOException, URISyntaxException, ParseException, InterruptedException {
        //根据sc：股票代码，来 查验t_stock中是否已经含有该股票的基本信息
        Stock stock = stockRepository.findBySc(symbol);

        //判断
        if (stock == null){
            //如果不存在，则先查基本信息
            //再保存至t_stock并获取实例
            Stock stockBaseInfo = StockInfoUtil.getStockBaseInfo(symbol);
            Stock savedStock = stockRepository.saveAndFlush(stockBaseInfo);

            //再根据含基本信息的stock实例 和 startDate ， 来查 历史信息
            //保存至t_stock_hist
            Date ttm = stockBaseInfo.getTtm();
            String startDate = DateTimeUtil.getStrWithDate(ttm, false);

            //注意，此时的List<StockHist>实例可能很大
            List<StockHist> stockHistoryInfo = StockInfoUtil.getStockHistoryInfo(savedStock, startDate);

            //所以此时需要分批次存(saveAll的最大上限是1000)，这里使用commons插件
            List<List<StockHist>> lists = ListUtils.partition(stockHistoryInfo, 500);
            //
            List<StockHist> stockHists = new ArrayList<>();
            for (List<StockHist> list : lists) {
                //分批存
                List<StockHist> stockHists1 = stockHistRepository.saveAllAndFlush(list);
                stockHists.addAll(stockHists1);
            }
            //返回含有历史数据的股票信息
            savedStock.setStockHists(stockHists);
            return savedStock;
        }else {
            //如果存在，则说明该股票的基本信息和历史信息已经存在于数据库
            return stock;
        }

    }
}
