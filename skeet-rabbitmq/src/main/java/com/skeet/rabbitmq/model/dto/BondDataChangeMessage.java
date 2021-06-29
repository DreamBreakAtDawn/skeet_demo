package com.skeet.rabbitmq.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Description
 * @Author chengsj
 * @Date 2020/12/8 19:59
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BondDataChangeMessage implements Serializable {

    private static final long serialVersionUID = 491807123918537181L;

    /**
     * 用户openId，可能为空
     */
    private String openId;

    /**
     * 消息Id
     */
    private String msgId;

    /**
     * 来源，可能为空
     */
    private String origin;
}
