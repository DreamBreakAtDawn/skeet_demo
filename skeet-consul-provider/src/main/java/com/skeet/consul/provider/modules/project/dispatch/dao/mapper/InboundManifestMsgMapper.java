package com.skeet.consul.provider.modules.project.dispatch.dao.mapper;

import com.skeet.consul.provider.config.mapper.BaseMapper;
import com.skeet.consul.provider.modules.project.dispatch.model.domain.InboundManifestMsgDTO;
import com.skeet.consul.provider.modules.project.dispatch.model.entity.InboundManifestMsg;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Desc:
 *
 * @author chengsj
 * @date 2019/11/22 11:35
 */
@Mapper
public interface InboundManifestMsgMapper extends BaseMapper<InboundManifestMsg> {

    List<InboundManifestMsgDTO> queryNoneConsignmentMappingData();
}
