package cn.lusq.tools.springcloud.ribbon.service.impl;

import cn.lusq.tools.springcloud.ribbon.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * 陆书勤
 * 2020/12/19 16:22
 */

@Service
public class DemoServiceImpl implements DemoService {

    @Autowired
    private RestTemplate restTemplate;
    @Override
    public String hi(String name) {
        return restTemplate.getForObject("http://SERVICE-HI/hi?name="+name,String.class);
    }
}
