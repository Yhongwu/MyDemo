package com.howard.dubbo.boot;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

/**
 * springboot整合dubbo 主要使用注解代替xml
 *
 * 引入dubbo-spring-boot-starter
 * @EnableDubbo 开启基于注解的dubbo
 * 配置application.properties
 * 配置dubbo相关注解
 *
 * @EnableHystrix 整合Hystrix方式来实现服务容错
 */
@EnableDubbo
@EnableHystrix
@SpringBootApplication
public class UserServiceProviderBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceProviderBootApplication.class, args);
	}

}

