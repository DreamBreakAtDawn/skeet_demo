package com.skeet.rabbitmq.service.impl;

import com.skeet.common.api.util.FastJsonUtil;
import com.skeet.rabbitmq.config.BaseRabbitConfig;
import com.skeet.rabbitmq.service.RabbitMqService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description: RabbitMQ推送服务类
 * @Author: echo
 * @Date: 2020/11/16 15:11
 * @Version: 1.0
 */
@Service
@Slf4j
public class RabbitMqServiceImpl implements RabbitMqService {

    @Resource
    private RabbitTemplate rabbitTemplate;

    @Override
    public <T> void send(String exchange, String routingKey, T message) {
        log.info("发送MQ消息, exchange=[{}], routingKey=[{}], message={}", exchange, routingKey,
                FastJsonUtil.toJSONString(message));
        rabbitTemplate.convertAndSend(exchange, routingKey, message, rabbitMessage -> rabbitMessage);
    }

    @Override
    public <T> void send(String routingKey, T message) {
        String exchange = BaseRabbitConfig.DEFAULT_QT_EXCHANGE;
        this.send(exchange, routingKey, message);
    }

}
