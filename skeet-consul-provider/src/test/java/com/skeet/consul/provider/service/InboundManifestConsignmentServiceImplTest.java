package com.skeet.consul.provider.service;

import com.google.common.collect.Lists;
import com.skeet.consul.provider.CommonAnnotationClass;
import com.skeet.consul.provider.project.dispatch.model.domain.ICDTO;
import com.skeet.consul.provider.project.dispatch.model.domain.InboundManifestMsgDTO;
import com.skeet.consul.provider.project.dispatch.model.domain.ManifestOperationDTO;
import com.skeet.consul.provider.project.dispatch.service.InboundManifestMsgService;
import com.skeet.consul.provider.util.FileUtil;
import com.skeet.consul.provider.util.JsonUtils;
import com.skeet.consul.provider.util.LambdaKit;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * Desc:
 *
 * @author chengsj
 * @date 2020/6/6 10:13
 */
public class InboundManifestConsignmentServiceImplTest extends CommonAnnotationClass {
    @Resource
    private InboundManifestMsgService inboundManifestMsgService;

    @Test
    public void testGenInsertSql() {
        List<String> sqlList = Lists.newArrayList();
        String template = "INSERT INTO `fb4_dispatch`.`inbound_manifest_consignment` (`manifest_id`, `operation_node`, `consignment_no`) VALUES ('%s', '%s', '%s');";

        List<InboundManifestMsgDTO> data = inboundManifestMsgService.queryNoneConsignmentMappingData();
        data.forEach(dto -> {
            String manifestId = dto.getManifestId();
            String operationNode = dto.getOperationNode();
            String extendFields = dto.getExtendFields();
            ManifestOperationDTO manifestOperationDTO = JsonUtils.jsonToBean(extendFields, ManifestOperationDTO.class);
            List<String> consignmentNos = LambdaKit.convertUnique(manifestOperationDTO.getIcDto(), ICDTO::getIc);
            consignmentNos.forEach(no -> sqlList.add(String.format(template, manifestId, operationNode, no)));
        });

//        sqlList.forEach(System.out::println);
        FileUtil.writeStringListToFile(FileUtil.appendPath(FileUtil.PARENT_WRITE_PATH, "SQL语句\\货单与入库委托单映射.txt"), sqlList);
    }
}
