package com.skeet.consul.provider.modules.project.dispatch.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 入库货单与委托单映射关系实体
 *
 * @author fengyub
 * @date 2020-04-21 14:25
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InboundManifestConsignment {

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
     * 委托单号
     */
    private String consignmentNo;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;
}
