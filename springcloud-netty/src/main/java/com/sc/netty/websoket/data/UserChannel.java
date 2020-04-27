package com.sc.netty.websoket.data;

import com.sc.netty.entity.User;
import io.netty.channel.Channel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserChannel {
    private User user;
    private Channel channel;
}
