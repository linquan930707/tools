package cn.lusq.tools.spring.feign.service.outside;

import cn.lusq.tools.spring.feign.service.outside.Hystric.SchedualServiceHiHystric;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 基于service-feign工程进行改造，只需要在FeignClient的SchedualServiceHi接口的注解中加上fallback的指定类就行了
 *  lusq
 * 2020/12/22 14:16
 */
@FeignClient(value = "hi-service",fallback = SchedualServiceHiHystric.class)
public interface HiFeign {

    @GetMapping(value = "hi")
    public String hi(@RequestParam(name = "name")  String name);



}
