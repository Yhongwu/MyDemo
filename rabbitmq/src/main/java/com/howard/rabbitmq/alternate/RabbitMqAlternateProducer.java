package com.howard.rabbitmq.alternate;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * Alternate Exchange
 * 备份交换器
 */
public class RabbitMqAlternateProducer {
    private static final String HOST = "192.168.1.102";
    private static final Integer PORT = 5672;
    private static final String EXCHANGE1 = "exchange1";
    private static final String EXCHANGE2 = "exchange2";
    private static final String QUEUE1 = "queue1";
    private static final String QUEUE2 = "queue2";
    private static final String ROUTING_KEY1 = "routing_key1";
    private static final String ROUTING_KEY2 = "routing_key2";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "123456";

    public static void main(String[] args) throws IOException, TimeoutException, NoSuchAlgorithmException, KeyManagementException, URISyntaxException {
        ConnectionFactory connectionFactory = new ConnectionFactory();

        connectionFactory.setHost(HOST);
        connectionFactory.setPort(PORT);
        connectionFactory.setUsername(USERNAME);
        connectionFactory.setPassword(PASSWORD);

        // 创建连接
        Connection connection = connectionFactory.newConnection();
        // 创建信道
        Channel channel = connection.createChannel();

        Map<String, Object> map = new HashMap<>();
        // 备份交换器
        map.put ("alternate-exchange" , EXCHANGE2);

        channel.exchangeDeclare(EXCHANGE1,"direct",false,true,map);
        channel.exchangeDeclare(EXCHANGE2,"fanout",false,true,null);
        //channel.exchangeDeclare(EXCHANGE2,"direct",false,true,null);

        // EXCHANGE1通过ROUTING_KEY1与QUEUE1绑定
        channel.queueDeclare(QUEUE1,false,false,true,null);
        channel.queueBind(QUEUE1,EXCHANGE1,ROUTING_KEY1);

        channel.queueDeclare(QUEUE2,false,false,true,null);
        // fanout 模式 QUEUE2可以接收来自EXCHANGE2的消息
        channel.queueBind(QUEUE2,EXCHANGE2,"");
        // direct模式 routingkey必须匹配才能接收
        //channel.queueBind(QUEUE2,EXCHANGE2,ROUTING_KEY2);

        String message = "hello world!";

        // 将消息发送到EXCHANGE1，routingkey为ROUTING_KEY2，而EXCHANGE1没有与之匹配的队列 所以会寻找备份交换器
        channel.basicPublish(EXCHANGE1,ROUTING_KEY2, MessageProperties.PERSISTENT_TEXT_PLAIN,message.getBytes());

        System.out.println("Send message : "+ message);

        channel.close();
        connection.close();

    }
}
