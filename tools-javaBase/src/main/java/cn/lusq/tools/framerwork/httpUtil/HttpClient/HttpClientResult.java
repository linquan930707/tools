package cn.lusq.tools.framerwork.httpUtil.HttpClient;

import java.io.Serializable;

/**
 * lusq
 * 2021/3/24 17:27
 */
public class HttpClientResult implements Serializable {
    /**
     * 响应状态码
     */
    private int code;

    /**
     * 响应数据
     */
    private String content;

    public HttpClientResult(int code, String content) {
        this.code=code;
        this.content = content;
    }

    public HttpClientResult(int code) {
        this.code=code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
