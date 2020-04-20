package com.sc.service.user;

import com.sc.constant.IProducerServiceUrl;
import com.sc.service.user.dto.UserDTO;
import com.sc.service.user.dto.UserVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name="PRODUCER")
public interface IFeignProducerService {

   @PostMapping(IProducerServiceUrl.LOGIN)
   UserDTO login(@RequestBody UserVO  vo);

   @GetMapping(IProducerServiceUrl.GETUSERS)
   List<UserDTO> getUsers();

   @GetMapping(IProducerServiceUrl.GETUSER)
   UserDTO getUser(@PathVariable("id") Integer id);

//   @GetMapping(IProducerServiceUrl.GETUSERBYID)
//   UserDTO getUserById(@ModelAttribute UserVO user );

//   @GetMapping(IProducerServiceUrl.GETUSERBYID)
//   UserDTO getUserById(@ModelAttribute UserVO vo);

   /**
    * feign不支持@ModelAttribute的传递方式
    * 需要把
    * @param id
    * @return
    */
   @GetMapping(IProducerServiceUrl.GETUSERBYID)
   UserDTO getUserById(@RequestParam("id")  Integer id);
}
