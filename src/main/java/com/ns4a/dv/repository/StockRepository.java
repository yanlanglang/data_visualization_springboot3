package com.ns4a.dv.repository;


import com.ns4a.dv.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @className: StockRepository
 * @author: Yan Lang
 * @date: 2022/11/25
 * @description: 股票基本信息
 **/
public interface StockRepository extends JpaRepository<Stock, Long> {

    /**
     * 通过股票代码，查询t_stock中是否已经存在该股票的基本信息
     * @param sc stock code：股票代码
     * @return
     */
    Stock findBySc(String sc);
}
