package com.skeet.consul.provider.modules.project.order.model.export;

import jdk.nashorn.internal.objects.annotations.Property;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Desc:
 *
 * @author chengsj
 * @date 2019/11/13 16:16
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InventorySnapshotExportDTO {

    @Property(name = "仓库代码")
    private String warehouseCode;

    @Property(name = "客户助记码")
    private String customerCode;

    @Property(name = "快照时间")
    private Integer snapshotTime;

    @Property(name = "委托单号")
    private String consignmentNo;

    @Property(name = "skuId")
    private String skuId;

    @Property(name = "sku编码")
    private String skuCode;

    @Property(name = "sku名称")
    private String skuName;

    @Property(name = "sku数量")
    private Integer warehouseStock;

    @Property(name = "单个sku计费体积（立方米）")
    private BigDecimal singleSkuChargeVolume;

    @Property(name = "总体积（立方米）")
    private BigDecimal skuTotalVolume;

    @Property(name = "入库时间")
    private String putawayTime;

    @Property(name = "在库时间（库龄）")
    private Long stockAge;

    @Property(name = "单个sku最小计费体积（立方米）")
    private BigDecimal minVolume;

    @Property(name = "单个sku原始体积（立方米）")
    private BigDecimal singleSkuOriginVolume;

    @Property(name = "sku长（厘米）")
    private BigDecimal length;

    @Property(name = "sku宽（厘米）")
    private BigDecimal width;

    @Property(name = "sku高（厘米）")
    private BigDecimal height;
}
