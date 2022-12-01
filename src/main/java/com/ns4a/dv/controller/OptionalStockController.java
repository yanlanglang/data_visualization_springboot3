package com.ns4a.dv.controller;


import com.ns4a.dv.entity.Consumer;
import com.ns4a.dv.service.OptionalStockService;
import com.ns4a.dv.utils.ResultJsonUtil;
import com.ns4a.dv.vo.OptionalStockVo;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @className: OptionalStockController
 * @author: Yan Lang
 * @date: 2022/11/28
 * @description:
 **/
@Controller
@RequestMapping("/optional_stock")
public class OptionalStockController {

    @Autowired
    private OptionalStockService optionalStockService;

    @GetMapping("/index")
    public @ResponseBody
    ResultJsonUtil index(HttpSession session) throws Exception {
        Consumer consumer =(Consumer) session.getAttribute("consumer");
        if (consumer != null){
            //用户是存在的，即他的id也是有的
            List<OptionalStockVo> optionalStocks =  optionalStockService.findOptionalStockVos(consumer.getId());
            if (optionalStocks.size() == 0){
                //自选篮为空
                return ResultJsonUtil.ok(false);
            }else {
                //自选篮不为空
                return ResultJsonUtil.ok(optionalStocks);
            }
        }else {
            return ResultJsonUtil.error("请先登录嗷！");
        }
    }

}
