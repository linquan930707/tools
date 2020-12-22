package cn.lusq.tools.spring.feign.controller;

import cn.lusq.tools.spring.feign.service.FeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * lusq
 * 2020/12/22 14:14
 */
@RestController
public class FeignController {

    @Autowired
    private FeignService feignService;

    @GetMapping(value = "hi")
    public String hi(@RequestParam(name = "name")  String name){
        return feignService.hi(name);
    }
}
