````
spring-boot-starter-web
spring-boot-starter-gateway
两个包之间会冲突


<packaging>pom</packaging> 找不到配置文件



SpringBoot对应SpringCloud版本号
https://start.spring.io/actuator/info
[spring-cloud]内容对照

Spring IO Platform与SpringBoot版本对应关系
https://blog.csdn.net/nnsword/article/details/86979647
https://spring.io/projects/platform#learn

错误
Exception in thread "main" java.lang.NoClassDefFoundError: org/springframework/util/unit/DataSize
maven compile 
pom版本问题


ERROR 27915 --- [0.0-8081-exec-1] o.a.c.c.C.[.[.[/].[dispatcherServlet]    : 
Servlet.service() for servlet [dispatcherServlet] in context with path [] threw exception [Request processing failed; nested exception is feign.RetryableException: Operation timed out (Connection timed out) executing GET http://spring-cloud-producer/hello?name=dfdsf] with root cause
新项目出现问题，先查看服务端口是否被占用
localhost:8080 访问没有问题
192.168.1.101:8080 访问有问题， 就改项目端口



Cannot load driver class: com.mysql.cj.jdbc.Driver
检查数据库版本
mysql 
select version()


```
