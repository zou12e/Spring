package com.sc.service;

import com.sc.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;


public interface IService {

    void save(String name);

    void println(String name);

    void print(String name);

    void autowired(String name);

    void transfer();

    List<Account> proxy();
}
