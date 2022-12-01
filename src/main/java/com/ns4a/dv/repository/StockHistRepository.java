package com.ns4a.dv.repository;


import com.ns4a.dv.entity.Stock;
import com.ns4a.dv.entity.StockHist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @className: StockHistRepository
 * @author: Yan Lang
 * @date: 2022/11/25
 * @description: 股票历史信息
 **/
public interface StockHistRepository extends JpaRepository<StockHist, Long> {

    /**
     * 通过stock来获取List<StockHist>
     *     在一对多关系中，通过”一“的一方 来获取"多"的一方
     * @param stock
     * @return
     */
    List<StockHist> findStockHistsByStock(Stock stock);

    /**
     * 通过stock来查找它的历史最高价
     * @param stock
     * @return
     */
    @Query("select max(sh.highest) from StockHist sh WHERE sh.stock = ?1")
    float findMaxHighestByStock(Stock stock);


    /**
     * 通过stock来查找它的历史最低价
     * @param stock
     * @return
     */
    @Query("select min(sh.lowest) from StockHist sh WHERE sh.stock = ?1")
    float findMinLowestByStock(Stock stock);

}
