package com.howard.dubbo.service;

import com.howard.dubbo.bean.UserAddress;

import java.util.List;

/**
 * Created by Howard Yao on 2019/1/20.
 */
public interface OrderService {
    List<UserAddress> initOrder(String userId) throws InterruptedException;
}
