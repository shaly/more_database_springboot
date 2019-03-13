package com.example.doubledatabases.config;


import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
// 前缀为primary.datasource.druid的配置信息
@ConfigurationProperties(prefix = "primary2.datasource.druid")
@MapperScan(basePackages = Primary2DataBaseConfig.PACKAGE, sqlSessionFactoryRef = "primary2SqlSessionFactory")
public class Primary2DataBaseConfig {
    /**
     * dao层的包路径
     */
    static final String PACKAGE = "com.example.doubledatabases.dao.primary2";

    /**
     * mapper文件的相对路径
     */
    private static final String MAPPER_LOCATION = "classpath:mappers/primary2/*.xml";

    @Value("${primary2.datasource.druid.filters}")
    private String filters;
    @Value("${primary2.datasource.druid.url}")
    private String url;
    @Value("${primary2.datasource.druid.username}")
    private String username;
    @Value("${primary2.datasource.druid.password}")
    private String password;
    @Value("${primary2.datasource.druid.driverClassName}")
    private String driverClassName;
    @Value("${primary2.datasource.druid.initialSize}")//
    private int initialSize;
    @Value("${primary2.datasource.druid.minIdle}")//
    private int minIdle;
    @Value("${primary2.datasource.druid.maxActive}")//
    private int maxActive;
    @Value("${primary2.datasource.druid.maxWait}")//
    private long maxWait;
    @Value("${primary2.datasource.druid.timeBetweenEvictionRunsMillis}")//
    private long timeBetweenEvictionRunsMillis;
    @Value("${primary2.datasource.druid.minEvictableIdleTimeMillis}")//
    private long minEvictableIdleTimeMillis;
    @Value("${primary2.datasource.druid.validationQuery}")
    private String validationQuery;
    @Value("${primary2.datasource.druid.testWhileIdle}")
    private boolean testWhileIdle;
    @Value("${primary2.datasource.druid.testOnBorrow}")
    private boolean testOnBorrow;
    @Value("${primary2.datasource.druid.testOnReturn}")
    private boolean testOnReturn;
    @Value("${primary2.datasource.druid.poolPreparedStatements}")//
    private boolean poolPreparedStatements;
    @Value("${primary2.datasource.druid.maxOpenPreparedStatements}")
    private int maxOpenPreparedStatements;


    @Bean(name = "primary2DataSource")
    public DataSource primary2DataSource() throws SQLException {
        DruidDataSource druid = new DruidDataSource();
        //监控同级拦截的filters
        druid.setFilters(filters);
        // 配置基本属性
        druid.setDriverClassName(driverClassName);
        druid.setUsername(username);
        druid.setPassword(password);
        druid.setUrl(url);

        //初始化时建立物理连接的个数
        druid.setInitialSize(initialSize);
        //最大连接池数量
        druid.setMaxActive(maxActive);
        //最小连接池数量
        druid.setMinIdle(minIdle);
        //获取连接时最大等待时间，单位毫秒。
        druid.setMaxWait(maxWait);
        //间隔多久进行一次检测，检测需要关闭的空闲连接
        druid.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        //一个连接在池中最小生存的时间
        druid.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        //用来检测连接是否有效的sql
        druid.setValidationQuery(validationQuery);
        //建议配置为true，不影响性能，并且保证安全性。
        druid.setTestWhileIdle(testWhileIdle);
        //申请连接时执行validationQuery检测连接是否有效
        druid.setTestOnBorrow(testOnBorrow);
        druid.setTestOnReturn(testOnReturn);
        //是否缓存preparedStatement，也就是PSCache，oracle设为true，mysql设为false。分库分表较多推荐设置为false
        druid.setPoolPreparedStatements(poolPreparedStatements);
        // 打开PSCache时，指定每个连接上PSCache的大小
        // druid.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);

        return druid;

    }

    @Bean(name = "primary2TransactionManager")
    public DataSourceTransactionManager primary2TransactionManager() throws SQLException {
        return new DataSourceTransactionManager(primary2DataSource());
    }

    @Bean(name = "primary2SqlSessionFactory")
    public SqlSessionFactory primary2SqlSessionFactory(@Qualifier("primary2DataSource") DataSource primary2DataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(primary2DataSource);//设置数据源bean
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(Primary2DataBaseConfig.MAPPER_LOCATION));//设置mapper文件路径
        return sessionFactory.getObject();
    }
}
