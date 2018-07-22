package com.howard.rabbitmq.demo;

import com.rabbitmq.client.*;
import sun.applet.Main;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by Howard Yao on 2018/7/11.
 */
public class RabbitMqDemoConsumer {
    private static final String QUEUE = "queue_test";
    private static final String HOST = "192.168.1.102";
    private static final Integer PORT = 5672;

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(HOST);
        connectionFactory.setPort(PORT);
        connectionFactory.setUsername("root");
        connectionFactory.setPassword("123456");

        Connection connection = connectionFactory.newConnection();
        final Channel channel = connection.createChannel();
        channel.basicQos(64);

        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body, "UTF-8");
                System.out.println("Received is = '" + msg + "'");
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        };
        //拉模式
        //GetResponse response= channel.basicGet("myqueue", false) ;
        //channel.basicAck (response . getEnvelope () .getDeliveryTag () , false) ;

        channel.basicConsume(QUEUE,consumer);
        //TimeUnit.SECONDS.sleep(5);

        // channel.close();
        //connection.close();


    }
}
