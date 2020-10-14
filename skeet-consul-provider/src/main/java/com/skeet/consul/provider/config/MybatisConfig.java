package com.skeet.consul.provider.config;

import org.springframework.context.annotation.Configuration;


@Configuration
public class MybatisConfig {

//    @Bean
//    @ConfigurationProperties(prefix = "spring.datasource")
//    @Primary
//    public DataSource dataSource(){
//        return DataSourceBuilder.create().type(DruidDataSource.class).build();
//    }
//
//    @Bean
//    @Primary
//    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
//        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
//        bean.setDataSource(dataSource);
//        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:META-INF/mybatis/mapper/**/*Mapper.xml"));
//        return bean.getObject();
//    }
//
//    @Bean
//    @Primary
//    public DataSourceTransactionManager transactionManager(DataSource dataSource) {
//        return new DataSourceTransactionManager(dataSource);
//    }
//
//    @Bean
//    @Primary
//    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) throws Exception {
//        sqlSessionFactory.getConfiguration().setMapUnderscoreToCamelCase(true);
//        return new SqlSessionTemplate(sqlSessionFactory);
//    }
}
