## Spring Cloud 版本
 

![](https://img.shields.io/badge/Spring%20Boot-2.0.8.RELEASE-brightgreen)
![](https://img.shields.io/badge/Mysql-6.0-blue)
![](https://img.shields.io/badge/JDK-1.8-brightgreen)
![](https://img.shields.io/badge/Maven-3.6.0-blue)
![](https://img.shields.io/badge/Spring%20Cloud-Finchley.SR4-orange) 

***


 
### 架构

 [架构](https://www.processon.com/view/link/5e85a729e4b07b16dcde329f)
 
 [Eureka原理图](https://www.processon.com/view/link/5e8c316ee4b0bf3ebcfdade8)
 
 [Hystrix工作流程](https://www.processon.com/view/link/5e9bc3c95653bb1a686e978c)

***
 

### 项目目录

|  目录                           |      信息      |  其他     | 备注           |  端口  |
|--------------------------------|----------------|----------|----------------|------|
|  config                        |   配置信息      |          |  服务统一配置信息 |      |
|  project-error                 |   错误信息      |          |  开发错误信息     |     |
|  springcloud-config            |   配置中心      |          |  消息总线、RabbitMQ |  8900 |
|  springcloud-eureka            |   注册中心      |          |                |    8761 |
|  springcloud-client-api        |   服务提供者API    |        |  Ribbon + Hystrix调用 |  |
|  springcloud-eureka-consumers  |   服务消费者    |           |  Feign, Hystrix | 8081 |
|  springcloud-eureka-producer   |   服务提供者    |           |                 | 8089 |
|  springcloud-gateway           |   网关         |           | 动态路由、 熔断、限流 | 8900 |
 
 ***
 
 ### 框架、工具
 

 ````
 eureka: 注册中心,提供服务注册和发现, 通过心跳检查维护注册的微服务
 ````
 ````
 spring-boot(consumers，producer): 注册到配置中心的微服务，每个微服务即是消费者，也是提供者
 ````
 ````
 spring-cloud-config: 配置中心，可通过git读取配置文件，并统一管理，可区分环境，版本，即改即生效等功能
 ````
 ````
 spring-cloud-bus: 消息总线，通过webhook发现配置中心内容改变，通知相关微服务，即时更新
 ````
 ````
 feign: 微服务之间的调用，封装了Http调用流程，简化模版代码
 ````
 ````
 hystrix: 熔断器， 微服务之间调用策略，提供服务隔离机制，防止雪崩效应
 ````
 ````
 hystrix-dashboard:  监控单个微服务hystrix的工具，包含请求响应时间, 请求成功率等数据
 ````
 ````
 turbine: 监控整个微服务集群的hystrix的工具
 ````
 ````
 spring-cloud-gateway:  统一网关，包含动态路由、 熔断、限流等功能
 ````
 ````
 skywalking: 提供分布式追踪、服务网格遥测分析、度量聚合和可视化一体化解决方案；
 
 ````
 
  ### 启动流程
  ````
  1. 先启动服务中心 springcloud-eureka
  2. 再启动配置中心 springcloud-config 。
  3. 在启动网关服务 springcloud-gateway
  4. 最后启动 springcloud-eureka-producer服务者 springcloud-eureka-consumers消费者
  
  查看Eureka服务启动情况
  http://localhost:8761/
  
  查看RabbitMQ情况
  http://127.0.0.1:15672
  
  获取配置信息demo
  http://localhost:8800/producer/mode
  
  
  修改配置springcloud-config后 可通过
  curl -X POST http://localhost:8900/actuator/bus-refresh
  刷新配置
  
  feign调用第三方接口示例
  http://localhost:8800/consumers/ifeign/userInfo
  
  
  ribbon + hystrix调用接口示例
  http://localhost:8800/consumers/userList
  
  
  feign调用示例
  http://localhost:8800/consumers/feign/userList
  
  
  封装feign调用示例
  http://localhost:8800/consumers/ifeign/userList
  
  ````
