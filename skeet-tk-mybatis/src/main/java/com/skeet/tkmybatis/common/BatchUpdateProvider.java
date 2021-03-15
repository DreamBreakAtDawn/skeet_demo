package com.skeet.tkmybatis.common;

import org.apache.ibatis.mapping.MappedStatement;
import tk.mybatis.mapper.mapperhelper.MapperHelper;
import tk.mybatis.mapper.mapperhelper.MapperTemplate;
import tk.mybatis.mapper.mapperhelper.SqlHelper;

/**
 * @Description
 * @Author chengsj
 * @Date 2021/3/11 10:44
 */
public class BatchUpdateProvider extends MapperTemplate {

    public BatchUpdateProvider(Class<?> mapperClass, MapperHelper mapperHelper) {
        super(mapperClass, mapperHelper);
    }

    /**
     * 通过主键更新不为null的字段
     */
    public String batchUpdateByPrimaryKeySelective(MappedStatement ms) {
        Class<?> entityClass = getEntityClass(ms);
        StringBuilder sql = new StringBuilder();
        // 拼接foreach标签
        sql.append("<foreach collection=\"list\" item=\"record\" separator=\";\" >");
        // 拼接表
        sql.append(SqlHelper.updateTable(entityClass, super.tableName(entityClass)));
        // 拼接set标签
        sql.append(SqlHelper.updateSetColumns(entityClass, "record", true, isNotEmpty()));
        // 拼接where标签
        sql.append(SqlHelper.wherePKColumns(entityClass, "record", true));
        sql.append("</foreach>");
        return sql.toString();
    }
}
