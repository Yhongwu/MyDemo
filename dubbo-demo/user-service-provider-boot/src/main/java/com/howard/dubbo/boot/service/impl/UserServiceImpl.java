package com.howard.dubbo.boot.service.impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.howard.dubbo.bean.UserAddress;
import com.howard.dubbo.service.UserService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * 服务提供者
 * 将实体和接口抽取出成公共模块service-api。供其它模块引用
 * 这里也引用service-api 只提供接口实现
 *
 * 引入dubbo和zookeeper客户端curator
 * 将服务提供者注册到注册中心(暴露服务)，让消费者调用
 *
 * Created by Howard Yao on 2019/1/20.
 */
@Service// dubbo的注解 暴露服务 weight权重
@Component // 也可用spring的service 避免混淆
public class UserServiceImpl implements UserService {

    /**
     * 容错 @HystrixCommand 代理异常处理
     * @param userId
     * @return
     */
    @HystrixCommand
    @Override
    public List<UserAddress> getUserAddressList(String userId) {
        UserAddress address_1 = new UserAddress(1, "广州","123","A","18822212345","Y");
        UserAddress address_2 = new UserAddress(2, "汕头","456","B","18845562346","N");

        // 模拟随机异常
        if (Math.random() > 0.5) {
            throw new RuntimeException();
        }
        return Arrays.asList(address_1, address_2);
    }
}
