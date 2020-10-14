package com.skeet.consul.provider.project.common.model.domain;

import lombok.Data;

import java.util.Date;

/**
 * Desc:增值服务委托单操作明细
 *
 * @author chengsj
 * @date 2020/4/28 15:38
 */
@Data
public class ValueAddedConsignmentOperationDetailDTO {

    /**
     * 委托单号
     */
    private String consignmentNo;

    /**
     * 增值操作项
     */
    private String operationCode;

    /**
     * 操作数量
     */
    private Integer operationQty;

    /**
     * 实际操作数量
     */
    private Integer actualOperationQty;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

}
