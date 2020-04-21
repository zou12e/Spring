package com.sc.producer;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sc.producer.entity.User;
import com.sc.producer.mappers.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringcloudEurekaProducerApplicationTests {

    @Autowired
    UserMapper userMapper;

    @Test
    public void contextLoads() {
        System.out.println("abc");
    }


    @Test
    public void getList() {
        List<User> list = userMapper.selectList(null);
        list.forEach(System.out::println);
    }

    @Test
    public void insert() {
        User user = new User();
        user.setName("test");
        userMapper.insert(user);
        getList();
    }

    @Test
    public void selectPage() {
        Page<User> page = new Page<>(1, 2);
        userMapper.selectPage(page, null);
        page.getRecords().forEach(System.out::println);
    }

    @Test
    public void delete() {
        userMapper.deleteById(1);
        System.out.println( userMapper.selectById(1));

    }
}
