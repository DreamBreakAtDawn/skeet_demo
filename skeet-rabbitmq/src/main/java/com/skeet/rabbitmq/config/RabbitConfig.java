package com.skeet.rabbitmq.config;

import com.skeet.rabbitmq.constant.PrimaryTenderQueueConstant;
import org.springframework.context.annotation.Configuration;

/**
 * @Description
 * @Author chengsj
 * @Date 2021/5/31 11:15
 */
@Configuration
public class RabbitConfig extends BaseRabbitConfig {

    @Override
    protected Class<?> getQueueConstantClass() {
        return PrimaryTenderQueueConstant.class;
    }
}
