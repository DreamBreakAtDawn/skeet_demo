package com.skeet.consul.provider.modules.project.common.model.domain;

import com.google.common.collect.Lists;
import com.skeet.consul.provider.util.JsonUtils;
import com.skeet.consul.provider.util.ReflectUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Desc:客服确认完成增值服务委托单对象
 *
 * @author chengsj
 * @date 2020/5/19 16:09
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ValueAddedConsignmentOperatorConfirmDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 委托单号
     */
    private String consignmentNo;

    /**
     * 工时
     */
    private BigDecimal workingHour;

    /**
     * 仓库备注
     */
    private String warehouseRemark;

    /**
     * 仓库上传附件
     */
    private String warehouseAttachmentUrl;

    /**
     * sku数据
     */
    List<ValueAddedConsignmentSkuDTO> skuDTOs;

    /**
     * 操作明细数据
     */
    List<ValueAddedConsignmentOperationDetailDTO> operationDetailDTOs;

    /**
     * 操作明细数据
     */
    List<ValueAddedConsignmentOperationFeeDTO> operationFeeDTOs;

    public static void main(String[] args) {
        ValueAddedConsignmentOperatorConfirmDTO obj = new ValueAddedConsignmentOperatorConfirmDTO();
        ReflectUtil.setDefaultValue(obj);
        obj.setSkuDTOs(Lists.newArrayList(ReflectUtil.setDefaultValue(new ValueAddedConsignmentSkuDTO())));
        obj.setOperationDetailDTOs(Lists.newArrayList(ReflectUtil.setDefaultValue(new ValueAddedConsignmentOperationDetailDTO())));
        obj.setOperationFeeDTOs(Lists.newArrayList(ReflectUtil.setDefaultValue(new ValueAddedConsignmentOperationFeeDTO())));
        System.out.println(JsonUtils.beanToJson(obj));
    }
}
