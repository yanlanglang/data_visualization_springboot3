package com.ns4a.dv.controller;


import com.ns4a.dv.entity.Stock;
import com.ns4a.dv.service.StockService;
import com.ns4a.dv.utils.ResultJsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @className: StockController
 * @author: Yan Lang
 * @date: 2022/11/25
 * @description:
 **/
@Controller
@RequestMapping("/stock")
public class StockController {

    @Autowired
    private StockService stockService;

    @PostMapping("/add")
    public @ResponseBody
    ResultJsonUtil add(String symbol){
        Stock stock = new Stock();

        //尝试存储
        try {
            stock = stockService.addStockAllInfo(symbol);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //设置响应消息
        return ResultJsonUtil.ok(stock);

    }

}
