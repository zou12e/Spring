package com.sc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.annotation.Resource;

public interface IService {

    void save(String name);

    void println(String name);

    void print(String name);


    void autowired(String name);

    void transfer();

    void proxy();
}
