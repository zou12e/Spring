package com.sc.netty.websoket.data;

import com.sc.netty.entity.User;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
public class DataContent implements Serializable
{

    private static final long serializableVersionUID = 1231123123123123123L;

    private Integer action;   // 类型
    private WSChatMsg chatMsg;  // 聊天内容
    private String extand;    // 扩展字端
    private List<User> onlineList;
}
