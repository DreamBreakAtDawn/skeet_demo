package com.skeet.rabbitmq.listener;

import com.rabbitmq.client.Channel;
import com.skeet.common.api.util.FastJsonUtil;
import com.skeet.rabbitmq.constant.PrimaryTenderQueueConstant;
import com.skeet.rabbitmq.model.dto.BondDataChangeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Description
 * @Author chengsj
 * @Date 2021/5/31 18:45
 */
@Component
@Slf4j
public class RabbitMqListener {

    @RabbitListener(queues = PrimaryTenderQueueConstant.QUEUE_BOND_UPDATE)
    public void process(@Payload BondDataChangeMessage message,
                        Message message2,
                        @Headers Map<String, Object> headers,
                        @Header("amqp_receivedDeliveryMode") String header1,
                        Channel channel) {
        log.info("接收MQ消息");
        log.info("message:【{}】", FastJsonUtil.toJSONString(message));
        log.info("message2:【{}】", FastJsonUtil.toJSONString(message2));
        log.info("body:【{}】", new String(message2.getBody()));
        log.info("headers:【{}】", FastJsonUtil.toJSONString(headers));
        log.info("header1:【{}】", FastJsonUtil.toJSONString(header1));
        log.info("channel:【{}】", FastJsonUtil.toJSONString(channel));
    }
}
