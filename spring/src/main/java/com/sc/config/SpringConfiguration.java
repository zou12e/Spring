package com.sc.config;


import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.sc.service.IService;
import com.sc.service.impl.AutowiredImpl;
import org.apache.commons.dbutils.QueryRunner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

import javax.sql.DataSource;

@Configuration
@ComponentScan( "com.sc")
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

    @Bean
    @Scope("prototype")
    public QueryRunner runner(DataSource dataSource) {
        return new QueryRunner(dataSource);
    }

}
