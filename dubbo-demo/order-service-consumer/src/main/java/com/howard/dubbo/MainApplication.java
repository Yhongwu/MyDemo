package com.howard.dubbo;

import com.howard.dubbo.service.OrderService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * 服务启动类
 *
 * 运行后可以在dubbo-admin的服务中看到该服务
 * Created by Howard Yao on 2019/2/13.
 */
public class MainApplication {
    public static void main(String[] args) throws IOException, InterruptedException {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:consumer.xml");
        OrderService orderService = context.getBean(OrderService.class);
        orderService.initOrder("1");
        // 为了不让程序终止
        System.in.read();

    }
}
/**
 启动user-service-provider提供服务
 启动order-service-consumer来调用远程服务 查看控制台

 配置好消费者和提供者的超时时间timeout 配置好消费者的重试次数 然后在userservice调用方法里打印语句表示调用方法 并设置睡眠一段时间
 测试：
 启动user-service-provider和order-service-consumer 查看是否调用超时 和出现应有的重试次数
 启动多个user-service-provider和order-service-consumer 查看调用超时
 重试时调用的是否为不同的服务提供方方法(集群容错模式 failover 失败时重试其它服务器 有多种模式可选 <dubbo:service cluster=""/>或 <dubbo:reference cluster=""/>配置)
 **/