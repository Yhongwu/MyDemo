package com.howard.dubbo.service.impl;

import com.howard.dubbo.bean.UserAddress;
import com.howard.dubbo.service.OrderService;
import com.howard.dubbo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 服务消费者
 * 依赖service-api的实体和接口 但不依赖真正的服务提供者实现
 * Created by Howard Yao on 2019/1/20.
 */
// spring的service
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private UserService userService;

    @Override
    public List<UserAddress> initOrder(String userId) throws InterruptedException {
        System.out.println("try...");
        // 调用远程服务
        List<UserAddress> addressList = userService.getUserAddressList(userId);
        for (UserAddress userAddress : addressList) {
            System.out.println(userAddress.getUserAddress());
        }
        return addressList;
    }
}
