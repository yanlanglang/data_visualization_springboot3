package com.ns4a.dv.controller;

import com.ns4a.dv.entity.Stock;
import com.ns4a.dv.entity.StockHist;
import com.ns4a.dv.service.StockHistService;
import com.ns4a.dv.service.StockService;
import com.ns4a.dv.utils.ResultJsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.List;

/**
 * @className: StockHistController
 * @author: Yan Lang
 * @date: 2022/11/26
 * @description:
 **/
@Controller
@RequestMapping("/stock_hist")
public class StockHistController {

    @Autowired
    private StockHistService stockHistService;

    @Autowired
    private StockService stockService;

    @GetMapping("/find")
    public @ResponseBody
    ResultJsonUtil
    find(String symbol) throws IOException, URISyntaxException, ParseException, InterruptedException {

        //不管之前存不存在，反正现在已经有了！
        Stock stock = stockService.addStockAllInfo(symbol);
        //获取股票历史信息
        List<StockHist> stockHistList = stockHistService.findStockHistInfo(stock);
        //防止历史信息不在基本信息之中
        stock.setStockHists(stockHistList);
        //返回股票的信息（此时既有基本信息，也有历史信息）
        return ResultJsonUtil.ok(stock);
    }


}
