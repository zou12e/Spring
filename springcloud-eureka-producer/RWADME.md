### Service Provider
````
    每个微服务即是
    服务提供方
    将自身服务注册到Eureka，从而使服务消费方能够找到

    @EnableDiscoveryClient来进行服务的注册

````

### Service Consumer
````
    也是
    服务消费方
    从Eureka获取注册服务列表，从而能够消费服务
    
    @EnableFeignClients来进行服务消费
    
    熔断器: Hystrix

    Hystrix Dashboard: Hystrix进行实时监控的工具
    
````

