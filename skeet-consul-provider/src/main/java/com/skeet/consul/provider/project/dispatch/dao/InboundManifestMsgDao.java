package com.skeet.consul.provider.project.dispatch.dao;

import com.skeet.consul.provider.config.mapper.BaseDao;
import com.skeet.consul.provider.project.dispatch.model.domain.InboundManifestMsgDTO;
import com.skeet.consul.provider.project.dispatch.model.entity.InboundManifestMsg;

import java.util.List;

/**
 * Desc:
 *
 * @author chengsj
 * @date 2019/11/22 11:32
 */
public interface InboundManifestMsgDao extends BaseDao<InboundManifestMsg> {

    /**
     * 获取数据
     *
     * @param manifestId    货单ID
     * @param operationNode 操作节点
     * @return 结果
     */
    List<InboundManifestMsg> obtainData(String manifestId, String operationNode);

    List<InboundManifestMsgDTO> queryNoneConsignmentMappingData();
}
