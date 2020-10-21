package com.skeet.consul.provider.project.qtrade.ai.entity;

import lombok.Data;

import java.util.Date;

/**
 * Desc:
 *
 * @author chengsj
 * @date 2020/10/20 11:31
 */
@Data
public class MarkProject {

    /**
     * 主键
     */
    private Integer tid;

    /**
     * 项目ID
     */
    private String projectId;

    /**
     * 项目名称
     */
    private String name;

    /**
     * 项目描述
     */
    private String description;

    /**
     * 项目所有者
     */
    private String createUsername;

    /**
     * 导入时间
     */
    private Date importTime;

    /**
     * 项目类型
     */
    private String projectType;

    /**
     * 数据类型，如单轮/多轮
     */
    private String dataType;

    /**
     * 标注数据完成数
     */
    private Integer dataFinishCount;

    /**
     * 标注数据总数
     */
    private Integer dataTotalCount;

    /**
     * 备注
     */
    private String remark;

    /**
     * 标签配置
     */
    private String labelConfig;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;
}
