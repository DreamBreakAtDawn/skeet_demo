package com.skeet.consul.provider.project.order.model.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 库存明细快照
 *
 * @author huangps
 * @date 2018/7/3.
 */
@Data
public class InventorySnapshotDTO {

    /**
     * tid
     */
    private Integer tid;

    /**
     * 快照记录的日期
     */
    private Integer snapshotTime;

    /**
     * 客户id
     */
    private String customerId;

    /**
     * 客户代码
     */
    private String customerCode;

    /**
     * 仓库代码
     */
    private String warehouseCode;

    /**
     * skuId
     */
    private String skuId;

    /**
     * sku code
     */
    private String skuCode;

    /**
     * sku名称
     */
    private String skuName;

    /**
     * 批次号
     */
    private String batchNo;

    /**
     * 有效期
     */
    private Date expiryDate;

    /**
     * 库存质量
     */
    private String stockQuality;

    /**
     * 委托单号
     */
    private String consignmentNo;

    /**
     * 在库库存数
     */
    private Integer warehouseStock;

    /**
     * 上架时间
     */
    private Date putawayTime;

    /**
     * 新增时间——copy inventory_detail的新增时间
     */
    private Date createTime;

    /**
     * 本地上架时间
     */
    private String localPutawayTime;

    /**
     * 单个sku的最小计费体积，单位：立方米
     */
    private BigDecimal minChargeVolume;
}
