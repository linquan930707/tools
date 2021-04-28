package cn.lusq.tools.framerwork.httpUtil;


import cn.lusq.tools.framerwork.httpUtil.HttpClient.HttpClientResult;
import cn.lusq.tools.framerwork.httpUtil.HttpClient.HttpClientUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * lusq
 * 2021/3/23 18:07
 */
public class HttpClientUtilTest {

    static final Log LOG = LogFactory.getLog(HttpClientUtilTest.class);


    @Test
    public void testPost() throws Exception {
        String url = "";
        Map<String,String>paging = new HashMap<>();
        paging.put("index","1");
        paging.put("size","10");
        paging.put("paging","true");
        Map<String,String>headers = new HashMap<>();
        headers.put("Content-Type","application/json");
        headers.put("Authorization","");
        headers.put("Host","");
        headers.put("User-Agent","");
        headers.put("uuid","");
        HttpClientResult httpClientResult = HttpClientUtil.doPost(url,headers,paging);
        System.out.println(httpClientResult.getCode());
        if(httpClientResult.getCode() == 200 ){
            LOG.info(httpClientResult.getContent());
        }
    }


    @Test
    public void baseRequest() throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("");

        Map<String,Object> paramer = new HashMap<>();
        Map<String,String>paging = new HashMap<>();
        paging.put("index","1");
        paging.put("size","10");
        paging.put("paging","true");
        paramer.put("page", JSONObject.parseObject(JSON.toJSONString(paging)));
        StringEntity stringEntity = new StringEntity(JSON.toJSONString(paging),"utf-8");
        httpPost.setEntity(stringEntity);
        httpPost.addHeader("Content-Type","application/json");
        httpPost.addHeader("Authorization","");
        httpPost.addHeader("Host","");
        httpPost.addHeader("User-Agent","");
        httpPost.addHeader("uuid","");
        CloseableHttpResponse response = client.execute(httpPost);
        String resultString = EntityUtils.toString(response.getEntity(), "utf-8");
        System.out.println(resultString);



    }


}
