package com.sc.config;


import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.DriverManagerDataSource;
import com.sc.service.IService;
import com.sc.service.impl.AutowiredImpl;
import org.apache.commons.dbutils.QueryRunner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * Spring的配置类，相当于bean.xml
 */
@Configuration
@ComponentScan( "com.sc")
@EnableAspectJAutoProxy
@PropertySource("classpath:bean.properties")
/**
 * 1. 开启事务
 */
@EnableTransactionManagement
public class SpringConfiguration {

    @Value("${datasource.url}")
    private String url;
    @Value("${datasource.driver}")
    private String driver;
    @Value("${datasource.username}")
    private String username;
    @Value("${datasource.password}")
    private String password;

    @Bean
    public AutowiredImpl autowired() {
        return new AutowiredImpl();
    }

    @Bean
    public DataSource dataSource() {
        try {
            ComboPooledDataSource ds = new ComboPooledDataSource();
            ds.setDriverClass(driver);
            ds.setJdbcUrl(url);
            ds.setUser(username);
            ds.setPassword(password);
            return ds;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Bean(name = "springDataSource")
    public DataSource springDataSource() {
        try {
            DriverManagerDataSource ds = new DriverManagerDataSource();
            ds.setDriverClass(driver);
            ds.setJdbcUrl(url);
            ds.setUser(username);
            ds.setPassword(password);
            return ds;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Bean
    @Scope("prototype")
    public QueryRunner runner(DataSource dataSource) {
        return new QueryRunner(dataSource);
    }

    @Bean
    @Scope("prototype")
    public JdbcTemplate jdbcTemplate(DataSource springDataSource) {
        return new JdbcTemplate(springDataSource);
    }

    /**
     * 2. 配置事务管理器
     * @return
     */
    @Bean
    public PlatformTransactionManager platformTransactionManager(DriverManagerDataSource springDataSource) {
        return new DataSourceTransactionManager(springDataSource);
    }

}
