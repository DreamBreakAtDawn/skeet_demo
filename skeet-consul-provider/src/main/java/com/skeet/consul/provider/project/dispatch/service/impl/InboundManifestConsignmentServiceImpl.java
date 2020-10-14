package com.skeet.consul.provider.project.dispatch.service.impl;

import com.google.common.collect.Lists;
import com.skeet.consul.provider.project.dispatch.dao.InboundManifestConsignmentDao;
import com.skeet.consul.provider.project.dispatch.model.domain.ICDTO;
import com.skeet.consul.provider.project.dispatch.model.domain.InboundManifestConsignmentDTO;
import com.skeet.consul.provider.project.dispatch.model.domain.ManifestOperationDTO;
import com.skeet.consul.provider.project.dispatch.model.entity.InboundManifestConsignment;
import com.skeet.consul.provider.project.dispatch.service.InboundManifestConsignmentService;
import com.skeet.consul.provider.util.LambdaKit;
import com.skeet.consul.provider.util.SimpleConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 入库货单与入库委托单映射服务
 *
 * @author fengyub
 * @date 2020-04-21 15:16
 */
@Service
@Slf4j
public class InboundManifestConsignmentServiceImpl implements InboundManifestConsignmentService {

    @Resource
    private InboundManifestConsignmentDao inboundManifestConsignmentDao;

    @Override
    public InboundManifestConsignmentDTO obtainData(String manifestId, String operationNode, String consignmentNo) {
        return null;
    }

    @Override
    public List<InboundManifestConsignmentDTO> obtainDataByConsignmentNoAndNode(String consignmentNo, String operationNode) {
        InboundManifestConsignment criteria = InboundManifestConsignment.builder().build();
        criteria.setConsignmentNo(consignmentNo);
        criteria.setOperationNode(operationNode);
        return SimpleConverter.convert(inboundManifestConsignmentDao.select(criteria), InboundManifestConsignmentDTO.class);
    }

    @Override
    public int insertMapInfo(ManifestOperationDTO manifestOperationDTO) {
        String manifestId = manifestOperationDTO.getManifestId();
        String operationNode = manifestOperationDTO.getOperationNode();
        List<String> consignmentNoList = LambdaKit.convert(manifestOperationDTO.getIcDto(), ICDTO::getIc);

        List<InboundManifestConsignment> inboundManifestConsignments = Lists.newArrayList();
        consignmentNoList.forEach(consignmentNo -> {
            InboundManifestConsignment inboundManifestConsignment = InboundManifestConsignment.builder()
                    .manifestId(manifestId)
                    .operationNode(operationNode)
                    .consignmentNo(consignmentNo)
                    .build();
            inboundManifestConsignments.add(inboundManifestConsignment);
        });

        return inboundManifestConsignmentDao.insertSelectiveList(inboundManifestConsignments);
    }

}
