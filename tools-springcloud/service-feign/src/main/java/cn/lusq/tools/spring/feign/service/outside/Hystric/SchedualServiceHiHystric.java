package cn.lusq.tools.spring.feign.service.outside.Hystric;

import cn.lusq.tools.spring.feign.service.outside.HiFeign;
import org.springframework.stereotype.Component;

/**
 * lusq
 * 2020/12/22 14:53
 */
@Component
public class SchedualServiceHiHystric implements HiFeign {
    @Override
    public String hi(String name) {
        return "sorry "+name;
    }
}
