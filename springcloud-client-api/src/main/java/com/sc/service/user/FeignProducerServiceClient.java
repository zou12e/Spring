package com.sc.service.user;


import com.sc.api.IFeignProducerServiceClient;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name="PRODUCER",
        fallbackFactory = FeignProducerServiceClientFallBackFactory.class)
public interface FeignProducerServiceClient extends IFeignProducerServiceClient {

}
