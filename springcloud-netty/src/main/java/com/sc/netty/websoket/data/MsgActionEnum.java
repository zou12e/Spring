package com.sc.netty.websoket.data;

public enum MsgActionEnum {

    CONNECT(1, "第一次连接"),
    CHAT(2, "消息"),
    SIGNED(3, "消息签收"),
    KEEPALIVE(4, "心跳");

    public final Integer type;
    public final String content;

    MsgActionEnum(Integer type, String content) {
        this.type = type;
        this.content = content;
    }
}
