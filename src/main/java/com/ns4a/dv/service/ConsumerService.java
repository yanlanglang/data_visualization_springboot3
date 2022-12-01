package com.ns4a.dv.service;


import com.ns4a.dv.entity.Consumer;

/**
 * @className: ConsumerService
 * @author: Yan Lang
 * @date: 2022/11/28
 * @description:
 **/
public interface ConsumerService {
    Consumer addConsumer(Consumer consumer) throws Exception;

    Consumer findConsumer(Consumer consumer);
}
