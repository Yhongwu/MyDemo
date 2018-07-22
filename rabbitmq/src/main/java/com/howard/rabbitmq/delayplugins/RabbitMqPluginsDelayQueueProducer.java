package com.howard.rabbitmq.delayplugins;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

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
 * 使用插件来实现
 * rabbitmq_delayed_message_exchange
 * Created by Howard Yao on 2018/7/19.
 */
public class RabbitMqPluginsDelayQueueProducer {
    private static final String HOST = "192.168.1.102";
    private static final Integer PORT = 5672;
    private static final String EXCHANGE = "exchange-delay";
    private static final String QUEUE = "queue-delay";
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

        Map<String, Object> map = new HashMap<>();
        // 插件提供的参数
        map.put("x-delayed-type", "direct");
        channel.exchangeDeclare(EXCHANGE,"x-delayed-message",false,true,map);

        channel.queueDeclare(QUEUE,false,false,false,null);
        channel.queueBind(QUEUE,EXCHANGE,ROUTING_KEY);

        String message = "hello world!";

        Map<String, Object> headers = new HashMap<>();
        headers.put("x-delay", 6000);
        AMQP.BasicProperties.Builder props = new AMQP.BasicProperties.Builder()
                .headers(headers);
        channel.basicPublish(EXCHANGE,ROUTING_KEY, props.build(),message.getBytes());

        System.out.println(new Date() + " send message "+ message);

        channel.close();
        connection.close();
    }
}
