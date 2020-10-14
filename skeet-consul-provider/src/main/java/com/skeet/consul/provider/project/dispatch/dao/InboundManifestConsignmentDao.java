package com.skeet.consul.provider.project.dispatch.dao;

import com.skeet.consul.provider.config.mapper.BaseDao;
import com.skeet.consul.provider.project.dispatch.model.entity.InboundManifestConsignment;

import java.util.List;


/**
 * @author fengyub
 * @date 2020-04-21 14:29
 */

public interface InboundManifestConsignmentDao extends BaseDao<InboundManifestConsignment> {

    /**
     * 获取数据
     *
     * @param manifestId 货单ID
     * @param operationNode 操作节点
     * @param consignmentNo  委托单号
     * @return 结果
     */
    List<InboundManifestConsignment> obtainData(String manifestId, String operationNode, String consignmentNo);

}
