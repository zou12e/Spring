package com.sc.utils;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * 连接工具类，用于从数据源中获取一个连接，并实现和线程的绑定
 */
@Component
public class ConnectionUtil {

    private ThreadLocal<Connection> tl = new ThreadLocal<Connection>();

    @Autowired
    private DataSource dataSource;

    /**
     * 获取当前线程上的连接
     *
     * @return
     */
    public Connection getThreadConnection() {
        try {
            // 1. 先从ThreadLocal获取连接
            Connection conn = tl.get();
            // 2. 判断是否有连接
            if (null == conn) {
                // 3. 从数据源中获取连接，并存入ThreadLocal中
                conn = dataSource.getConnection();
                tl.set(conn);
            }
            // 4. 返回当前线程的连接
            return conn;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    /**
     * 连接和线程解绑
     */
    public void remove() {
        tl.remove();
    }
}
