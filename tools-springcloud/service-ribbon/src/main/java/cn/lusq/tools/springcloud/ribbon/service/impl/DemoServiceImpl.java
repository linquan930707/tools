package cn.lusq.tools.springcloud.ribbon.service.impl;

import cn.lusq.tools.springcloud.ribbon.service.DemoService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * lusq
 * 2020/12/19 16:22
 */

@Service
public class DemoServiceImpl implements DemoService {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 在hiService方法上加上@HystrixCommand注解。该注解对该方法创建了熔断器的功能，并指定了fallbackMethod熔断方法
     * @param name
     * @return
     */
    @Override
    @HystrixCommand(fallbackMethod = "hiError")
    public String hi(String name) {
        return restTemplate.getForObject("http://HI-SERVICE/hi?name="+name,String.class);
    }



    public String hiError(String name) {
        return "hi,"+name+",sorry,error!";
    }
}
