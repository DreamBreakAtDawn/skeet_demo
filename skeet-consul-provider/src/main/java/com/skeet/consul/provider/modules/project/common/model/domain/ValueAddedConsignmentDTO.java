package com.skeet.consul.provider.modules.project.common.model.domain;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.skeet.consul.provider.util.JsonUtils;
import com.skeet.consul.provider.util.ReflectUtil;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Desc:增值服务委托单
 *
 * @author chengsj
 * @date 2020/4/28 14:16
 */
@Data
public class ValueAddedConsignmentDTO {

    /**
     * 委托单号
     */
    private String consignmentNo;

    /**
     * 客户编号
     */
    private String customerId;

    /**
     * 客户助记码
     */
    private String customerCode;

    /**
     * 物理仓代码
     */
    private String warehouseCode;

    /**
     * 逻辑仓代码
     */
    private String logicWarehouseCode;

    /**
     * 物流产品
     */
    private String logisticsProduct;

    /**
     * 运输渠道
     */
    private String transportChannel;

    /**
     * 业务类型
     */
    private String businessType;

    /**
     * 所属作业段
     */
    private String operationSegment;

    /**
     * 增值服务类型
     */
    private String serviceType;

    /**
     * 预计完成时间
     */
    private Date expectCompleteTime;

    /**
     * 数据来源
     */
    private String dataSource;

    /**
     * 状态
     */
    private String status;

    /**
     * 子状态
     */
    private String subStatus;

    /**
     * 审核时间
     */
    private Date approveTime;

    /**
     * 完成时间
     */
    private Date completeTime;

    /**
     * 工时
     */
    private BigDecimal workingHour;

    /**
     * 客户上传附件
     */
    private String customerAttachmentUrl;

    /**
     * 客户备注
     */
    private String customerRemark;

    /**
     * 客户上传附件
     */
    private String warehouseAttachmentUrl;

    /**
     * 仓库备注
     */
    private String warehouseRemark;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 修改人
     */
    private String updateUser;

    /**
     * sku集合
     */
    List<ValueAddedConsignmentSkuDTO> skuDTOs;

    /**
     * 包裹集合
     */
    List<ValueAddedConsignmentPackageDTO> packageDTOs;

    Map<String, ValueAddedConsignmentPackageDTO> packageDTOMap;

    public static void main(String[] args) {
        ValueAddedConsignmentDTO obj = new ValueAddedConsignmentDTO();
        ReflectUtil.setDefaultValue(obj);
        obj.setSkuDTOs(Lists.newArrayList());
        obj.setPackageDTOs(Lists.newArrayList());
        HashMap<String, ValueAddedConsignmentPackageDTO> map = Maps.newHashMap();
        map.put("A", new ValueAddedConsignmentPackageDTO());
        map.put("B", new ValueAddedConsignmentPackageDTO());
        obj.setPackageDTOMap(map);
        System.out.println(JsonUtils.beanToJson(obj));
    }
}
