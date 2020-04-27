package com.sc.netty;

import com.sc.netty.websoket.WSServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringCloudNettyApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudNettyApplication.class, args);

        WSServer.getInstance().start();

    }

}
