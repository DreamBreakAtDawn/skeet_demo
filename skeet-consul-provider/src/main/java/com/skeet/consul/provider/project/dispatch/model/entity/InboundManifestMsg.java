package com.skeet.consul.provider.project.dispatch.model.entity;

import lombok.Data;

import java.util.Date;

/**
 * Desc:
 *
 * @author chengsj
 * @date 2019/11/22 11:32
 */
@Data
public class InboundManifestMsg {

    /**
     * 主键
     */
    private Integer tid;

    /**
     * 货单ID，唯一值，TMS提供
     */
    private String manifestId;

    /**
     * 操作节点，如清关（CL），发运（SP），起飞（TF）
     */
    private String operationNode;

    /**
     * 扩展字段，json格式
     */
    private String extendFields;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;
}
