package com.howard.dubbo.boot;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.ImportResource;

/**
 * springboot整合dubbo消费者
 * 1 导入dubbo-starter 使用@EnableDubbo 使用注解api
 * 2 导入dubbo-starter 通过@ImportResource引入dubbo.xml配置文件保留xml配置
 * Reference注解表示使用远程服务 代替autowire
 * test:
 * 启动user-service-provider-boot
 * 启动order-service-consumer-boot
 * 观察http://localhost:7001 管理台
 * 浏览器http://localhost:8080/initOrder?useId=1测试
 *
 * 多次刷新：http://localhost:8080/initOrder?useId=1 (服务容错测试)
 *
 *
 * @EnableHystrix 整合hystrix实现容错
 */
@EnableDubbo
@EnableHystrix
@SpringBootApplication
public class OrderServiceConsumerBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceConsumerBootApplication.class, args);
	}

}

