package com.ns4a.dv.repository;


import com.ns4a.dv.entity.PurchasedStock;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @className: PurchasedStockRepository
 * @author: Yan Lang
 * @date: 2022/11/28
 * @description:
 **/
public interface PurchasedStockRepository extends JpaRepository<PurchasedStock, Long> {
}
