package com.skeet.consul.provider.project.dispatch.service;

import com.skeet.consul.provider.project.dispatch.model.domain.InboundManifestConsignmentDTO;
import com.skeet.consul.provider.project.dispatch.model.domain.ManifestOperationDTO;

import java.util.List;

/**
 * @author fengyub
 * @date 2020-04-21 14:41
 */
public interface InboundManifestConsignmentService {

    /**
     * 获取数据
     *
     * @param manifestId    货单ID
     * @param operationNode 操作节点
     * @param consignmentNo 委托单号
     * @return 结果
     */
    InboundManifestConsignmentDTO obtainData(String manifestId, String operationNode, String consignmentNo);

    /**
     * 根据委托单号和节点获取数据
     *
     * @param consignmentNo 委托单号
     * @return 结果
     */
    List<InboundManifestConsignmentDTO> obtainDataByConsignmentNoAndNode(String consignmentNo, String operationNode);

    /**
     * 插入映射关系
     *
     * @param manifestOperationDTO 货单数据
     * @return 结果
     */
    int insertMapInfo(ManifestOperationDTO manifestOperationDTO);
}
