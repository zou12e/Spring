package com.sc.netty.websoket.data;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class WSChatMsg implements Serializable
{

    private static final long serializableVersionUID = 1231123123123122423L;

    private Integer senderID;   // 发送者ID
    private Integer receiverID; // 接受者ID
    private String senderNickname;    // 发送者昵称
    private String receiverNickname;    // 接受者昵称
    private String msg;         // 消息
    private Integer msgId;      // 消息ID
    private Date now;           // 发送时间

}
