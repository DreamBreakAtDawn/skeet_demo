package com.skeet.consul.provider.modules.project.order.dao.impl;

import com.skeet.consul.provider.config.mapper.BaseDaoImpl;
import com.skeet.consul.provider.config.mapper.BaseMapper;
import com.skeet.consul.provider.modules.project.order.dao.InventorySnapshotDao;
import com.skeet.consul.provider.modules.project.order.dao.mapper.InventorySnapshotMapper;
import com.skeet.consul.provider.modules.project.order.model.domain.InventoryCommonCriteria;
import com.skeet.consul.provider.modules.project.order.model.domain.InventorySnapshotCriteria;
import com.skeet.consul.provider.modules.project.order.model.domain.InventorySnapshotDTO;
import com.skeet.consul.provider.modules.project.order.model.entity.InventorySnapshot;
import com.skeet.consul.provider.modules.project.order.model.export.InventoryChargeExportDTO;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author huangps
 * @date 2018/7/3.
 */
@Repository
public class InventorySnapshotDaoImpl extends BaseDaoImpl<InventorySnapshot> implements InventorySnapshotDao {

    @Resource
    private InventorySnapshotMapper inventorySnapshotMapper;

    @Override
    protected BaseMapper<InventorySnapshot> getBaseMapper() {
        return inventorySnapshotMapper;
    }

    @Override
    public void insertInventorySnapshot(InventoryCommonCriteria criteria) {
        inventorySnapshotMapper.insertInventorySnapshot(criteria);
    }

    @Override
    public void insertInventorySnapshotList(List<InventorySnapshot> snapshots) {
        inventorySnapshotMapper.insertInventorySnapshotList(snapshots);
    }

    @Override
    public int selectDataCount(InventoryCommonCriteria criteria) {
        Integer snapshotTime = criteria.getSnapshotTime();
        List<String> warehouseCodeList = criteria.getWarehouseCodeList();

        Example example = new Example(InventorySnapshot.class);
        example.createCriteria()
                .andEqualTo("snapshotTime", snapshotTime)
                .andIn("warehouseCode", warehouseCodeList);
//        return inventorySnapshotMapper.selectCountByExample(example);
        return 0;
    }

    @Override
    public List<String> selectWarehouseCode(Integer snapshotTime) {
        return inventorySnapshotMapper.selectWarehouseCode(snapshotTime);
    }

    @Override
    public List<InventoryChargeExportDTO> selectChargeData(InventorySnapshotCriteria criteria) {
        return inventorySnapshotMapper.selectChargeData(criteria);
    }

    @Override
    public List<InventorySnapshotDTO> selectBy(InventorySnapshotCriteria criteria) {
        return inventorySnapshotMapper.selectBy(criteria);
    }
}
