package cn.lusq.tools.spring.hi;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * lusq
 * 2020/12/19 16:37
 */
@SpringCloudApplication
@EnableEurekaClient
public class HiApplication {
    public static void main(String[] args) {
        SpringApplication.run(HiApplication.class, args);
    }
}
