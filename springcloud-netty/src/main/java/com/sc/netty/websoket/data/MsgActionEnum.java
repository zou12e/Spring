package com.sc.netty.websoket.data;

public enum MsgActionEnum {

    CONNECT(1, "上线"),
    CHAT(2, "发送消息"),
    SIGNED(3, "消息签收"),
    KEEPALIVE(4, "心跳"),
    OFFLINE(5, "下线"),
    LINELIST(6, "在线列表");

    public final Integer type;
    public final String content;

    MsgActionEnum(Integer type, String content) {
        this.type = type;
        this.content = content;
    }
}
