package com.skeet.consul.provider.project.common.model.domain;

import lombok.Data;

import java.util.Date;

/**
 * Desc:增值服务委托单包裹信息
 *
 * @author chengsj
 * @date 2020/4/28 15:46
 */
@Data
public class ValueAddedConsignmentPackageDTO {

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
     * 相关的业务单号
     */
    private String relativeBusinessNo;

    /**
     * 相关的业务单类型
     */
    private String relativeBusinessType;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}
