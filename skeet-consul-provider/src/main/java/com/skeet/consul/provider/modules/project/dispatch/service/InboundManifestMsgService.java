package com.skeet.consul.provider.modules.project.dispatch.service;

import com.skeet.consul.provider.modules.project.dispatch.model.domain.InboundManifestMsgDTO;
import com.skeet.consul.provider.modules.project.dispatch.model.domain.ManifestOperationDTO;

import java.util.List;

/**
 * Desc:
 *
 * @author chengsj
 * @date 2019/11/22 11:31
 */
public interface InboundManifestMsgService {

    /**
     * 获取数据
     *
     * @param manifestId    货单ID
     * @param operationNode 操作节点
     * @return 结果
     */
    InboundManifestMsgDTO obtainData(String manifestId, String operationNode);

    /**
     * 如果数据不存在则插入
     *
     * @param manifestOperationDTO 货单数据
     * @return 结果
     */
    int insertIfNotExist(ManifestOperationDTO manifestOperationDTO);

    /**
     * 查询没有对应委托单映射的货单ID
     *
     * @return
     */
    List<InboundManifestMsgDTO> queryNoneConsignmentMappingData();

    /**
     * 根据货单ID查询数据
     *
     * @param manifiestIds
     * @return
     */
    List<InboundManifestMsgDTO> selectByManiestIds(List<String> manifiestIds);
}
