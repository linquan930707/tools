package cn.lusq.tools.springcloud.ribbon.controller;

import cn.lusq.tools.springcloud.ribbon.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 陆书勤
 * 2020/12/19 16:22
 */
@RestController
public class DemoController {

    @Autowired
    private DemoService demoService;

    @GetMapping(value = "/hi")
    public String hi(@RequestParam  String name){
        return demoService.hi(name);
    }

}
