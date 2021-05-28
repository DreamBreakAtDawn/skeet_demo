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
 * 申购债券表
 * </p>
 *
 * @author chengsj
 * @since 2021-04-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_primary_subscribe_bond")
public class PrimarySubscribeBondEntity implements Serializable {


    @TableId(value = "Fid", type = IdType.AUTO)
    private Long id;

    /**
     * 机构id
     */
    @TableField("Forg_id")
    private String orgId;

    /**
     * 债券id
     */
    @TableField("Fbond_id")
    private Long bondId;

    /**
     * 债券来源，1-新债库
     */
    @TableField("Fbond_source")
    private Integer bondSource;

    /**
     * 债券代码
     */
    @TableField("Fbond_code")
    private String bondCode;

    /**
     * 债券简称
     */
    @TableField("Fbond_simple_name")
    private String bondSimpleName;

    /**
     * 债券全称
     */
    @TableField("Fbond_full_name")
    private String bondFullName;

    /**
     * 债券类型 1：利率债，2：信用债
     */
    @TableField("Fbond_type")
    private Integer bondType;

    /**
     * 发行规模
     */
    @TableField("Fissue_amount")
    private BigDecimal issueAmount;

    /**
     * 发行市场
     */
    @TableField("Fexchange_market")
    private String exchangeMarket;

    /**
     * 招标方式：0-荷兰式，1-美国式，2-混合式
     */
    @TableField("Ftender_type")
    private Integer tenderType;

    /**
     * 招标标的：1-数量，2-利率，3-利差，4-价格
     */
    @TableField("Ftender_target")
    private Integer tenderTarget;

    /**
     * 是否已公告
     */
    @TableField("Fannouncement_flag")
    private Integer announcementFlag;

    /**
     * 是否截标
     */
    @TableField("Fintercept_flag")
    private Integer interceptFlag;

    /**
     * 实际截标时间
     */
    @TableField("Freal_intercept_time")
    private String realInterceptTime;

    /**
     * 是否中标
     */
    @TableField("Fsuccessful_bid_flag")
    private Integer successfulBidFlag;

    /**
     * 数据状态：0-删除，1-启用
     */
    @TableField("Fdata_status")
    private Integer dataStatus;

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
