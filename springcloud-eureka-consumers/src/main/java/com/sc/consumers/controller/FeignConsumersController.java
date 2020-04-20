package com.sc.consumers.controller;

import com.sc.api.IProducerService;
import com.sc.service.thirdparty.IThirdPartyService;
import com.sc.service.user.IFeignProducerService;
import com.sc.service.user.dto.UserDTO;
import com.sc.service.user.dto.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/feign")
public class FeignConsumersController {

    @Autowired
    IFeignProducerService iproducerService;

    @Autowired
    IThirdPartyService ithirdPartyService;

    @PostMapping("/login")
    public UserDTO login(@RequestBody UserVO vo) {
        return iproducerService.login(vo);
    }

    @GetMapping("/userList")
    public List<UserDTO> userList() {
        return iproducerService.getUsers();
    }

    @GetMapping("/user/{id}")
    public UserDTO user(@PathVariable("id") Integer id) {
        return iproducerService.getUser(id);
    }

    @GetMapping("/userById")
    public UserDTO userById(@ModelAttribute UserVO vo) {
        return iproducerService.getUserById(vo.getId());
    }

    /**
     * 调用第三方服务接口
     * @return
     */
    @GetMapping("/userInfo")
    public String userInfo() {
        return ithirdPartyService.getInfo();
    }
}
