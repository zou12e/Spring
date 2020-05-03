package com.sc.utils;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

/**
 *  事务相关工具类
 *  1. 开启事务
 *  2. 提交事务
 *  3. 回滚事务
 *  4. 释放连接
 */
@Component
public class TransactionManager {

    @Autowired
    private  ConnectionUtil connectionUtil;
    /**
     * 开启事务
     */
    public void beginTransaction() {
        try {
            connectionUtil.getThreadConnection().setAutoCommit(false);
            System.out.println("1. 开启事务");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 提交事务
     */
    public void commit() {
        try {
            connectionUtil.getThreadConnection().commit();
            System.out.println("3. 提交事务");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 回滚事务
     */
    public void rollback() {
        try {
            connectionUtil.getThreadConnection().rollback();
            System.out.println("5. 回滚事务");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 释放连接
     */
    public void release() {
        try {
            // 还回线程池中
            connectionUtil.getThreadConnection().close();
            connectionUtil.remove();
            System.out.println("6. 释放连接");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
