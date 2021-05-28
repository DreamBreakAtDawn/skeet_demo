package com.skeet.mybatisplus.modules.bond.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 人员与申购债券关系表
 * </p>
 *
 * @author chengsj
 * @since 2021-04-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_primary_user_bond_relation")
public class PrimaryUserBondRelationEntity implements Serializable {


    @TableId(value = "Fid", type = IdType.AUTO)
    private Long id;

    /**
     * 申购券id
     */
    @TableField("Fsubscribe_bond_id")
    private Long subscribeBondId;

    /**
     * 债销机构id
     */
    @TableField("Forg_id")
    private String orgId;

    /**
     * 债销openId
     */
    @TableField("Fopen_id")
    private String openId;

    /**
     * 关系类型：1-投标，2-关注
     */
    @TableField("Frelation_type")
    private Integer relationType;

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
