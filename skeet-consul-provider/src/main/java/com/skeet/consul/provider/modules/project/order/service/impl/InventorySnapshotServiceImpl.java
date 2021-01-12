package com.skeet.consul.provider.modules.project.order.service.impl;

import com.skeet.consul.provider.constant.FilePathConstant;
import com.skeet.consul.provider.modules.project.order.dao.InventorySnapshotDao;
import com.skeet.consul.provider.modules.project.order.model.domain.InventorySnapshotCriteria;
import com.skeet.consul.provider.modules.project.order.model.domain.InventorySnapshotDTO;
import com.skeet.consul.provider.modules.project.order.model.export.InventoryChargeExportDTO;
import com.skeet.consul.provider.modules.project.order.service.InventorySnapshotService;
import com.skeet.consul.provider.util.PoiExcelFileUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Desc:
 *
 * @author chengsj
 * @date 2020/4/21 20:47
 */
@Service
public class InventorySnapshotServiceImpl implements InventorySnapshotService {

    private static final ExecutorService EXECUTOR = Executors.newFixedThreadPool(20);

    @Resource
    private InventorySnapshotDao inventorySnapshotDao;

    @Override
    public void exportInventoryChargeDataParallel(InventorySnapshotCriteria criteria) {
        List<Integer> snapshotTimes = criteria.getSnapshotTimes();
        snapshotTimes.forEach(snapshotTime -> EXECUTOR.execute(() -> doExport(criteria, snapshotTime)));

    }

    @Override
    public void exportInventoryChargeDataSerial(InventorySnapshotCriteria criteria) {
        List<Integer> snapshotTimes = criteria.getSnapshotTimes();
        snapshotTimes.forEach(snapshotTime -> doExport(criteria, snapshotTime));

    }

    @Override
    public List<InventorySnapshotDTO> selectBy(InventorySnapshotCriteria criteria) {
        return inventorySnapshotDao.selectBy(criteria);
    }

    private void doExport(InventorySnapshotCriteria criteria, Integer snapshotTime) {
        InventorySnapshotCriteria queryCriteria = InventorySnapshotCriteria.builder()
                .fromSnapshotTime(snapshotTime)
                .toSnapshotTime(snapshotTime)
                .customerIds(criteria.getCustomerIds())
                .build();
        List<InventoryChargeExportDTO> dtos = inventorySnapshotDao.selectChargeData(queryCriteria);
        PoiExcelFileUtil.writeToFileXlsx(dtos, FilePathConstant.DIRECTORY_STORAGE + snapshotTime + "的大客户仓租快照数据.xlsx");
    }
}
