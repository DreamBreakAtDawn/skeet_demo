package com.skeet.consul.provider.modules.project.order.dao.mapper;

import com.skeet.consul.provider.config.mapper.BaseMapper;
import com.skeet.consul.provider.modules.project.order.model.domain.InventoryCommonCriteria;
import com.skeet.consul.provider.modules.project.order.model.domain.InventorySnapshotCriteria;
import com.skeet.consul.provider.modules.project.order.model.domain.InventorySnapshotDTO;
import com.skeet.consul.provider.modules.project.order.model.entity.InventorySnapshot;
import com.skeet.consul.provider.modules.project.order.model.export.InventoryChargeExportDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author huangps
 * @date 2018/7/3.
 */
@Mapper
public interface InventorySnapshotMapper extends BaseMapper<InventorySnapshot> {

    void insertInventorySnapshot(@Param("criteria") InventoryCommonCriteria criteria);

    /**
     * 查找已生成快照的所有仓库
     *
     * @param snapshotTime 快照时间
     * @return 仓库集合
     */
    List<String> selectWarehouseCode(@Param("snapshotTime") Integer snapshotTime);

    /**
     * 批量插入库存快照数据
     */
    void insertInventorySnapshotList(@Param("snapshots") List<InventorySnapshot> snapshots);

    List<InventoryChargeExportDTO> selectChargeData(@Param("criteria") InventorySnapshotCriteria criteria);

    List<InventorySnapshotDTO> selectBy(InventorySnapshotCriteria criteria);
}
