package cn.lusq.tools.spring.hi.controller;

import cn.lusq.tools.spring.hi.service.HiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * lusq
 * 2020/12/19 16:37
 */
@RestController(value = "/api/v1/hi")
public class DemoController {

    @Autowired
    private HiService hiService;

    @GetMapping(value = "/hi")
    public String hi(@RequestParam(name = "name")  String name){
        return hiService.hi(name);
    }



}
