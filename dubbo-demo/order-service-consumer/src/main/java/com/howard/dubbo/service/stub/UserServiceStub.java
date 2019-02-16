package com.howard.dubbo.service.stub;

import com.howard.dubbo.bean.UserAddress;
import com.howard.dubbo.service.UserService;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 本地存根
 * 在调用远程服务前的处理
 * 必须存在一个有参数构造器 带参为userService 远程服务的代理 默认已实现该构造器
 * Created by Howard Yao on 2019/2/16.
 */
public class  UserServiceStub implements UserService{

    private final UserService userService;

    /**
     * duboo会传入userservice远程代理对象
     * @param userService
     */
    public UserServiceStub(UserService userService) {
        super();
        this.userService = userService;
    }
    @Override
    public List<UserAddress> getUserAddressList(String userId) throws InterruptedException {
        if (!StringUtils.isEmpty(userId)) {
            return userService.getUserAddressList(userId);
        }
        return null;
    }
}
