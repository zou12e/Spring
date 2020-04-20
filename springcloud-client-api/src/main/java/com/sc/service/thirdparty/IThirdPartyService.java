package com.sc.service.thirdparty;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="mock-api", url = "http://mock-api.com/NnXRADzy.mock/api/v1")
public interface IThirdPartyService {

    @GetMapping("/user/info")
    String getInfo();

}
