package cn.lusq.tools.spring.hi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 陆书勤
 * 2020/12/19 16:37
 */
@SpringBootApplication
@EnableEurekaClient
public class HiApplication {
    public static void main(String[] args) {
        SpringApplication.run(HiApplication.class, args);
    }
}
