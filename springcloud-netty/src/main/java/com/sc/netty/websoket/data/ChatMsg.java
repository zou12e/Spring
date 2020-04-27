package com.sc.netty.websoket.data;

import lombok.Data;

import java.io.Serializable;

@Data
public class ChatMsg implements Serializable
{

    private static final long serializableVersionUID = 1231123123123122423L;

    private Integer senderID;   // 发送者ID
    private Integer receiverID; // 接受者ID
    private String msg;         // 消息
    private Integer msgId;      // 消息ID

}
