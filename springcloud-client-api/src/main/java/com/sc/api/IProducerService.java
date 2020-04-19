package com.sc.api;

import com.sc.service.user.dto.UserDTO;
import com.sc.service.user.dto.UserVO;
import java.util.List;

public interface IProducerService {

    List<UserDTO> getUsers();

    UserDTO getUser(Integer id);

    UserDTO getUserById(UserVO vo);

    UserDTO login(UserVO vo);
}
