package cn.lusq.tools.springcloud.registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * lusq
 * 2020/12/19 13:59
 */
@SpringBootApplication
@EnableEurekaServer
public class RegistryApplication {


    public static void main(String[] args) {
        SpringApplication.run(RegistryApplication.class, args);
    }

}
