package com.skeet.consul.provider.modules.project.dispatch.service.impl;

import com.skeet.consul.provider.modules.project.dispatch.dao.InboundManifestMsgDao;
import com.skeet.consul.provider.modules.project.dispatch.model.domain.InboundManifestMsgDTO;
import com.skeet.consul.provider.modules.project.dispatch.model.domain.ManifestOperationDTO;
import com.skeet.consul.provider.modules.project.dispatch.model.entity.InboundManifestMsg;
import com.skeet.consul.provider.modules.project.dispatch.service.InboundManifestMsgService;
import com.skeet.consul.provider.util.JsonUtils;
import com.skeet.consul.provider.util.SimpleConverter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * Desc:
 *
 * @author chengsj
 * @date 2019/11/22 11:31
 */
@Service
@Slf4j
public class InboundManifestMsgServiceImpl implements InboundManifestMsgService {

    @Resource
    private InboundManifestMsgDao inboundManifestMsgDao;

    @Override
    public InboundManifestMsgDTO obtainData(String manifestId, String operationNode) {
        List<InboundManifestMsg> inboundManifestMsgs = inboundManifestMsgDao.obtainData(manifestId, operationNode);
        return CollectionUtils.isEmpty(inboundManifestMsgs) ? null : SimpleConverter.convert(inboundManifestMsgs.get(0), InboundManifestMsgDTO.class);
    }

    @Override
    public int insertIfNotExist(ManifestOperationDTO manifestOperationDTO) {
        if (Objects.isNull(manifestOperationDTO)) {
            log.warn("ManifestOperationDTO对象为空，不做插入操作");
            return 0;
        }

        String manifestId = manifestOperationDTO.getManifestId();
        String operationNode = manifestOperationDTO.getOperationNode();

        InboundManifestMsgDTO inboundManifestMsgDTO = this.obtainData(manifestId, operationNode);
        if (Objects.nonNull(inboundManifestMsgDTO)) {
            log.warn("【{}】【{}】已有TMS操作数据，不再插入", manifestId, operationNode);
            return 0;
        }

        InboundManifestMsg insertData = new InboundManifestMsg();
        insertData.setManifestId(manifestId);
        insertData.setOperationNode(operationNode);
        insertData.setExtendFields(JsonUtils.beanToJson(manifestOperationDTO));
        return inboundManifestMsgDao.insertSelective(insertData);
    }

    @Override
    public List<InboundManifestMsgDTO> queryNoneConsignmentMappingData() {
        return inboundManifestMsgDao.queryNoneConsignmentMappingData();
    }

    @Override
    public List<InboundManifestMsgDTO> selectByManiestIds(List<String> manifiestIds) {
        return null;
    }
}
