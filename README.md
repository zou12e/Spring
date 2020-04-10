## Spring Cloud 版本
 

![](https://img.shields.io/badge/Spring%20Boot-2.0.8.RELEASE-brightgreen)
![](https://img.shields.io/badge/Mysql-6.0-blue)
![](https://img.shields.io/badge/JDK-1.8-brightgreen)
![](https://img.shields.io/badge/Maven-3.6.0-blue)
![](https://img.shields.io/badge/Spring%20Cloud-Finchley.SR4-orange) 

***


 
### 架构

 [架构](https://www.processon.com/view/link/5e85a729e4b07b16dcde329f)
 
***
 

### 项目目录

|  目录                           |      信息      |  其他     | 备注           |
|--------------------------------|----------------|----------|----------------|
|  config                        |   配置信息      |          |  服务统一配置信息 |
|  project-error                 |   错误信息      |          |  开发错误信息    |
|  springcloud-config            |   配置中心      |          |  消息总线、RabbitMQ |
|  springcloud-eureka            |   注册中心      |          |                |
|  springcloud-eureka-consumers  |   服务消费者    |           |  Feign, Hystrix |
|  springcloud-eureka-producer   |   服务提供者    |           |                 |
|  springcloud-gateway           |   网关         |           | 动态路由、 熔断、限流 |
 
 ***
 
 ### 框架、工具
 ````
 eureka: 配置中心，注册中心
 consumers，producer: 注册到配置中心的微服务，即是消费者，也是提供者
 spring-cloud-config: 配置中心，通用配置
 spring-cloud-bus: 消息总线，当配置中心内容改变，通知相关微服务
 feign: 微服务之间的调用
 hystrix: 熔断器， 微服务之间调用失败策略
 hystrix-dashboard:  监控单个微服务hystrix的工具，包含请求响应时间, 请求成功率等数据
 turbine: 监控整个微服务集群的hystrix的工具
 spring-cloud-gateway:  统一网关
 skywalking: 提供分布式追踪、服务网格遥测分析、度量聚合和可视化一体化解决方案；
 
 ````
 
