package com.sc.service.user;

import com.sc.service.user.dto.UserDTO;
import com.sc.service.user.dto.UserVO;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 次类已不使用
 * 可查看FeignProducerServiceFallBackFactory类
 */
@Component
public class FeignProducerServiceFallBack implements FeignProducerService {
    @Override
    public UserDTO login(UserVO vo) {
        UserDTO dot = new UserDTO();
        dot.setName("掉线了");
        return dot;
    }

    @Override
    public List<UserDTO> getUsers() {
        return null;
    }

    @Override
    public UserDTO getUser(Integer id) {
        return null;
    }

    @Override
    public UserDTO getUserById(Integer id) {
        return null;
    }
}
