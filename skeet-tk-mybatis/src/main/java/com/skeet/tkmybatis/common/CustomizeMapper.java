package com.skeet.tkmybatis.common;

import org.apache.ibatis.annotations.UpdateProvider;
import tk.mybatis.mapper.annotation.RegisterMapper;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @Description
 * @Author chengsj
 * @Date 2021/3/11 10:43
 */
@RegisterMapper
public interface CustomizeMapper<T> extends Mapper<T> {

    /**
     * 根据主键批量更新，只更新非空属性
     *
     * @param record
     * @return
     *
     */
    @UpdateProvider(type = BatchUpdateProvider.class, method = "dynamicSQL")
    int batchUpdateByPrimaryKeySelective(List<T> record);
}
