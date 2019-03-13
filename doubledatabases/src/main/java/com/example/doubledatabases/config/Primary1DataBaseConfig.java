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
@ConfigurationProperties(prefix = "primary1.datasource.druid")
@MapperScan(basePackages = Primary1DataBaseConfig.PACKAGE, sqlSessionFactoryRef = "primary1SqlSessionFactory")
public class Primary1DataBaseConfig {
    /**
     * dao层的包路径
     */
    static final String PACKAGE = "com.example.doubledatabases.dao.primary1";

    /**
     * mapper文件的相对路径
     */
    private static final String MAPPER_LOCATION = "classpath:mappers/primary1/*.xml";

    @Value("${primary1.datasource.druid.filters}")
    private String filters;
    @Value("${primary1.datasource.druid.url}")
    private String url;
    @Value("${primary1.datasource.druid.username}")
    private String username;
    @Value("${primary1.datasource.druid.password}")
    private String password;
    @Value("${primary1.datasource.druid.driverClassName}")
    private String driverClassName;
    @Value("${primary1.datasource.druid.initialSize}")//
    private int initialSize;
    @Value("${primary1.datasource.druid.minIdle}")//
    private int minIdle;
    @Value("${primary1.datasource.druid.maxActive}")//
    private int maxActive;
    @Value("${primary1.datasource.druid.maxWait}")//
    private long maxWait;
    @Value("${primary1.datasource.druid.timeBetweenEvictionRunsMillis}")//
    private long timeBetweenEvictionRunsMillis;
    @Value("${primary1.datasource.druid.minEvictableIdleTimeMillis}")//
    private long minEvictableIdleTimeMillis;
    @Value("${primary1.datasource.druid.validationQuery}")
    private String validationQuery;
    @Value("${primary1.datasource.druid.testWhileIdle}")
    private boolean testWhileIdle;
    @Value("${primary1.datasource.druid.testOnBorrow}")
    private boolean testOnBorrow;
    @Value("${primary1.datasource.druid.testOnReturn}")
    private boolean testOnReturn;
    @Value("${primary1.datasource.druid.poolPreparedStatements}")//
    private boolean poolPreparedStatements;
    @Value("${primary1.datasource.druid.maxOpenPreparedStatements}")
    private int maxOpenPreparedStatements;


    @Bean(name = "primary1DataSource")
    public DataSource primary1DataSource() throws SQLException {
        DruidDataSource druid=new DruidDataSource();
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

    @Bean(name = "primary1TransactionManager")
    public DataSourceTransactionManager primary1TransactionManager ()throws SQLException{
        return new DataSourceTransactionManager(primary1DataSource());
    }

    @Bean(name = "primary1SqlSessionFactory")
    public SqlSessionFactory primary1SqlSessionFactory(@Qualifier("primary1DataSource") DataSource primary1DataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(primary1DataSource);//设置数据源bean
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(Primary1DataBaseConfig.MAPPER_LOCATION));//设置mapper文件路径
        return sessionFactory.getObject();
    }



}