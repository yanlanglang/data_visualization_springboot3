package com.ns4a.dv.repository;


import com.ns4a.dv.entity.Consumer;
import com.ns4a.dv.entity.OptionalStock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @className: OptionalStockRepository
 * @author: Yan Lang
 * @date: 2022/11/28
 * @description:
 **/
public interface OptionalStockRepository extends JpaRepository<OptionalStock, Long> {

    /**
     * 通过用户，查找所有OptionalStock
     * @param consumer
     * @return
     */
    List<OptionalStock> findAllByConsumer(Consumer consumer);

}
