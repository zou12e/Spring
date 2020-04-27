package com.sc.netty.websoket.data;

import io.netty.channel.Channel;
import java.util.HashMap;
import java.util.Map;

public class UserChannelRel {

    public static Map<Integer, UserChannel> MAP = new HashMap<>();

    public static void put(Integer key, UserChannel value) {
        MAP.put(key, value);
    }

    public static UserChannel get(Integer key) {
       return MAP.get(key);
    }

    public static UserChannel remove(Integer key) {
        return MAP.remove(key);
    }
}
