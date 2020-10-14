package com.skeet.consul.provider.project.dispatch.model.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Desc:
 *
 * @author chengsj
 * @date 2019/11/23 11:01
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InboundManifestMsgDTO {

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
}
