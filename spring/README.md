## Spring学习笔记

1. 概述：

   - Spring 全栈式的开源框架；
   - IOC （控制反转，依赖注入）
     - 控制反转：把创建对象的权利交给框架，消减计算机程序的耦合，消减代码的依赖关系
     - 依赖注入：依赖关系的维护（交给Spring管理）
   - AOP（面向切面编程)）
2. 优势：

   - 解耦，简化开发
     - 耦合：程序间的依赖关系，类之间的依赖，方法间的依赖
     - 解藕：降低程序间的依赖关系， 编译期不依赖，运行时依赖
     - 解藕思路：第一步：使用反射来创建对象，避免使用new关键字，第二步：通过读取配置文件来创建对象；
   - AOP支持
   - 声明式事物
   - 方便程序测试
   - 方便使用各种优秀的框架
   - 降低JaveEE API的使用难度



```java
		/**
     * 创建对象的方式与依赖注入
     * @param args
     */
    public static void main(String[] args) {
        /**
         * 普通new()对象
         */
        IService service = new ServiceImpl();
        service.save("new()");

        /**
         * 自己实现BeanFactory
         */
        IService serviceBean = (IService) MyBeanFactory.getBean("service");
        serviceBean.save("MyBeanFactory");



        /**
         *  ClassPathXmlApplicationContext 类路径配置文件
         *  FileSystemXmlApplicationContext 磁盘可访问路径的配置文件
         *  AnnotationConfigApplicationContext 注解
        */
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml") ;
        IService xmlBean = context.getBean("service", IService.class);
        xmlBean.save("xmlService");

        /**
         * ApplicationContext    单例对象适用
         *  构建核心容器时 采用立即加载模式，读取文件后马上创建对象
         *
         * BeanFactory           多例对象适用
         *  构建核心容器时 采用延迟加载的模式，什么时候获取对象，才真正创建对象
         */
        Resource resource = new ClassPathResource("bean.xml");
        BeanFactory beanFactory =  new XmlBeanFactory(resource);
        IService xmlBeanFactory = beanFactory.getBean("service", IService.class);
        xmlBeanFactory.save("xmlBeanFactoryService");

        /**
         * 普通工厂的方法创建对象
         */
        IService beanService = context.getBean("beanService", IService.class);
        beanService.save("beanService");


        /**
         * 普通工厂的静态方法创建对象
         */
        IService staticBeanService = context.getBean("staticService", IService.class);
        staticBeanService.save("staticService");


        /**
         * 构造函数依赖注入
         */
        IService constructorService = context.getBean("constructorService", IService.class);
        constructorService.println("constructorService");


        /**
         * set方法依赖注入
         */
        IService setService = context.getBean("setService", IService.class);
        setService.println("setService");


        /**
         * set方法依赖注入集合
         */
        IService collectionService = context.getBean("collectionService", IService.class);
        collectionService.print("collectionService");
      
    }
```



