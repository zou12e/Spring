package com.sc.producer.controller;

import com.sc.api.IProducerService;
import com.sc.constant.IProducerServiceUrl;
import com.sc.producer.dto.EntityCopyMapper;
import com.sc.producer.mapper.UserMapper;
import com.sc.producer.model.User;
import com.sc.service.user.dto.UserDTO;
import com.sc.service.user.dto.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
