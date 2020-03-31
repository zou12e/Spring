package com.sc.producer.mapper;

import com.sc.producer.model.User;

import java.util.List;

/**
 * @Version: 1.0
 * @Desc:
 */
public interface UserMapper {

    List<User> getAll();

    User getUser(String id);

    Long insertUser(User user);

    Long updateUser(User user);

    Long deleteUser(Integer id);

//    2020-03-31 23:20:57.757 ERROR 25041 --- [ost-startStop-1]
//    o.s.b.web.embedded.tomcat.TomcatStarter  :
//    Error starting Tomcat context. Exception: org.springframework.beans.factory.BeanCreationException.
//            Message: Error creating bean with name 'servletEndpointRegistrar'
//    defined in class path resource [org/springframework/boot/actuate/autoconfigure/endpoint/web/ServletEndpointManagementContextConfiguration$WebMvcServletEndpointManagementContextConfiguration.class]:
//    Bean instantiation via factory method failed; nested exception is org.springframework.beans.BeanInstantiationException:
//    Failed to instantiate [org.springframework.boot.actuate.endpoint.web.ServletEndpointRegistrar]:
//    Factory method 'servletEndpointRegistrar' threw exception;
//    nested exception is org.springframework.beans.factory.BeanCreationException:
//    Error creating bean with name 'healthEndpoint' defined in class path resource
//[org/springframework/boot/actuate/autoconfigure/health/HealthEndpointConfiguration.class]:
//    Bean instantiation via factory method failed; nested exception is org.springframework.beans.BeanInstantiationException:
//    Failed to instantiate [org.springframework.boot.actuate.health.HealthEndpoint]:
//    Factory method 'healthEndpoint' threw exception;
//    nested exception is org.springframework.beans.factory.BeanCreationException:
//    Error creating bean with name 'org.springframework.boot.actuate.autoconfigure.jdbc.DataSourceHealthIndicatorAutoConfiguration':
//    Bean instantiation via constructor failed; nested exception is org.springframework.beans.BeanInstantiationException:
//    Failed to instantiate [org.springframework.boot.actuate.autoconfigure.jdbc.DataSourceHealthIndicatorAutoConfiguration$$EnhancerBySpringCGLIB$$24418988]:
//    Constructor threw exception; nested exception is org.springframework.beans.factory.BeanCreationException:
//    Error creating bean with name 'dataSource': Post-processing of
//        FactoryBean's singleton object failed; nested exception is org.springframework.beans.factory.BeanCreationException: Error creating bean with name '
//    scopedTarget.dataSource' defined in class path resource [org/springframework/boot/autoconfigure/jdbc/DataSourceConfiguration$Hikari.class]: Bean instantiation via factory method failed; nested exception is org.springframework.beans.BeanInstantiationException: Failed to instantiate [com.zaxxer.hikari.HikariDataSource]:
//    Factory method 'dataSource
//    ' threw exception; nested exception is java.lang.IllegalStateException: Cannot load driver class: com.mysql.cj.jdbc.Driver

}