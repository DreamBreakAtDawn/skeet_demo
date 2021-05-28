package com.skeet.mybatisplus.modules.bond.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 申购招投标表
 * </p>
 *
 * @author chengsj
 * @since 2021-04-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_primary_subscribe_tender")
public class PrimarySubscribeTenderEntity implements Serializable {


    @TableId(value = "Fid", type = IdType.AUTO)
    private Long id;

    /**
     * 状态 0:已确认(已提交) 1:已撤销 2:待提交(已确认) 3:修改中 4:撤销中  5:已退回
     */
    @TableField("Fstatus")
    private Integer status;

    /**
     * 申购券ID
     */
    @TableField("Fsubscribe_bond_id")
    private Long subscribeBondId;

    /**
     * 利率（%）
     */
    @TableField("Frate")
    private BigDecimal rate;

    /**
     * 利差（%）
     */
    @TableField("Frate_margin")
    private BigDecimal rateMargin;

    /**
     * 价格
     */
    @TableField("Fprice")
    private BigDecimal price;

    /**
     * 量（亿）
     */
    @TableField("Fvol")
    private BigDecimal vol;

    /**
     * 债销人-openId
     */
    @TableField("Fopen_id")
    private String openId;

    /**
     * 债销人-姓名
     */
    @TableField("Fuser_name")
    private String userName;

    /**
     * 债券人-类型
     */
    @TableField("Fuser_type")
    private Integer userType;

    /**
     * 债销人-机构id
     */
    @TableField("Forg_id")
    private String orgId;

    /**
     * 债销人-机构名称
     */
    @TableField("Forg_name")
    private String orgName;

    /**
     * 投资人-openId
     */
    @TableField("Fopp_open_id")
    private String oppOpenId;

    /**
     * 投资人-姓名
     */
    @TableField("Fopp_user_name")
    private String oppUserName;

    /**
     * 投资人-类型
     */
    @TableField("Fopp_user_type")
    private Integer oppUserType;

    /**
     * 投资人-机构id
     */
    @TableField("Fopp_org_id")
    private String oppOrgId;

    /**
     * 投资人-机构名称
     */
    @TableField("Fopp_org_name")
    private String oppOrgName;

    /**
     * 数据状态：0-删除，1-启用
     */
    @TableField("Fdata_status")
    private Integer dataStatus;

    /**
     * 数据来源
     */
    @TableField("Fdata_source")
    private Integer dataSource;

    /**
     * 创建时间
     */
    @TableField("Fcreated_time")
    private LocalDateTime createdTime;

    /**
     * 更新时间
     */
    @TableField("Fupdated_time")
    private LocalDateTime updatedTime;


}
