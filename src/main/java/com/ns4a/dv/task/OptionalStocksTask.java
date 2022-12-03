package com.ns4a.dv.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @className: OptionalStocksTask
 * @author: Yan Lang
 * @date: 2022/12/1
 * @description:
 *      + @Component：注解用于对那些比较中立的类进行注释，
 *      相当于在持久层、业务层和控制层分别采用 @Repository、@Service 和 @Controller 对分层中的类进行注释；
 *      + @EnableScheduling：开启定时任务；
 *      + @EnableAsync：开启多线程
 **/
@Component
@EnableScheduling
@EnableAsync
public class OptionalStocksTask {

    /**
     * 更新用户的自选篮信息
     */
    private static final Integer OPTIONAL_STOCKS_TIME = 30000;

    @Autowired
    private RedisTemplate<String, Object> stringObjectRedisTemplate;

//    @Async
//    @Scheduled(fixedDelay = 1000)  //每隔1秒执行一次
//    public void first(){
//
//    }
//
//    @Async
//    @Scheduled(fixedDelay = 2000) //每隔2秒执行一次
//    public void second(){
//    }

}
