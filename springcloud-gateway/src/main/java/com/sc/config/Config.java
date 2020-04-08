package com.sc.config;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


@Configuration
public class Config {

    /**
     * 用户限流
     *     获取请求用户id作为限流key。
     * @return
     */
//    @Bean
    KeyResolver userKeyResolver() {
        return exchange -> Mono.just(exchange.getRequest().getQueryParams().getFirst("user"));
    }

    /**
     * IP限流
     *     获取请求用户ip作为限流key。
     * @return
     */
//    @Bean
    public KeyResolver ipKeyResolver() {
        return exchange -> Mono.just(exchange.getRequest().getRemoteAddress().getHostName());
    }

    /**
     * 接口限流
     *     获取请求地址的uri作为限流key。
     * @return
     */
//    @Bean
    public KeyResolver apiKeyResolver() {
       return exchange -> Mono.just(exchange.getRequest().getPath().value());
    }



}