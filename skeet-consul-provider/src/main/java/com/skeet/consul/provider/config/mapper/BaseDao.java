/*
 * Copyright (c) 2017 4PX Information Technology Co.,Ltd. All rights reserved.
 */
package com.skeet.consul.provider.config.mapper;

import java.util.List;

/**
 * 数据访问层基础支撑接口, 提供一些常用的数据层操作接口。
 *
 * @author linxiaomin
 * @date 2017年04月20日
 */
public interface BaseDao<T> {
    /**
     * 根据实体中的属性值进行查询，查询条件使用等号
     *
     * @param record 需要查询的对象模板
     * @return 查询结果列表，如果查询不到返回长度为0的List对象
     */
    List<T> select(T record);

//    /**
//     * 根据实体中的属性进行查询，只能有一个返回值，有多个结果是抛出异常，查询条件使用等号
//     *
//     * @param record 需要查询的对象模板
//     * @return 查询结果，如果查询不到返回null
//     */
//    T selectOne(T record);
//
//    /**
//     * 根据实体中的属性查询总数，查询条件使用等号
//     *
//     * @param record 需要查询的对象模板
//     * @return 查询命中的条数
//     */
//    int selectCount(T record);
//
//    /**
//     * 保存一个实体，null的属性也会保存，不会使用数据库默认值
//     *
//     * @param record 需要插入的对象
//     * @return 影响的数据条数
//     */
//    int insert(T record);

    /**
     * 保存一个实体，null的属性不会保存，会使用数据库默认值
     *
     * @param record 需要插入的对象
     * @return 影响的数据条数
     */
    int insertSelective(T record);

//    /**
//     * 批量插入，支持批量插入的数据库可以使用，例如MySQL,H2等，另外该接口限制实体包含id属性并且必须为自增列
//     *
//     * @param recordList 需要插入的实体列表
//     * @return 插入的数据条数
//     */
//    int insertList(List<T> recordList);

    /**
     * 批量插入实体列表，实体中null的属性不会保存，
     * 支持批量插入的数据库可以使用，例如MySQL,H2等，另外该接口限制实体包含id属性并且必须为自增列。
     *
     * @param recordList 需要插入的实体列表
     * @return 插入的条数
     */
    int insertSelectiveList(List<T> recordList);

//    /**
//     * 根据实体属性作为条件进行删除，查询条件使用等号
//     *
//     * @param record 需要删除的实体
//     * @return 影响的数据条数
//     */
//    int delete(T record);
}
