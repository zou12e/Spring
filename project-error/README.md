### 搭建框架是出现的一些错误，记录一下

````
spring-boot-starter-web
spring-boot-starter-gateway
两个包之间会冲突

````

````
项目的打包类型：pom、jar、war
packaging 默认是jar类型，
pom  父类型都为pom类型
jar 内部调用或者是作服务使用
war 需要部署的项目
 
<packaging>pom</packaging> 找不到配置文件
````


````
SpringBoot对应SpringCloud版本号
https://start.spring.io/actuator/info
[spring-cloud]内容对照

Spring IO Platform与SpringBoot版本对应关系
https://blog.csdn.net/nnsword/article/details/86979647
https://spring.io/projects/platform#learn
````

````
Exception in thread "main" java.lang.NoClassDefFoundError: org/springframework/util/unit/DataSize
maven compile 
pom版本问题
````

````
ERROR 27915 --- [0.0-8081-exec-1] o.a.c.c.C.[.[.[/].[dispatcherServlet]    : 
Servlet.service() for servlet [dispatcherServlet] in context with path [] threw exception [Request processing failed; nested exception is feign.RetryableException: Operation timed out (Connection timed out) executing GET http://spring-cloud-producer/hello?name=dfdsf] with root cause
新项目出现问题，先查看服务端口是否被占用
localhost:8080 访问没有问题
192.168.1.101:8080 访问有问题， 就改项目端口
````


````
Cannot load driver class: com.mysql.cj.jdbc.Driver
检查数据库版本
mysql 
select version()

com.mysql.jdbc.Driver是mysql-connector-java 5中的，而com.mysql.cj.jdbc.Driver是mysql-connector-java 6中的。

1、JDBC连接Mysql5需用com.mysql.jdbc.Driver，例如：
driverClassName=com.mysql.jdbc.Driver
url=jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf8&useSSL=false
username=root
password=root

2、JDBC连接Mysql6需用com.mysql.cj.jdbc.Driver，同时需要指定时区serverTimezone，例如：
driverClassName=com.mysql.cj.jdbc.Driver
url=jdbc:mysql://localhost:3306/test?serverTimezone=UTC&?useUnicode=true&characterEncoding=utf8&useSSL=false
username=root
password=root

3、设定时区时，serverTimezone=UTC比中国时间早8个小时，若在中国，可设置serverTimezone=Asia/Shanghai或者serverTimezone=Hongkong，例如：
driverClassName=com.mysql.cj.jdbc.Driver
url=jdbc:mysql://localhost:3306/test?serverTimezone=Asia/Shanghai&?useUnicode=true&characterEncoding=utf8&useSSL=false
username=root
password=root

4、如果mysql-connector-java用的6.0以上的，如：
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>6.0.6</version>
</dependency>
但是你的driver用的还是com.mysql.jdbc.Driver就会报错，此时需要把com.mysql.jdbc.Driver改为com.mysql.cj.jdbc.Driver。

````

````
curl -X POST http://localhost:8900/actuator/bus-refresh
{"timestamp":"2020-04-01T03:54:10.836+0000","status":405,"error":"Method Not Allowed","message":"Request method 'POST' not supported","path":"/actuator/bus-refresh"}

设置
management.endpoints.web.exposure.include=*
````


````
检查下 RabbitMQ 连接
Channel shutdown: connection error
````

````
Description:
Parameter 1 of method requestRateLimiterGatewayFilterFactory in org.springframework.cloud.gateway.config.GatewayAutoConfiguration required a single bean, but 2 were found:
	- userKeyResolver: defined by method 'userKeyResolver' in class path resource [com/sc/config/Config.class]
	- ipKeyResolver: defined by method 'ipKeyResolver' in class path resource [com/sc/config/Config.class]
Action:
Consider marking one of the beans as @Primary, updating the consumer to accept multiple beans, or using @Qualifier to identify the bean that should be consumed
Disconnected from the target VM, address: '127.0.0.1:51782', transport: 'socket'
Process finished with exit code 1


spring cloud gateway 
key-resolver 与 config 一一对应

````


````
Servlet.service() for servlet [dispatcherServlet] in context with path [] threw exception [Request processing failed; nested exception is org.springframework.web.client.ResourceAccessException: I/O error on POST request for "http://producer/login": producer; nested exception is java.net.UnknownHostException: producer] with root cause

如果使用服务名调用 需要开启负载 (@LoadBalanced)
@Bean
@LoadBalanced
public RestTemplate restTemplate(RestTemplateBuilder builder) {
	return builder.build();
}
RestTemplate

Spring Boot<=1.3 无需定义，Spring Boot自动为您定义了一个。
Spring Boot >= 1.4 Spring Boot不再自动定义一个RestTemplate，而是定义了一个RestTemplateBuilder允许您更好地控制所RestTemplate创建的对象


````


````
 Property 'configuration' and 'configLocation' can not specified with together
 
 yml配置
 mybatis-plus.config-location
 与
 mybatis-plus.configuration.log-impl
配置冲突(config-location  与  configuration)
删除一个即可
````

