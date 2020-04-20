package com.sc.api;

import com.sc.constant.IFeignProducerServiceUrl;
import com.sc.service.user.dto.UserDTO;
import com.sc.service.user.dto.UserVO;
import org.springframework.web.bind.annotation.*;
import java.util.List;


public interface IFeignProducerServiceClient {

    @PostMapping(IFeignProducerServiceUrl.LOGIN)
    UserDTO login(@RequestBody UserVO  vo);

    @GetMapping(IFeignProducerServiceUrl.GETUSERS)
    List<UserDTO> getUsers();

    @GetMapping(IFeignProducerServiceUrl.GETUSER)
    UserDTO getUser(@PathVariable("id") Integer id);

    @GetMapping(IFeignProducerServiceUrl.GETUSERBYID)
    UserDTO getUserById(@RequestParam("id")  Integer id);
}
