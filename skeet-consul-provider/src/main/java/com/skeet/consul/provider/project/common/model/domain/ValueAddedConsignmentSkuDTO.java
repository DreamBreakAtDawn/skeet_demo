package com.skeet.consul.provider.project.common.model.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Desc:增值服务委托单sku信息
 *
 * @author chengsj
 * @date 2020/4/28 15:43
 */
@Data
public class ValueAddedConsignmentSkuDTO {

    /**
     * 委托单号
     */
    private String consignmentNo;

    /**
     * 增值操作项
     */
    private String operationCode;

    /**
     * sku编码
     */
    private String skuCode;

    /**
     * 重量，单位千克
     */
    private BigDecimal weight;

    /**
     * 长，单位厘米
     */
    private BigDecimal length;

    /**
     * 宽，单位厘米
     */
    private BigDecimal width;

    /**
     * 高，单位厘米
     */
    private BigDecimal height;

    /**
     * 体积，单位立方厘米
     */
    private BigDecimal volume;

    /**
     * 操作数量
     */
    private Integer operationQty;

    /**
     * 实际sku编码
     */
    private String actualSkuCode;

    /**
     * 实际重量，单位千克
     */
    private BigDecimal actualWeight;

    /**
     * 实际长，单位厘米
     */
    private BigDecimal actualLength;

    /**
     * 实际宽，单位厘米
     */
    private BigDecimal actualWidth;

    /**
     * 实际高，单位厘米
     */
    private BigDecimal actualHeight;

    /**
     * 实际体积，单位立方厘米
     */
    private BigDecimal actualVolume;

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
