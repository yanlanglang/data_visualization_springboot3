package com.ns4a.dv.repository;


import com.ns4a.dv.entity.Consumer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @className: ConsumerRepository
 * @author: Yan Lang
 * @date: 2022/11/28
 * @description: 用户
 **/
public interface ConsumerRepository extends JpaRepository<Consumer, Long> {

    Consumer findByUsernameAndPassword(String username, String password);

    Consumer findByUsername(String username);

    Consumer findConsumerById(Long id);

}
