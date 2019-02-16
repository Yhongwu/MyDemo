package com.howard.dubbo.boot.controller;

import com.howard.dubbo.bean.UserAddress;
import com.howard.dubbo.boot.service.OrderService;
import com.howard.dubbo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Howard Yao on 2019/2/16.
 */
@Controller
public class OrderController {
    @Autowired
    private OrderService orderService;

    @RequestMapping("/initOrder")
    @ResponseBody
    public List<UserAddress> initOrder(String userId) throws InterruptedException {
        return orderService.initOrder(userId);
    }

}
