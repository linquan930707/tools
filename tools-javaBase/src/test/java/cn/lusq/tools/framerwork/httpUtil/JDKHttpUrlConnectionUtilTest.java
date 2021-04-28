package cn.lusq.tools.framerwork.httpUtil;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * lusq
 * 2021/3/25 9:19
 */
public class JDKHttpUrlConnectionUtilTest {

    @Test
    public void test1(){
        String url = "";
        Map<String,String>paging = new HashMap<>();
        paging.put("index","1");
        paging.put("size","10");
        paging.put("paging","true");
        Map<String,String>headers = new HashMap<>();
        headers.put("Content-Type","application/json");
        headers.put("Authorization","");
        headers.put("Host","");
        headers.put("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.141 Safari/537.36");
        headers.put("uuid","");
        String responseStr = JDKHttpUrlConnectionUtil.post(url,paging,headers);
    }



}
