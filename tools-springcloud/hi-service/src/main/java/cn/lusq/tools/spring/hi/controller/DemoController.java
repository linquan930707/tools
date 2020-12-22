package cn.lusq.tools.spring.hi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * lusq
 * 2020/12/19 16:37
 */
@RestController
public class DemoController {

    @GetMapping(value = "hi")
    public String hi(@RequestParam(name = "name")  String name){
        return "hi!"+name;
    }



}
