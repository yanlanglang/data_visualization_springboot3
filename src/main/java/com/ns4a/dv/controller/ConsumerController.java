package com.ns4a.dv.controller;

import com.ns4a.dv.entity.Consumer;
import com.ns4a.dv.service.ConsumerService;
import com.ns4a.dv.service.StockService;
import com.ns4a.dv.utils.ResultJsonUtil;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @className: ConsumerController
 * @author: Yan Lang
 * @date: 2022/11/28
 * @description:
 **/
@Controller
@RequestMapping("/consumer")
public class ConsumerController {

    @Autowired
    private ConsumerService consumerService;

    @Autowired
    private StockService stockService;

    /**
     * 注册
     * @param consumer 含username、password
     * @return
     */
    @PostMapping("/signup")
    public @ResponseBody
    ResultJsonUtil signup(Consumer consumer) throws Exception {
        Consumer savedConsumer = consumerService.addConsumer(consumer);
        if (savedConsumer == null){
            return ResultJsonUtil.error("用户名已经存在！");
        }else {
            return ResultJsonUtil.ok("尊敬的 "+savedConsumer.getNickname()+"，您注册成功啦！感谢有您的加入！");
        }
    }

    /**
     * 登录
     * @param consumer 含username、password
     * @return
     */
    @GetMapping("/login")
    public @ResponseBody
    ResultJsonUtil login(Consumer consumer, HttpSession session){
        Consumer loginedConsumer = consumerService.findConsumer(consumer);
        if (loginedConsumer != null){
            session.setAttribute("consumer", loginedConsumer);
            return ResultJsonUtil.ok(loginedConsumer);
        }else {
            return ResultJsonUtil.error("用户名或密码错误，请重新尝试！");
        }
    }


}
