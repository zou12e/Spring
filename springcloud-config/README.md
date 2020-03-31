### Spring Cloud Config 

#### Server
````
    提供客户端和服务端的支持
    提供各个环境的配置
    配置文件修改后可以快速生效
    可以提供不同版本的管理
    可以支持不同的语言（java、.Net、Delphi、node等）
    支持一定数量的并发
    高可用（防止意外宕机导致配置不可用）

    注解
    @EnableConfigServer

    仓库中的配置文件会被转换成web接口，访问可以参照以下的规则：
    /{application}/{profile}[/{label}]
    /{application}-{profile}.yml
    /{label}/{application}-{profile}.yml
    /{application}-{profile}.properties
    /{label}/{application}-{profile}.properties
    以springcloud-config-dev.properties为例，
    它的application是springcloud-config，
    profile是dev，
    label是分支的意思，如果只有一个主分支，可以不写，默认会访问master分支，
    client会根据填写的参数来选择读取对应的配置。
````

### RabbitMQ
````
    http://127.0.0.1:15672
    guest
    guest
````