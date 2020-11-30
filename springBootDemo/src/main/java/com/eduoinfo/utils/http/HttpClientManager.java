package com.eduoinfo.utils.http;

import com.alibaba.fastjson.JSON;
import lombok.extern.log4j.Log4j2;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;
import org.testng.Assert;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

@Log4j2
@Service
public class HttpClientManager {

    private CloseableHttpClient httpClient = HttpClients.createDefault();

    private CloseableHttpResponse response = null;

    public String doGet(String url, String message) {
        String sendURL = url + message;
        HttpGet get = new HttpGet(sendURL);

        try {
            response = httpClient.execute(get);
        } catch (IOException e) {
            e.printStackTrace();
        }

        log.info("sendURL:" + sendURL);
        Assert.assertEquals(response.getStatusLine().getStatusCode(),
                HttpStatus.SC_OK, "请求URL失败" + response.getStatusLine().getStatusCode());
        String result = null;
        try {
            result = EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info("result:"+ JSON.toJSONString(result,true));
        return result;
    }

    public String doPost(String URL, String message) {
        HttpPost post = new HttpPost(URL);
        if (message != null) {
            String request = message.trim();
            log.info("request:" + message);
        }else {
            log.info("request为空:" + message);
        }


        try {
            post.setEntity(new StringEntity(message));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            response = httpClient.execute(post);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Assert.assertEquals(response.getStatusLine().getStatusCode(),
                HttpStatus.SC_OK, "请求失败" + response.getStatusLine().getStatusCode());
        String result = null;
        try {
            result = EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info("result:"+result);
        return result;


    }
}
