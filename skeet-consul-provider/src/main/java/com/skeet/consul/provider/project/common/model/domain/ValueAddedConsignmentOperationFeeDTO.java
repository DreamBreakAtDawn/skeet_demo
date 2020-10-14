package com.skeet.consul.provider.project.common.model.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Desc:增值服务委托单操作费用
 *
 * @author chengsj
 * @date 2020/4/28 15:41
 */
@Data
public class ValueAddedConsignmentOperationFeeDTO {

    /**
     * 委托单号
     */
    private String consignmentNo;

    /**
     * 增值操作项
     */
    private String operationCode;

    /**
     * 金额
     */
    private BigDecimal feeAmount;

    /**
     * 币种
     */
    private String feeCurrency;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}
