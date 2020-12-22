package cn.lusq.tools.spring.hi.service.impl;

import cn.lusq.tools.spring.hi.service.HiService;
import org.springframework.stereotype.Service;

/**
 *  * lusq
 * 2020/12/22 14:16
 */
@Service
public class HiServiceImpl implements HiService {



    @Override
    public String hi(String name) {
        return "hi!"+name;
    }
}
