package com.sc.netty.websoket.data;

import lombok.Data;

import java.io.Serializable;

@Data
public class DataContent implements Serializable
{

    private static final long serializableVersionUID = 1231123123123123123L;

    private Integer action;   // 类型
    private ChatMsg chatMsg;  // 聊天内容
    private String extand;    // 扩展字端
}
