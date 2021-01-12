package com.skeet.consul.provider.modules.project.dispatch.dao.impl;

import com.skeet.consul.provider.config.mapper.BaseDaoImpl;
import com.skeet.consul.provider.config.mapper.BaseMapper;
import com.skeet.consul.provider.modules.project.dispatch.dao.InboundManifestConsignmentDao;
import com.skeet.consul.provider.modules.project.dispatch.dao.mapper.InboundManifestConsignmentMapper;
import com.skeet.consul.provider.modules.project.dispatch.model.entity.InboundManifestConsignment;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author fengyub
 * @date 2020-04-21 14:33
 */
@Repository
public class InboundManifestConsignmentDaoImpl extends BaseDaoImpl<InboundManifestConsignment> implements InboundManifestConsignmentDao {

    @Resource
    private InboundManifestConsignmentMapper inboundManifestConsignmentMapper;

    @Override
    protected BaseMapper<InboundManifestConsignment> getBaseMapper() {
        return inboundManifestConsignmentMapper;
    }

    @Override
    public List<InboundManifestConsignment> obtainData(String manifestId, String operationNode, String consignmentNo) {
        Example example = new Example(InboundManifestConsignment.class);
        example.createCriteria()
                .andEqualTo("manifestId", manifestId)
                .andEqualTo("operationNode", operationNode)
                .andEqualTo("consignmentNo", consignmentNo);

        return inboundManifestConsignmentMapper.selectByExample(example);
    }
}
