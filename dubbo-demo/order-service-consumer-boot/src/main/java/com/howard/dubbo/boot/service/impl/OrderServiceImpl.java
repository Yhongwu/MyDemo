package com.howard.dubbo.boot.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.howard.dubbo.bean.UserAddress;
import com.howard.dubbo.boot.service.OrderService;
import com.howard.dubbo.service.UserService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 服务消费者
 * 依赖service-api的实体和接口 但不依赖真正的服务提供者实现
 * Created by Howard Yao on 2019/1/20.
 */
// spring的service
@Service
public class OrderServiceImpl implements OrderService {

    /**
     * 表示使用远程服务
     * check 启动时检查 默认true
     * @Reference(url = "127.0.0.1:20882") dubbo直连 直接连接userservice服务 绕过注册中心
     * loadbalance = "roundrobin" 多台提供者时负载均衡策略 负载均衡级别 默认为随机调用
     */
    @Reference
    private UserService userService;

    /**
     * 调用远程出现异常则调用errorFallback方法
     * @param userId
     * @return
     * @throws InterruptedException
     */
    @HystrixCommand(fallbackMethod = "errorFallback")
    @Override
    public List<UserAddress> initOrder(String userId) throws InterruptedException {
        // 调用远程服务
        List<UserAddress> addressList = userService.getUserAddressList(userId);
        for (UserAddress userAddress : addressList) {
            System.out.println(userAddress.getUserAddress());
        }
        return addressList;
    }

    public List<UserAddress> errorFallback(String userId) {
        UserAddress address = new UserAddress(3, "test","666","B","66666666","N");
        return Arrays.asList(address);
    }
}
