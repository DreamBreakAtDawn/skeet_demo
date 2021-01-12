package com.skeet.consul.provider.modules.project.order.model.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Desc:仓租通用查询条件
 *
 * @author chengsj
 * @date 2019/9/12 15:42
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InventoryCommonCriteria {

    /**
     * 快照时间
     */
    private Integer snapshotTime;

    /**
     * 客户代码
     */
    private String customerCode;

    /**
     * 客户编号
     */
    private String customerId;

    /**
     * 仓库代码集合，有多种含义：如
     * 同一时区的仓库；
     * 漏生成数据的仓库；
     * 需重新生成数据的仓库
     */
    private List<String> warehouseCodeList;

}
