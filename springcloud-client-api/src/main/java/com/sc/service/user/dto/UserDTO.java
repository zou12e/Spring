package com.sc.service.user.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class UserDTO {

    private int id;

    private String name;

    private int age;

    private String email;

    private String password;

    private Timestamp createTime;

    private Timestamp updateTime;
}
