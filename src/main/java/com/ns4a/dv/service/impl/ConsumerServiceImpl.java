package com.ns4a.dv.service.impl;


import com.ns4a.dv.entity.Consumer;
import com.ns4a.dv.entity.OptionalStock;
import com.ns4a.dv.entity.Stock;
import com.ns4a.dv.entity.StockYield;
import com.ns4a.dv.repository.ConsumerRepository;
import com.ns4a.dv.repository.OptionalStockRepository;
import com.ns4a.dv.repository.StockRepository;
import com.ns4a.dv.repository.StockYieldRepository;
import com.ns4a.dv.service.ConsumerService;
import com.ns4a.dv.utils.Md5Util;
import com.ns4a.dv.utils.RandomNumberUtil;
import com.ns4a.dv.utils.akshare.StockInfoUtil;
import com.ns4a.dv.vo.RealTimeStockVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @className: ConsumerServiceImpl
 * @author: Yan Lang
 * @date: 2022/11/28
 * @description:
 **/
@Service
public class ConsumerServiceImpl implements ConsumerService {
    @Autowired
    private ConsumerRepository consumerRepository;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private OptionalStockRepository optionalStockRepository;

    @Autowired
    private StockYieldRepository stockYieldRepository;

    //用户初始化的信息
    private static final String INIT_AVATAR = "https://wallhaven.cc/w/m9xyg8";
    private static final String INIT_GENDER = "男";
    private static final String INIT_NICKNAME_BEFORE = "ns4a_";
    private static final boolean INIT_HAS_ROOT = true;

    /**
     * 默认的股票代码集合
     */
    private static final String[] INIT_STOCK_CODES = {"002585", "002555", "002596"};

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Consumer addConsumer(Consumer consumer) throws Exception {
        //先检查数据库中是否已经有相同用户名的用户
        Consumer tempUser = consumerRepository.findByUsername(consumer.getUsername());
        if (tempUser != null){
            //已经存在
            return null;
        }

        /*
        初始化操作：添加自选篮、购买篮、创建时间、默认权利、性别、头像、昵称
         */
        //默认昵称
        String defaultNickname = INIT_NICKNAME_BEFORE + RandomNumberUtil.getRandomNumber(0);
        //MD5加密
        String password = consumer.getPassword();
        String md5Password = Md5Util.getCode(password);
        //构造初始化用户
        Consumer initConsumer = new Consumer(consumer.getUsername(), md5Password, INIT_AVATAR, INIT_GENDER, defaultNickname, new Date(), null, null, INIT_HAS_ROOT);
        //保存之后的用户
        Consumer savedUser = consumerRepository.saveAndFlush(initConsumer);

        //初始化用户的自选股
        List<OptionalStock> initOptionalStocks = new ArrayList<>();
        for (String initStockCode : INIT_STOCK_CODES) {
            //查看数据库中是否有这只股票
            Stock savedStock = stockRepository.findBySc(initStockCode);
            if (savedStock != null) {
                //说明此时数据库中 有这只股票的全部信息(基本和历史)，那就说明，所以一定能查到这只股票的实时数据信息
                //获取这只股票的实时信息
                RealTimeStockVo realTimeStockVo = StockInfoUtil.getRealTimeStock(initStockCode);
                //初始化股票收益
                StockYield initStockYield = new StockYield(new Date(), realTimeStockVo.getNp());
                //得先把initStockYield保存
                StockYield savedStockYield = stockYieldRepository.save(initStockYield);
                //一只自选股
                //特别注意，这几个参数都已经持久化！！！
                OptionalStock initOptionalStock = new OptionalStock(savedUser, savedStock, savedStockYield);
                //添加进自选篮
                initOptionalStocks.add(initOptionalStock);
            }
        }

        //如果股票篮里至少有一只股票，才保存至数据库
        if (initOptionalStocks.size() > 0) {
            for (OptionalStock initOptionalStock : initOptionalStocks) {
                //持久化
                OptionalStock savedOptionalStock = optionalStockRepository.save(initOptionalStock);
            }
        }
        return savedUser;
    }

    @Override
    public Consumer findConsumer(Consumer consumer) {
        //MD5加密
        String password = consumer.getPassword();
        String md5Password = Md5Util.getCode(password);
        return consumerRepository.findByUsernameAndPassword(consumer.getUsername(), md5Password);
    }
}
