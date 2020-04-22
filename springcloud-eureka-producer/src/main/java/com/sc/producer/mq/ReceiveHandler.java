package com.sc.producer.mq;

import com.sc.producer.config.RabbitmqConfig;
import com.sc.producer.entity.User;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import com.rabbitmq.client.Channel;

@Component
public class ReceiveHandler {

    @RabbitListener(queues = RabbitmqConfig.QUEUE_TOPICES_SMS)
    public void sendSms(User user, Message message, Channel channel) {
        System.out.println("sendSms: " +  user.toString());
    }

    @RabbitListener(queues = RabbitmqConfig.QUEUE_TOPICES_SMS)
    public void sendSms2(User user, Message message, Channel channel) {
        System.out.println("sendSms2: " +  user.toString());
    }

    @RabbitListener(queues = RabbitmqConfig.QUEUE_TOPICES_EMAIL)
    public void sendEmail(User user, Message message, Channel channel) {
        System.out.println("sendEmail: " +  user.toString());

    }
}
