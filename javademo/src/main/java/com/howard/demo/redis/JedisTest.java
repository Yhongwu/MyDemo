package com.howard.demo.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
/**
 * 单机版redis测试
 * 需要安装redis的主机
 * 2017年8月27日
 * @author hongwu
 */
public class JedisTest {
	/**
	 * 简单测试
	 */
	public static void jedisTest() {
		Jedis jedis = new Jedis("192.168.1.112",6379);
		jedis.set("year", "2017");
		String str = jedis.get("year");
		System.out.println(str);
		jedis.close();
	}
	/**
	 * jedis连接池
	 */
	public static void jedisPoolTest() {
		//创建连接池
		JedisPool pool = new JedisPool("192.168.1.112",6379);
		//从连接池获取redis连接
		Jedis jedis = pool.getResource();
		
		//jedis.set("month", "8");
		String str = jedis.get("year");
		System.out.println(str);
		//操作结束关闭连接
		jedis.close();
		//程序结束 关闭连接池
		pool.close();
	}
	
	public static void main(String[] args) {
//		jedisTest();
		jedisPoolTest();
		
	}
}
