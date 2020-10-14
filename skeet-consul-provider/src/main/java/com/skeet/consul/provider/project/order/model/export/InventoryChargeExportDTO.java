/*
 * Copyright (c) 2018 4PX Information Technology Co.,Ltd. All rights reserved.
 */
package com.skeet.consul.provider.project.order.model.export;

import jdk.nashorn.internal.objects.annotations.Property;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author chenshzh
 * @date 2018年07月04日
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InventoryChargeExportDTO {

    /**
     * 业务代码（仓库-客户代码-日期）
     */
    @Property(name = "仓租单号")
    private String bsCode;
    /**
     * 客户编号
     */
    @Property(name = "客户编号")
    private String customerId;
    /**
     * 客户助记码
     */
    @Property(name = "客户助记码")
    private String customerCode;
    /**
     * 仓库编码
     */
    @Property(name = "仓库编码")
    private String warehouseCode;
    /**
     * 体积
     */
    @Property(name = "sku总体积（立方米）")
    private BigDecimal itmVolume;
    /**
     * 重量
     */
    @Property(name = "sku总重量（千克）")
    private BigDecimal itmWeight;
    /**
     * 数量
     */
    @Property(name = "sku数量")
    private Integer itmQuantity;

    /**
     * sku长
     */
    @Property(name = "sku长（厘米）")
    private BigDecimal length;

    /**
     * sku宽
     */
    @Property(name = "sku宽（厘米）")
    private BigDecimal width;

    /**
     * sku高
     */
    @Property(name = "sku高（厘米）")
    private BigDecimal height;

    /**
     * 当前周期内的计费天数（如计费周期7天计一次费，则计费天数最多为7天）
     */
    @Property(name = "计费天数")
    private Integer  chargeDayNum;

    /**
     * 库龄
     */
    @Property(name = "库龄")
    private Integer stockAge;

    /**
     * 季度
     */
    @Property(name = "季度")
    private String chargeSeason;

    /**
     * 商品标签条码
     */
    @Property(name = "商品条码")
    private String skuId;

    /**
     * 快照时间
     */
    @Property(name = "快照时间")
    private Integer snapshotTime;
}
