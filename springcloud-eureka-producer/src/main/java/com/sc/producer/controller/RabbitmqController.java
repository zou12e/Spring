package com.sc.producer.controller;

import com.sc.producer.config.RabbitmqConfig;
import com.sc.producer.dto.EntityCopyMapper;
import com.sc.producer.entity.User;
import com.sc.service.user.dto.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@Slf4j
@RequestMapping("/mq")
public class RabbitmqController {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @PostMapping("/send")
    public String send(@RequestBody UserVO vo) {

        // 交换机
        // 通配符工作模式(Topic)  > 路由工作模式(Direct) > 发布订阅模式(Fanout) > 工作队列模式（Default Exchange）
        //                         (HEADERS)工作模式

        // 1. 生产者
        /**
         * 1. 交换机名称
         * 2. routingKey
         * 3. 消息类容
         */
        String routingKey = "inform.email.sms";
        User user = EntityCopyMapper.INSTANCE.toUser(vo);
        rabbitTemplate.convertAndSend(RabbitmqConfig.EXCHANGE_TOPICES_INFORM, routingKey, user, new CorrelationData(UUID.randomUUID().toString()));
        return "ok";
    }
}
