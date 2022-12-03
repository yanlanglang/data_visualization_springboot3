package com.ns4a.dv.controller;


import com.ns4a.dv.entity.Consumer;
import com.ns4a.dv.service.OptionalStockService;
import com.ns4a.dv.utils.PrintInfoUtil;
import com.ns4a.dv.utils.ResultJsonUtil;
import com.ns4a.dv.utils.akshare.StockInfoUtil;
import com.ns4a.dv.vo.OptionalStockVo;
import com.ns4a.dv.vo.RealTimeStockVo;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @className: OptionalStockController
 * @author: Yan Lang
 * @date: 2022/11/28
 * @description:
 **/
@Controller
@RequestMapping("/optional_stock")
public class OptionalStockController {


    /**
     * 直方图的过期时间
     */
    private static final long TIMEOUT_HISTOGRAM = 5 * 1000;

    /**
     * 股票代码集合的过期时间
     */
    private static final long TIMEOUT_REALTIME =  5 *1000;

    @Autowired
    private RedisTemplate<String, Object> stringObjectRedisTemplate;

    @Autowired
    private OptionalStockService optionalStockService;

    @GetMapping("/index")
    public @ResponseBody
    ResultJsonUtil index(HttpSession session) throws Exception {
        Consumer consumer =(Consumer) session.getAttribute("consumer");
        if (consumer != null){
            //redis中有值

            List<OptionalStockVo> vos = (List<OptionalStockVo>) stringObjectRedisTemplate.opsForValue().get("optionalStockVos");
            if (vos != null){
                PrintInfoUtil.printAboutRedisPresent("/optional_stock/index");
                return ResultJsonUtil.ok(vos);
            }

            PrintInfoUtil.printAboutRedisAbsent("/optional_stock/index");
            //redis中没有值
            List<OptionalStockVo> optionalStockVos =  optionalStockService.findOptionalStockVos(consumer.getId());
            if (optionalStockVos.size() == 0){
                //自选篮为空
                return ResultJsonUtil.ok(false);
            }else {
                //自选篮不为空
                //存储到redis中
                // param3: 过期时间，long类型
                // param4: 时间的单位
                stringObjectRedisTemplate.opsForValue().set("optionalStockVos", optionalStockVos, TIMEOUT_HISTOGRAM, TimeUnit.MILLISECONDS);
                return ResultJsonUtil.ok(optionalStockVos);
            }
        }else {
            return ResultJsonUtil.error("请先登录嗷！");
        }
    }


    /**
     *
     * @param session
     * @return 指定股票的RealTimeStockVo rts
     */
    @GetMapping("/realtime")
    public @ResponseBody
    ResultJsonUtil realtime(String sc, HttpSession session) throws Exception {
        Consumer consumer =(Consumer) session.getAttribute("consumer");
        if (consumer!=null){
            //判断redis中有没有值
            RealTimeStockVo temp = (RealTimeStockVo) stringObjectRedisTemplate.opsForValue().get("realtime" + sc);
            if (temp != null){
                //有值
                PrintInfoUtil.printAboutRedisPresent("/optional_stock/realtime");

                return ResultJsonUtil.ok(temp);
            }

            //没值
            RealTimeStockVo realTimeStock = StockInfoUtil.getRealTimeStock(sc);
            //在redis中存值 (5秒内自动销毁)
            stringObjectRedisTemplate.opsForValue().set("realtime"+sc, realTimeStock, TIMEOUT_REALTIME, TimeUnit.MILLISECONDS);
            PrintInfoUtil.printAboutRedisAbsent("/optional_stock/realtime");
            return ResultJsonUtil.ok(realTimeStock);
        }

        return ResultJsonUtil.error("请先登录！");
    }
}
