package cn.lusq.tools.framerwork.httpUtil;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * JDK提供  使用java.net.HttpURLConnection 连接
 * lusq
 * 2021/3/22 18:11
 */
public class JDKHttpUrlConnectionUtil {
    /**
     * 使用jdk自带的HttpURLConnection向URL发送POST请求并输出响应结果
     * 参数使用流传递，并且硬编码为字符串"name=XXX"的格式
     */
    public static String post(String urlStr, String data){
        try {
            //url?name=XX&&age=XX
            //建立连接
            URL url = new URL(urlStr);
            // 创建HttpURLConnection对象
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            //设置请求方式
            connection.setRequestMethod("POST");
            // 超时时间
            connection.setConnectTimeout(3000);
            //设置请求头  heard数据
            connection.setRequestProperty("Content-Type", "application/json; charset=utf-8");
            //需要输出
            connection.setDoOutput(true);
            //需要输入
            connection.setDoInput(true);
            //不允许缓存
            connection.setUseCaches(false);
            //接，从上述第2条中url.openConnection()至此的配置必须要在connect之前完成，
            connection.connect();
            //建立输入流，向指向的URL传入参数
            DataOutputStream dataOutputStream = new DataOutputStream(connection.getOutputStream());
            //将字符串转换成json对象
            JSONObject jsonObject = JSON.parseObject(data);
            //写出json字符串
            dataOutputStream.writeBytes(jsonObject.toJSONString());
            //刷新对象输出流，将任何字节都写入潜在的流中
            dataOutputStream.flush();
            //关闭输出流
            dataOutputStream.close();
            // //获得响应状态
            int responseCode = connection.getResponseCode();
            if(HttpURLConnection.HTTP_OK==responseCode){
                StringBuilder result = new StringBuilder();
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                while((line = reader.readLine()) != null){
                    result.append(line);
                }
                reader.close();
                connection.disconnect();
                return result.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 使用jdk自带的HttpURLConnection向URL发送GET请求并输出响应结果
     * 参数使用流传递，并且硬编码为字符串"name=XXX"的格式
     */
    public static String get(String strUrl){
        try {
            URL url = new URL(strUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // 在连接之前设置各种属性
            // Content-Type实体头用于向接收方指示实体的介质类型，指定HEAD方法送到接收方的实体介质类型，或GET方法发送的请求介质类型
            conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
            // 设置打开与此URLConnection引用的资源的通信链接时使用的指定超时值（以毫秒为单位）
            conn.setConnectTimeout(10000);
            // 将读取超时设置为指定的超时时间，以毫秒为单位。
             conn.setReadTimeout(60000);
            conn.setRequestMethod("GET");
            conn.setUseCaches(false);
            // 建立连接
            conn.connect();
            // 获取响应
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            StringBuilder result = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            reader.close();
            conn.disconnect();
            return result.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
