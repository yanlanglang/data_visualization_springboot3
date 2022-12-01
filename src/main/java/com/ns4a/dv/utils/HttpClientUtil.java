package com.ns4a.dv.utils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * @className: HttpClientUtil
 * @author: Yan Lang
 * @date: 2022/11/24
 * @description:
 **/
public class HttpClientUtil {

    /**
     * 根据api获取响应消息
     * @param api 接口地址
     * @return
     * @throws IOException
     * @throws InterruptedException
     * @throws URISyntaxException
     */
    public HttpResponse<String> getResponse(String api) throws IOException, InterruptedException, URISyntaxException {
        //直接创建一个新的HttpClient
        HttpClient client = HttpClient.newHttpClient();
        //现在我们只需要构造一个Http请求实体，就可以让客户端帮助我们发送出去了（实际上就跟浏览器访问类似）
        HttpRequest request = HttpRequest.newBuilder().uri(new URI(api)).build();
        //现在我们就可以把请求发送出去了，注意send方法后面还需要一个响应体处理器（内置了很多）这里我们选择ofString直接吧响应实体转换为String字符串
        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }

}
