package com.skeet.consul.provider.project.order.service;

import com.skeet.consul.provider.project.order.model.domain.InventorySnapshotCriteria;
import com.skeet.consul.provider.project.order.model.domain.InventorySnapshotDTO;

import java.util.List;

/**
 * Desc:
 *
 * @author chengsj
 * @date 2020/4/21 20:46
 */
public interface InventorySnapshotService {

    void exportInventoryChargeDataParallel(InventorySnapshotCriteria criteria);

    void exportInventoryChargeDataSerial(InventorySnapshotCriteria criteria);

    List<InventorySnapshotDTO> selectBy(InventorySnapshotCriteria criteria);
}
