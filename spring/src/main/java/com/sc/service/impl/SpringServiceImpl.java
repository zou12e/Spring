package com.sc.service.impl;

import com.sc.domain.Account;
import com.sc.service.IService;
import com.sc.service.ISpringService;
import com.sc.utils.TransactionManager;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Data
@NoArgsConstructor
@Service
/**
 * 3. 配置事务通知
 transactionManager： 事务管理器
 propagation：用于指定事务的传播行为。默认值REQUIRED，表示一定有事务，查询使用SUPPORTS
 isolation： 指定事务的隔离级别，默认值DEFAULT，表示使用数据库的默认隔离级别
 timeout：用于指定事务超时时间，默认值-1，用不超时，单位秒
 readOnly：用于指定事务是否只读。默认值false，表示读写。查询使用true，表示只读
 rollbackFor：用于指定一个异常，发生该异常时，事务回滚；默认事务都混滚
 noRollbackFor：用于指定一个异常，发生该异常时，事务不回滚；默认事务都混滚
 */
@Transactional(transactionManager = "platformTransactionManager")
public class SpringServiceImpl implements ISpringService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void transfer() {
        jdbcTemplate.update("update account set money = 100 where id = 1");
        int j = 10 / 0;
        jdbcTemplate.update("update account set money = 200 where id = 2");
    }
}
