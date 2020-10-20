/*
 * Copyright (c) 2017 4PX Information Technology Co.,Ltd. All rights reserved.
 */
package com.skeet.consul.provider.config.mapper;

import java.util.List;

/**
 * 数据访问层基础支撑抽象类。实现了一些公用的数据操作接口，自定义的 DAO 可以继承此抽象类获得
 * 基础操作数据的能力。
 *
 * @author linxiaomin
 * @date 2017年04月20日
 */
public abstract class BaseDaoImpl<T> implements BaseDao<T> {

    /**
     * 获取自定义DAO中注入的mapper, 自定义DAO必须实现此方法来获取。
     *
     * @return 自定义DAO中注入的 mapper
     */
    protected abstract BaseMapper<T> getBaseMapper();

    @Override
    public List<T> select(T record) {
        return null;
//        return getBaseMapper().select(record);
    }

    //
//    @Override
//    public T selectOne(T record) {
//        return getBaseMapper().selectOne(record);
//    }
//
//    @Override
//    public int selectCount(T record) {
//        return getBaseMapper().selectCount(record);
//    }
//
//    @Override
//    public int insert(T record) {
//        return getBaseMapper().insert(record);
//    }
//
    @Override
    public int insertSelective(T record) {
        return 0;
//        return getBaseMapper().insertSelective(record);
    }

    //
    @Override
    public int insertSelectiveList(List<T> recordList) {
        return 0;
//        return getBaseMapper().insertSelectiveList(recordList);
    }
//
//    @Override
//    public int insertList(List<T> recordList) {
//        return getBaseMapper().insertList(recordList);
//    }
//
//    @Override
//    public int delete(T record) {
//        return getBaseMapper().delete(record);
//    }

}
