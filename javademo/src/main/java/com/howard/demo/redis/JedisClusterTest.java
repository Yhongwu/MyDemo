package com.howard.demo.redis;

import java.util.HashSet;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
/**
 * redis集群的测试
 * 测试环境:伪redis集群 6台redis实例位于同一台linux下
 * 注意防火墙端口
 * 2017年8月27日
 * @author hongwu
 */
public class JedisClusterTest {
	public static void main(String[] args) {
		//创建集群节点
		HashSet<HostAndPort> nodes = new HashSet<>();
		nodes.add(new HostAndPort("192.168.1.112", 7001));
		nodes.add(new HostAndPort("192.168.1.112", 7002));
		nodes.add(new HostAndPort("192.168.1.112", 7003));
		nodes.add(new HostAndPort("192.168.1.112", 7004));
		nodes.add(new HostAndPort("192.168.1.112", 7005));
		nodes.add(new HostAndPort("192.168.1.112", 7006));
		
		//创建连接
		JedisCluster jedisCluster = new JedisCluster(nodes);
		jedisCluster.set("time", "20170827");
		String str = jedisCluster.get("time");
		System.out.println(str);
		Long incr = jedisCluster.incr("time");
		System.out.println(incr);
		
		//程序结束 关闭连接
		jedisCluster.close();
	}
}
