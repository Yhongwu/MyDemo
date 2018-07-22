package com.howard.rabbitmq.demo;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;

/**
 * Created by Howard Yao on 2018/7/11.
 */
public class RabbitMqDemoProducer {
    private static final String HOST = "192.168.1.102";
    private static final Integer PORT = 5672;
    private static final String EXCHANGE = "exchange_test";
    private static final String QUEUE = "queue_test";
    private static final String ROUTING_KEY = "routing_key_test";


    public static void main(String[] args) throws IOException, TimeoutException, NoSuchAlgorithmException, KeyManagementException, URISyntaxException {
        ConnectionFactory connectionFactory = new ConnectionFactory();

        //使用URI的方式创建连接
        //ConnectionFactory factory =  new ConnectionFactory();
        //factory.setUri("amqp://root:123456@192.168.1.102");
        //Connection conn= factory.newConnection();
        // 创建连接
        //Connection connection = connectionFactory.newConnection();

        connectionFactory.setHost(HOST);
        connectionFactory.setPort(PORT);
        connectionFactory.setUsername("root");
        connectionFactory.setPassword("123456");

        // 创建连接
        Connection connection = connectionFactory.newConnection();
        // 创建信道
        Channel channel = connection.createChannel();
        // type=diect 交换器名为exchange_test
        channel.exchangeDeclare(EXCHANGE,"direct",true,false,null);
        // 创建持久化的队列 队列名queue_test
        channel.queueDeclare(QUEUE,true,false,false,null);
        // 将队列与交换器绑定 ROUTING_KEY在很多情况下默认等同于队列名
        channel.queueBind(QUEUE,EXCHANGE,ROUTING_KEY);
        // 发送消息
        String message = "hello world!";
        channel.basicPublish(EXCHANGE,ROUTING_KEY, MessageProperties.PERSISTENT_TEXT_PLAIN,message.getBytes());

        //MessageProperties.PERSISTENT_TEXT_PLAIN等价于
        new AMQP.BasicProperties.Builder()
                . contentType("text/plain")
                .deliveryMode(2)  //模式2表示消息会被持久化
                //.priority(1) // 优先级
                .build();
                        System.out.println("send message "+ message + " to queue["+ QUEUE +"] !");


        //交换器与交换器绑定
       /* channel.exchangeDeclare("exchange1","direct",false,true,null);
        channel.exchangeDeclare("exchange2","direct",false,true,null);

        channel.exchangeBind("exchange2","exchange1","exchangeKey");

        channel.queueDeclare("queue",false,false,true,null);
        channel.queueBind("queue","exchange2","");

        channel.basicPublish("exchange1","exchangeKey",null,"hello world".getBytes());
*/


        //channel.close();
       // connection.close();

    }
}
