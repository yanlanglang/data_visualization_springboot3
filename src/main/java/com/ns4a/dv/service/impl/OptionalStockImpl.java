package com.ns4a.dv.service.impl;


import com.ns4a.dv.entity.Consumer;
import com.ns4a.dv.entity.OptionalStock;
import com.ns4a.dv.entity.Stock;
import com.ns4a.dv.entity.StockYield;
import com.ns4a.dv.repository.ConsumerRepository;
import com.ns4a.dv.repository.OptionalStockRepository;
import com.ns4a.dv.repository.StockHistRepository;
import com.ns4a.dv.service.OptionalStockService;
import com.ns4a.dv.utils.akshare.StockInfoUtil;
import com.ns4a.dv.vo.OptionalStockVo;
import com.ns4a.dv.vo.RealTimeStockVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @className: OptionalStockImpl
 * @author: Yan Lang
 * @date: 2022/11/28
 * @description:
 **/
@Service
public class OptionalStockImpl implements OptionalStockService {

    @Autowired
    private ConsumerRepository consumerRepository;

    @Autowired
    private OptionalStockRepository optionalStockRepository;

    @Autowired
    private StockHistRepository stockHistRepository;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public List<OptionalStockVo> findOptionalStockVos(Long id) throws Exception {
        //可以改用Optional
        Consumer consumer = consumerRepository.findConsumerById(id);
        //通过用户查找他的自选篮
        List<OptionalStock> optionalStocks = optionalStockRepository.findAllByConsumer(consumer);

        //返回的结果
        List<OptionalStockVo> optionalStockVos = new ArrayList<>();
        //对照optionalStockVos实体类，返回需要的数据集合
        for (OptionalStock optionalStock : optionalStocks) {
            //params:获取一对一中的Stock实例，由此获取sc、sn、industry、rmv四条信息
            Stock stock = optionalStock.getStock();

            //实时的股票信息
            RealTimeStockVo realTimeStock = StockInfoUtil.getRealTimeStock(stock.getSc());
            //params:实时的价格
            float np = realTimeStock.getNp();

            //获取一对一中的StockYield实例
            StockYield stockYield = optionalStock.getStockYield();
            //添加（到自选篮）时的价格
            float createPrice = stockYield.getCreatePrice();
            //params:收益
            float yield = (np - createPrice) / 100;

            //历史最低价和历史最高价
            float maxHighest = stockHistRepository.findMaxHighestByStock(stock);
            float minLowest = stockHistRepository.findMinLowestByStock(stock);
            //相对位置 = [(当前位置)-历史最低价] / (历史最高价-历史最低价)
            float rp = (np-minLowest)/(maxHighest-minLowest);

            //实例化
            OptionalStockVo optionalStockVo = new OptionalStockVo(
                    stock.getSc(),
                    stock.getSn(),
                    stock.getIndustry(),
                    stock.getRmv(),
                    np,
                    maxHighest,
                    minLowest,
                    rp,
                    yield);
            //添加到返回结果中
            optionalStockVos.add(optionalStockVo);
        }
        return optionalStockVos;
    }
}
