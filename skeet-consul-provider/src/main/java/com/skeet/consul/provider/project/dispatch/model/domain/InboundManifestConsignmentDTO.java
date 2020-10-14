package com.skeet.consul.provider.project.dispatch.model.domain;

import lombok.Data;

/**
 * @author fengyub
 * @date 2020-04-21 14:27
 */
@Data
public class InboundManifestConsignmentDTO {

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
}
