/*
 * Copyright (c) 2018 4PX Information Technology Co.,Ltd. All rights reserved.
 */
package com.skeet.consul.provider.modules.project.order.model.domain;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author chenshzh
 * @date 2018年07月04日
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InventoryChargeDTO implements Serializable {

    private static final long serialVersionUID = -7206480478555144169L;

    /**
     * 业务代码（仓库-客户代码-日期）
     */
    private String bsCode;
    /**
     * 票号
     */
    private String billNo;
    /**
     * 客户ID
     */
    private String customerId;
    /**
     * 客户编号
     */
    private String customerCode;
    /**
     * 仓库编码
     */
    private String warehouseCode;
    /**
     * 体积
     */
    private BigDecimal itmVolume;
    /**
     * 重量
     */
    private BigDecimal itmWeight;
    /**
     * 数量
     */
    private Integer itmQuantity;
    /**
     * 计费区间代码
     */
    private String libraryAgeCode;
    /**
     * 计费截止时间
     */
    private Date itmEnddate;
    /**
     * 是否计费（Y，N）
     */
    private String itmSign;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 重新计费次数
     */
    private Integer chargeNum;
    /**
     * 库位类型
     */
    @SerializedName(value = "locationType", alternate = {"extraCode"})
    private String locationType;

    /**
     * sku长
     */
    private BigDecimal length;

    /**
     * sku宽
     */
    private BigDecimal width;

    /**
     * sku高
     */
    private BigDecimal height;

    /**
     * 当前周期内的计费天数（如计费周期7天计一次费，则计费天数最多为7天）
     */
    private Integer chargeDayNum;

    /**
     * 库龄
     */
    private Integer stockAge;

    /**
     * 季度
     */
    private String chargeSeason;

    /**
     * 商品标签条码
     */
    private String skuId;

    /**
     * 请求唯一id
     */
    private String requestId;

    /**
     * 单条数据的唯一id，通过snow_flake算法生成
     */
    private String uid;

    /**
     * 计费模式
     */
    private String chargeMode;

    /**
     * 计费时间
     */
    private Date chargeTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 快照时间
     */
    private Integer snapshotTime;

    /**
     * 是否为有效数据
     */
    private String isValid;

    /**
     * 操作段
     */
    private String operationSegment;
}
