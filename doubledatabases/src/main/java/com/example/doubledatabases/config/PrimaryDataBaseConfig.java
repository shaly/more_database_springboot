package com.example.doubledatabases.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;
@Configuration
// 前缀为primary.datasource.druid的配置信息
//
@MapperScan(basePackages = "com.example.doubledatabases.dao.primary", sqlSessionFactoryRef = "primarySqlSessionFactory")
public class PrimaryDataBaseConfig {

    @Value("${primary.datasource.druid.filters}")
    private String filters;
    @Value("${primary.datasource.druid.url}")
    private String url;
    @Value("${primary.datasource.druid.username}")
    private String username;
    @Value("${primary.datasource.druid.password}")
    private String password;
    @Value("${primary.datasource.druid.driverClassName}")
    private String driverClassName;
    @Value("${primary.datasource.druid.initialSize}")//
    private int initialSize;
    @Value("${primary.datasource.druid.minIdle}")//
    private int minIdle;
    @Value("${primary.datasource.druid.maxActive}")//
    private int maxActive;
    @Value("${primary.datasource.druid.maxWait}")//
    private long maxWait;
    @Value("${primary.datasource.druid.timeBetweenEvictionRunsMillis}")//
    private long timeBetweenEvictionRunsMillis;
    @Value("${primary.datasource.druid.minEvictableIdleTimeMillis}")//
    private long minEvictableIdleTimeMillis;
    @Value("${primary.datasource.druid.validationQuery}")
    private String validationQuery;
    @Value("${primary.datasource.druid.testWhileIdle}")
    private boolean testWhileIdle;
    @Value("${primary.datasource.druid.testOnBorrow}")
    private boolean testOnBorrow;
    @Value("${primary.datasource.druid.testOnReturn}")
    private boolean testOnReturn;
    @Value("${primary.datasource.druid.poolPreparedStatements}")//
    private boolean poolPreparedStatements;
    @Value("${primary.datasource.druid.maxOpenPreparedStatements}")
    private int maxOpenPreparedStatements;


    // 主数据源使用@Primary注解进行标识
    @Primary
    @ConfigurationProperties(prefix = "primary.datasource.druid")
    @Bean(name = "primaryDataSource")
    public DataSource primaryDataSource() throws SQLException{
        DruidDataSource dataSource=(DruidDataSource) DataSourceBuilder.create().type(com.alibaba.druid.pool.DruidDataSource.class).build();
        dataSource.setInitialSize(initialSize);
        dataSource.setMinIdle(minIdle);
        dataSource.setMaxActive(maxActive);
        dataSource.setMaxWait(maxWait);
        dataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        dataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        dataSource.setPoolPreparedStatements(poolPreparedStatements);
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(maxOpenPreparedStatements);
        dataSource.setFilters(filters);
        return dataSource;

    }

    // 创建该数据源的事务管理
    @Primary
    @Bean(name = "primarySqlSessionFactory")
    public SqlSessionFactory primarySqlSessionFactory(@Qualifier("primaryDataSource") DataSource primaryDataSource) throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(primaryDataSource);//设置数据源bean
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources( "classpath:mappers/primary/*.xml"));//设置mapper文件路径
        return sessionFactory.getObject();
    }
    // 创建Mybatis的连接会话工厂实例
    @Primary
    @Bean(name = "primaryTransactionManager")
    public DataSourceTransactionManager primaryTransactionManager (@Qualifier("primaryDataSource") DataSource primaryDataSource)throws SQLException{
        return new DataSourceTransactionManager(primaryDataSource);
    }

//    @Primary
//    @Bean(name = "primarySqlSessionTemplate")
//    public SqlSessionTemplate primarySqlSessionTemplate(
//            @Qualifier("primarySqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
//        return new SqlSessionTemplate(sqlSessionFactory);
//    }


}
