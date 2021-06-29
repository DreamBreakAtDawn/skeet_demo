package com.skeet.rabbitmq.controller;

import com.skeet.rabbitmq.constant.PrimaryTenderQueueConstant;
import com.skeet.rabbitmq.model.dto.BondDataChangeMessage;
import com.skeet.rabbitmq.service.RabbitMqService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description
 * @Author chengsj
 * @Date 2021/5/31 14:50
 */
@RestController
@RequestMapping("/skeet/rabbitmq")
public class RabbitMqController {

    @Resource
    private RabbitMqService rabbitMqService;

    @GetMapping("/send")
    public String send() {
        BondDataChangeMessage message = BondDataChangeMessage.builder()
                .openId("openId_1")
                .msgId("jayce_message")
                .build();
        rabbitMqService.send(PrimaryTenderQueueConstant.QUEUE_BOND_UPDATE, message);
        return "success";
    }
}
