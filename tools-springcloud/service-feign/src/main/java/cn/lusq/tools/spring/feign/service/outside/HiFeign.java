package cn.lusq.tools.spring.feign.service.outside;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *  lusq
 * 2020/12/22 14:16
 */
@FeignClient(value = "hi-service")
public interface HiFeign {

    @GetMapping(value = "hi")
    public String hi(@RequestParam(name = "name")  String name);



}
