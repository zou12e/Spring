package com.sc.service.user;

import com.sc.service.user.dto.UserDTO;
import com.sc.service.user.dto.UserVO;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 1. 实现 FallbackFactory
 * 2. 提供FeignClient注解的接口
 * 3. 必须是一个spring bean
 */
@Component
public class FeignProducerServiceClientFallBackFactory implements FallbackFactory<FeignProducerServiceClient> {

    @Override
    public FeignProducerServiceClient create(Throwable throwable) {
        return new FeignProducerServiceClient() {
            @Override
            public UserDTO login(UserVO vo) {
                UserDTO dot = new UserDTO();
                dot.setName("掉线了 FeignProducerServiceFallBackFactory" + throwable);
                return dot;
            }

            @Override
            public List<UserDTO> getUsers() {
                return null;
            }

            @Override
            public UserDTO getUser(Integer id) {
                UserDTO dot = new UserDTO();
                dot.setName("单独配置 FeignProducerServiceClientFallBackFactory" + throwable);
                return dot;
            }

            @Override
            public UserDTO getUserById(Integer id) {
                return null;
            }
        };
    }
}
