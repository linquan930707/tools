package cn.lusq.tools.spring.feign.service.impl;

import cn.lusq.tools.spring.feign.service.FeignService;
import cn.lusq.tools.spring.feign.service.outside.HiFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 陆书勤
 * 2020/12/22 14:16
 */
@Service
public class FeignServiceImpl implements FeignService {
    @Autowired
    private HiFeign hiFeign;


    @Override
    public String hi(String name) {
        return hiFeign.hi(name);
    }
}
