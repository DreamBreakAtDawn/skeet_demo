package com.skeet.h2.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * Created with IDEA
 *
 * @description: 多数据源配置
 * @author: xubo
 * @create: 2018-10-10 14:26
 */
@Configuration
public class MultiDatasourceConfig {

    @Bean("skeetDataSource")
    @Qualifier("skeetDataSource")
    @Primary // 定义主数据源
    @ConfigurationProperties(prefix = "skeet.datasource")
    public DataSource primaryDataSource() {
        return DataSourceBuilder.create().build();
    }

}
