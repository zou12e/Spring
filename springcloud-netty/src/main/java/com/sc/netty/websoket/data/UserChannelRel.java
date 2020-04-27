package com.sc.netty.websoket.data;

import io.netty.channel.Channel;
import java.util.HashMap;
import java.util.Map;

public class UserChannelRel {

    public static Map<Integer, Channel> MAP = new HashMap<>();

    public static void put(Integer key, Channel value) {
        MAP.put(key, value);
    }

    public static Channel get(Integer key) {
       return MAP.get(key);
    }
}
