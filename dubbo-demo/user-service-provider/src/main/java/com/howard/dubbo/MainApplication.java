package com.howard.dubbo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * 服务启动类
 *
 * 运行后可以在dubbo-admin的服务中看到该服务
 * Created by Howard Yao on 2019/2/13.
 */
public class MainApplication {
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:provider.xml");
        context.start();
        // 为了不让程序终止
        System.in.read();

    }
}
