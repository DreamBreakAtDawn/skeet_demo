package com.skeet.rabbitmq.constant;

import static com.skeet.rabbitmq.constant.QueueConfigConstant.PREFIX_PRIMARY_TENDER;

/**
 * @Description
 * @Author chengsj
 * @Date 2021/5/31 11:18
 */
public class PrimaryTenderQueueConstant {

    public static final String QUEUE_BOND_MIGRATE = PREFIX_PRIMARY_TENDER + "bond-migrate";
    public static final String QUEUE_BOND_QUARANTINE = PREFIX_PRIMARY_TENDER + "bond-quarantine";
    public static final String QUEUE_BOND_STATUS_CHANGE = PREFIX_PRIMARY_TENDER + "dt-bond-status-change";
    public static final String QUEUE_BOND_HISTORY_REMOVE = PREFIX_PRIMARY_TENDER + "dt-bond-history-remove";
    public static final String QUEUE_BOND_UPDATE = PREFIX_PRIMARY_TENDER + "bond-update";
}
