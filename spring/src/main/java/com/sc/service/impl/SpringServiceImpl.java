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
