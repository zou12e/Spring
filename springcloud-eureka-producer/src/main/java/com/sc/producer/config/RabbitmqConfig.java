package com.sc.producer.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class RabbitmqConfig {

    public static final String EXCHANGE_TOPICES_INFORM = "exchange_topices_inform";

    public static final String QUEUE_TOPICES_SMS = "queue_topices_sms";
    public static final String QUEUE_TOPICES_EMAIL = "queue_topices_email";

    public static final String ROUTINGKEY_SMS = "inform.#.sms.#";
    public static final String ROUTINGKEY_EMAIL = "inform.#.email.#";

    @Autowired
    CachingConnectionFactory cachingConnectionFactory;

    @Bean
    RabbitTemplate rabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(cachingConnectionFactory);
        /**
         * 监听消息是否发送成功
         */
        rabbitTemplate.setConfirmCallback(((data, ask, cause) -> {
            if (ask) {
                // UUID
                log.info("消息发送成功:" + data.getId());
            } else {
                log.error("消息发送失败：ConfirmCallback");
            }
        }));
        rabbitTemplate.setReturnCallback(((msg, reqCode, reqText, exchange, routingkey) -> {
            log.error("消息发送失败：ReturnCallback");
        }));

        return rabbitTemplate;
    }


    /**
     * 声明交换机
     *
     * @return
     */
    @Bean(EXCHANGE_TOPICES_INFORM)
    public Exchange EXCHANGE_TOPICES_INFORM() {
        // durable(true) 持久化， mq重启还有数据
        return ExchangeBuilder.topicExchange(EXCHANGE_TOPICES_INFORM).durable(true).build();
    }


    /**
     * 声明队列
     */
    @Bean(QUEUE_TOPICES_SMS)
    public Queue QUEUE_TOPICES_SMS() {
        return new Queue(QUEUE_TOPICES_SMS, true);
    }

    /**
     * 声明队列
     */
    @Bean(QUEUE_TOPICES_EMAIL)
    public Queue QUEUE_TOPICES_EMAIL() {
        return new Queue(QUEUE_TOPICES_EMAIL, true);
    }


    /**
     * 绑定交换机和队列
     */
    @Bean
    public Binding BINDING_QUEUE_TOPICES_SMS(@Qualifier(QUEUE_TOPICES_SMS) Queue queue,
                                             @Qualifier(EXCHANGE_TOPICES_INFORM) Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTINGKEY_SMS).noargs();
    }

    /**
     * 绑定交换机和队列
     */
    @Bean
    public Binding BINDING_QUEUE_TOPICES_EMAIL(@Qualifier(QUEUE_TOPICES_EMAIL) Queue queue,
                                               @Qualifier(EXCHANGE_TOPICES_INFORM) Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTINGKEY_EMAIL).noargs();
    }


}