```xml
1. 创建bean的三种方式
    第一种方式：适用默认的构造的函数创建
    第二种方式：使用普通工厂的方法创建对象
    第三种方式：使用工厂的静态方法创建对象

<bean id="service" class="com.sc.service.impl.ServiceImpl"></bean>
<bean id="beanService" factory-bean="service"  factory-method="getService"></bean>
<bean id="staticService" class="com.sc.service.impl.ServiceImpl" 
      factory-method="getStaticService"  ></bean>

2. bean对象的作用范围
    scope属性
        singleton 单例
        prototype 多例
        request 作用于web应用的请求范围
        session 作用于web应用的会话范围
        globalSession 应用集群的会话范围


3. bean对象的生命周期
    单例对象:
        出生: 当容器创建时时对象出生(解析配置文件时)
        活着: 只要容器还在，对象一直活着
        死亡: 容器销毁，对象销毁
        总结: 单例对象的生命周期与容器相同
    多例对象:
        出生: 当我们使用对象时，spring框架帮我们创建
        活着: 对象只要在使用的过程中就一直活着。
        死亡: 没有别的对象引用时，由Java垃圾回收器回收

4. 依赖注入(DI)
    管理： 交给Spring管理依赖关系，依赖关系的维护被称之为依赖注入
    注入数据三类：
            1. 基本数据类型和String
            2. 其他bean类型（配置文件中的bean，或者注解配置的bean）
            3. 复杂类型/集合类型
               List结构标签 list array set
               Map结构标签 map props
    注入方式三种：
            1. 构造函数提供
                使用constructor-arg标签
                    type: 构造函数数据类型
                    index: 构造函数下标
                    name: 构造函数名字
                    ============================
                    value: 直接赋值
                    ref: 用于其他的bean类型数据
                优势:
                    获取bean对象时，注入数据是必须的操作，否则对象无法创建成功
                弊端:
                    改变了bean对象的实例化方式，是我们创建对象时，如果拿不到这些数据，也必须提供

    <bean id="constructorService" class="com.sc.service.impl.ServiceImpl"   >
        <constructor-arg name="name" value="Jeff" ></constructor-arg>
        <constructor-arg name="age" value="18" ></constructor-arg>
        <constructor-arg name="birthday" ref="now" ></constructor-arg>
    </bean>


            2. 使用set方法提供
                使用property标签
                    name: 用于指定注入时所调用的set方法名称
                    value: 直接赋值
                    ref: 用于其他的bean类型数据

    <bean id="setService" class="com.sc.service.impl.ServiceImpl" >
        <property name="name" value="jeff" ></property>
    </bean>
		
		<!--复杂类型/集合类型 Set方式 -->
    <bean id="collectionService" class="com.sc.service.impl.ServiceImpl"   >
        <property name="myStrs"  >
            <array>
                <value>AAA</value>
            </array>
        </property>
        <property name="myList"  >
            <list>
                <value>BBB</value>
            </list>
        </property>
        <property name="mySet"  >
            <set>
                <value>CCC</value>
                <value>DDD</value>
            </set>
        </property>
        <property name="myMap"  >
           <map>
               <entry key="AAA" value="AAAValue"   ></entry>
               <entry key="BBB"  >
                    <value>BBBValue</value>
               </entry>
           </map>
        </property>
        <property name="myProps"  >
            <props>
                <prop key="AAA" >AAAValue</prop>
                <prop key="BBB" >BBBValue</prop>
            </props>
        </property>
    </bean>

            3. 使用注解提供     
               1. 创建对象  (xml <bean>)
                    @Component
                        作用：用于把当前类对象存入spring容器中
                        属性：value 指定bean的id
                    @Controller   表现层
                    @Service      业务层
                    @Repository   持久层
               2. 注入数据  (xml <property>)
                    @Autowired
                        作用：自动按照类型注入，只要容器中有唯一的一个bean对象，就能注入成功
                             如果ioc容器中没有bean，则报错
                             如果ioc容器有多个bean，配合@Qualifier使用
                        位置：
                            变量上，或者方法上
                    @Qualifier 配合@Autowired使用，选择多个bean中的id
                        属性： value指定bean的id
                    @Resource 直接按照bean的id注入， 可单独使用
                        属性： name指定bean的id
            
                    =============以上注入bean类型，集合只能xml注入=============

                 		@Value
                        作用： 注入基本类型与String类型
                        属性： value指定属性的值，可以使用SpEL表达式 ${表达式}
            
               3. 改变作用域范围  (xml scope)
                    @Scope("singleton") 默认单例
                    @Scope("prototype") 多例
            
               4. 生命周期  (xml init-method destroy-method)
                    @PostConstruct 初始化
                    @PreDestroy    销毁



```



```java
全注解配置
   @Configuration
      作用：指定当前类是一个配置类
           等同于 beanContext.xml
      细节：当配置类作为参数创建对象时，该注解可以不写
           ApplicationContext context = new
           AnnotationConfigApplicationContext(SpringConfiguration.class) ;

   @ComponentScan
      作用：用于通过注解指定spring在创建容器时要扫描的包
      属性：
          value：它和basePackages的作用一样，都是指定创建容器时要扫描的包
                 该注解等同于xml中配置的：
                  <context:component-scan base-package="com.sc"></context:component-scan>

                    
  @Bean
      作用：用于把当前方法的返回值作为bean对象存入spring的ioc容器中
      属性：
           name：用于指定bean的id
      细节：
           当我们使用注解配置方法时，如果方法有参数，spring框架去容器中查找有没有可用的bean对象
           查找的方式和@Autowired注解的作用一样

  @Import
      作用：用于导入其他配置类
      属性
          value：用于指定其他配置类的字节码
            @Configuration 主配置
            @Import        导入的其他子配置

                    
  @PropertySource
      作用：用于指定properties文件位置
      属性：
           value：指定文件名称和路径
                  关键字：classpath，表示类路径下


使用Junit单元测试
  Spring整合junit的配置
      1. 导入spring整合junit jar
                    
          <groupId>org.springframework</groupId>
          <artifactId>spring-test</artifactId>
          <version>5.2.5.RELEASE</version>

          <groupId>junit</groupId>
          <artifactId>junit</artifactId>
          <version>4.12</version>

      2. 使用Junit提供一个注解把原有的main方法替换，替换成spring提供的
          @RunWith(SpringJUnit4ClassRunner.class)

      3. 告知spring容器 ioc创建基于xml还是注解
          @ContextConfiguration(classes = SpringConfiguration.class)
              locations：指定xml配置
              classes：  指定注解类配置

                    
当我们使用spring 5.x版本以上时，junit必须在4.12以上

```







