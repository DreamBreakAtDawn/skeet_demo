package com.skeet.consul.provider.project.common.model.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Desc:
 *
 * @author chengsj
 * @date 2019/9/11 20:51
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StockContractWarehouseConfigDTO {

    /**
     * 客户编号
     */
    private String customerId;

    /**
     * 仓库编码
     */
    private String warehouseCode;

    /**
     * 生效时间，格式如：20190909
     */
    private Integer effectiveTime;

    /**
     * 失效时间，格式如：99991231
     */
    private Integer ineffectiveTime;

    /**
     * 状态，生效/失效
     */
    private String effectiveStatus;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 修改人
     */
    private String updateUser;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;
}
