package com.skeet.consul.provider.project.order.model.domain;

import com.skeet.consul.provider.project.common.model.domain.StockContractWarehouseConfigDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Desc:仓租快照查询条件
 *
 * @author chengsj
 * @data 2019/1/7 11:28
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InventorySnapshotCriteria {

    /**
     * 仓库代码，以逗号分隔的多个仓库代码组成的字符串
     */
    private String warehouseCode;

    /**
     * 区间代码
     */
    private String area;

    /**
     * 快照起始日期
     */
    private Integer fromSnapshotTime;

    /**
     * 快照截止日期
     */
    private Integer toSnapshotTime;

    /**
     * 快照日期
     */
    private Integer snapshotTime;

    /**
     * 快照时间集合
     */
    private List<Integer> snapshotTimes;

    /**
     * 计费周期表达式
     */
    private String cron;

    /**
     * 最小体积按0.001立方米计算的客户
     */
    private String minVolumeCustomerCode;

    /**
     * 计费周期
     */
    private String period;

    /**
     * 菜鸟ICBU客户集合
     */
    private List<String> icbuCustomerIds;

    /**
     * 客户助记码
     */
    private String customerCode;

    /**
     * 是否使用最小体积客户的配置，N：不使用，其他情况：使用
     */
    private String useMinVolumeCustomer;

    /**
     * 仓库代码集合，有多种含义：如
     * 同一时区的仓库；
     * 漏生成数据的仓库；
     * 需重新生成数据的仓库
     */
    private List<String> warehouseCodeList;

    /**
     * 包仓配置集合
     */
    private List<StockContractWarehouseConfigDTO> contractConfigList;

    /**
     * 客户集合
     */
    private List<String> customerIds;
}
