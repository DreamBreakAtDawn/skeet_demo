package com.skeet.consul.provider.modules.project.qtrade.ai.entity;

import lombok.Data;

import java.util.Date;

/**
 * Desc:
 *
 * @author chengsj
 * @date 2020/10/20 11:31
 */
@Data
public class MarkProjectData {

    /**
     * 主键
     */
    private Integer tid;

    /**
     * 项目ID
     */
    private String projectId;

    /**
     * 文本序号
     */
    private Integer textSequence;

    /**
     * 原始文本
     */
    private String originalText;

    /**
     * 实体
     */
    private String markEntity;

    /**
     * 意图
     */
    private String markIntention;

    /**
     * 文本类别
     */
    private String markType;

    /**
     * 结构化数据
     */
    private String structuredData;

    /**
     * 标准化数据
     */
    private String standardisedData;

    /**
     * 标注状态
     */
    private String status;

    /**
     * 标注人
     */
    private String markUsername;

    /**
     * 耗时，单位毫秒
     */
    private Integer timeConsuming;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;
}
