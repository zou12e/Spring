package com.sc.producer.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;


@RestController
/**
 * 自动刷新配置
 */
@RefreshScope
public class ConfigController {
    @Value("${mode}")
    private String mode;

    @RequestMapping("/mode")
    public String from() {
        return "config：" + this.mode;
    }

}
