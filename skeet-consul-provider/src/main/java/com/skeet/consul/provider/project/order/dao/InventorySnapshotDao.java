package com.skeet.consul.provider.project.order.dao;

import com.skeet.consul.provider.config.mapper.BaseDao;
import com.skeet.consul.provider.project.order.model.domain.InventoryCommonCriteria;
import com.skeet.consul.provider.project.order.model.domain.InventorySnapshotCriteria;
import com.skeet.consul.provider.project.order.model.domain.InventorySnapshotDTO;
import com.skeet.consul.provider.project.order.model.entity.InventorySnapshot;
import com.skeet.consul.provider.project.order.model.export.InventoryChargeExportDTO;

import java.util.List;

/**
 * @author huangps
 * @date 2018/7/3.
 */
public interface InventorySnapshotDao extends BaseDao<InventorySnapshot> {

    /**
     * 插入库存快照
     */
    void insertInventorySnapshot(InventoryCommonCriteria criteria);

    /**
     * 批量插入库存快照
     */
    void insertInventorySnapshotList(List<InventorySnapshot> snapshots);

    /**
     * 查找数据条数
     */
    int selectDataCount(InventoryCommonCriteria criteria);

    /**
     * 查找已生成快照的所有仓库
     *
     * @param snapshotTime 快照时间
     * @return 仓库集合
     */
    List<String> selectWarehouseCode(Integer snapshotTime);

    List<InventoryChargeExportDTO> selectChargeData(InventorySnapshotCriteria criteria);

    List<InventorySnapshotDTO> selectBy(InventorySnapshotCriteria criteria);
}
