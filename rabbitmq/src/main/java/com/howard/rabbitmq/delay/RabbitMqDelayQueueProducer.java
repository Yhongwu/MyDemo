package com.howard.rabbitmq.delay;

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
 * 延迟队列
 * Created by Howard Yao on 2018/7/19.
 */
public class RabbitMqDelayQueueProducer {
    private static final String HOST = "192.168.1.102";
    private static final Integer PORT = 5672;
    private static final String EXCHANGE1 = "exchange1";
    private static final String EXCHANGE2 = "exchange2";
    private static final String QUEUE1 = "queue1";
    private static final String QUEUE2 = "queue2";
    private static final String ROUTING_KEY = "routing_key";
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

        channel.exchangeDeclare(EXCHANGE1,"direct",false,true,null);
        //channel.exchangeDeclare("exchange2","fanout",false,true,null);
        channel.exchangeDeclare(EXCHANGE2,"fanout",false,true,null);

        Map<String, Object> queueArgs = new HashMap<>();
        // 设置队列消息过期时间为6s
        queueArgs.put ("x-message-ttl" , 6000);
        queueArgs.put("x-dead-letter-exchange", EXCHANGE2);//过期消息转向路由
        channel.queueDeclare(QUEUE1,false,false,false,queueArgs);
        channel.queueBind(QUEUE1,EXCHANGE1,ROUTING_KEY);

        channel.queueDeclare(QUEUE2,false,false,false,null);
        channel.queueBind(QUEUE2,EXCHANGE2,"");

        String message = "hello world!";

        /*AMQP.BasicProperties properties = new AMQP.BasicProperties.Builder()
                .contentType("text/plain")
                .deliveryMode(2)  //模式2表示消息会被持久化
                .expiration("6000")
                .build();*/
        channel.basicPublish(EXCHANGE1,ROUTING_KEY, MessageProperties.PERSISTENT_TEXT_PLAIN,message.getBytes());

        System.out.println(new Date() + " send message "+ message);

        channel.close();
        connection.close();

    }
}
