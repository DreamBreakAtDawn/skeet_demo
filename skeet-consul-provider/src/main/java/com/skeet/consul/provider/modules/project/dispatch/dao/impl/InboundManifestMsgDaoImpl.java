package com.skeet.consul.provider.modules.project.dispatch.dao.impl;

import com.skeet.consul.provider.config.mapper.BaseDaoImpl;
import com.skeet.consul.provider.config.mapper.BaseMapper;
import com.skeet.consul.provider.modules.project.dispatch.dao.InboundManifestMsgDao;
import com.skeet.consul.provider.modules.project.dispatch.dao.mapper.InboundManifestMsgMapper;
import com.skeet.consul.provider.modules.project.dispatch.model.domain.InboundManifestMsgDTO;
import com.skeet.consul.provider.modules.project.dispatch.model.entity.InboundManifestMsg;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Desc:
 *
 * @author chengsj
 * @date 2019/11/22 11:33
 */
@Repository
public class InboundManifestMsgDaoImpl extends BaseDaoImpl<InboundManifestMsg> implements InboundManifestMsgDao {

    @Resource
    private InboundManifestMsgMapper inboundManifestMsgMapper;

    @Override
    protected BaseMapper<InboundManifestMsg> getBaseMapper() {
        return inboundManifestMsgMapper;
    }

    @Override
    public List<InboundManifestMsg> obtainData(String manifestId, String operationNode) {
//        Example example = new Example(InboundManifestMsg.class);
//        example.createCriteria()
//                .andEqualTo("manifestId", manifestId)
//                .andEqualTo("operationNode", operationNode);
//
//        return inboundManifestMsgMapper.selectByExample(example);
        return null;
    }

    @Override
    public List<InboundManifestMsgDTO> queryNoneConsignmentMappingData() {
        return inboundManifestMsgMapper.queryNoneConsignmentMappingData();
    }
}
