package com.ns4a.dv.service.impl;


import com.ns4a.dv.entity.Stock;
import com.ns4a.dv.entity.StockHist;
import com.ns4a.dv.repository.StockHistRepository;
import com.ns4a.dv.service.StockHistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @className: StockHistServiceImpl
 * @author: Yan Lang
 * @date: 2022/11/26
 * @description:
 **/
@Service
public class StockHistServiceImpl implements StockHistService {

    @Autowired
    private StockHistRepository stockHistRepository;

    @Override
    public List<StockHist> findStockHistInfo(Stock stock) {
        return stockHistRepository.findStockHistsByStock(stock);
    }
}
