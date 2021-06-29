package com.skeet.rabbitmq.service;

/**
 * @Description: RabbitMQ推送服务类
 * @Author: echo
 * @Date: 2020/11/16 15:08
 * @Version: 1.0
 */
public interface RabbitMqService {


    /**
     * 通过routingKey发送消息到队列
     *
     * @param exchange
     * @param routingKey 路由key
     * @param message    消息体
     */
    <T> void send(String exchange, String routingKey, T message);


    /**
     * 通过routingKey发送消息到队列
     *
     * @param routingKey 路由key
     * @param message    消息体
     */
    <T> void send(String routingKey, T message);
}
