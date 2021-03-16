package com.skeet.h2.config;

import com.github.springtestdbunit.bean.DatabaseConfigBean;
import com.github.springtestdbunit.bean.DatabaseDataSourceConnectionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * Desc:
 *
 * @author chengsj
 * @date 2020/10/29 15:50
 */
@Configuration
public class DbUnitConfig {

//    @Resource(name = "h2DataSource")
//    private DataSource dataSource;

    @Bean
    public DatabaseConfigBean dbUnitDatabaseConfig() {
        DatabaseConfigBean configBean = new DatabaseConfigBean();
        configBean.setAllowEmptyFields(true);
        return configBean;
    }

//    @Bean("h2DataSource")
//    @Qualifier("h2DataSource")
//    @ConfigurationProperties(prefix = "h2.datasource")
//    public DataSource dataSource() {
//        return DataSourceBuilder.create().build();
//    }

    /**
     * DatabaseDataSourceConnectionFactoryBean的名称必须是dbUnitDatabaseConnection
     * 否则它不会被dbunit框架使用到
     */
    @Bean
    public DatabaseDataSourceConnectionFactoryBean dbUnitDatabaseConnection(
            DatabaseConfigBean databaseConfig,
            DataSource dataSource) {
        DatabaseDataSourceConnectionFactoryBean factoryBean = new DatabaseDataSourceConnectionFactoryBean(dataSource);
        factoryBean.setDatabaseConfig(databaseConfig);
        return factoryBean;
    }
}
