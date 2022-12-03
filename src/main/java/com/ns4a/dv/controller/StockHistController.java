package com.ns4a.dv.controller;

import com.ns4a.dv.entity.Stock;
import com.ns4a.dv.entity.StockHist;
import com.ns4a.dv.service.StockHistService;
import com.ns4a.dv.service.StockService;
import com.ns4a.dv.utils.PrintInfoUtil;
import com.ns4a.dv.utils.ResultJsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @className: StockHistController
 * @author: Yan Lang
 * @date: 2022/11/26
 * @description:
 **/
@Controller
@RequestMapping("/stock_hist")
public class StockHistController {

    /**
     * K线图的过期时间
     */
    private static final long TIMEOUT_K_LINE = 59 * 1000;

    @Autowired
    private RedisTemplate<String, Object> stringObjectRedisTemplate;

    @Autowired
    private StockHistService stockHistService;

    @Autowired
    private StockService stockService;

    @GetMapping("/find")
    public @ResponseBody
    ResultJsonUtil
    find(String symbol) throws IOException, URISyntaxException, ParseException, InterruptedException {

        Stock stock =(Stock) stringObjectRedisTemplate.opsForValue().get("stock");
        if (stock!=null){
            //说明redis中有值

            PrintInfoUtil.printAboutRedisPresent("/stock_hist/find");
            return ResultJsonUtil.ok(stock);
        }

        PrintInfoUtil.printAboutRedisAbsent("/stock_hist/find");

        //此时redis中是没值的
        //不管数据库中的股票数据存不存在，反正现在已经有了！(存在则直接查询，不存在则先添加，再查询)
        stock = stockService.addStockAllInfo(symbol);
        //获取股票历史信息
        List<StockHist> stockHistList = stockHistService.findStockHistInfo(stock);
        //防止历史信息不在基本信息之中
        stock.setStockHists(stockHistList);


        //存入stock的全部信息
        stringObjectRedisTemplate.opsForValue().set("stock", stock, TIMEOUT_K_LINE, TimeUnit.MILLISECONDS);

        //返回股票的信息（此时既有基本信息，也有历史信息）
        return ResultJsonUtil.ok(stock);


    }


}
