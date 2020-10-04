package com.wh.kaifa.utils;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommonUtils {

    public static void main(String[] args) {
        try {
//            Map<String, String> param = new HashMap<>();
//            param.put("grant_type", "client_credential");
//            param.put("appID", "wx11f305cf799ba674");
//            param.put("appsecret", "1a8ee83680e190d95cc44d644ad6d429");
//            String s = doGet("https://api.weixin.qq.com/cgi-bin/token", param);
//            System.out.println("===" + s);
            System.out.println(getAccesToken());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getAccesToken() throws IOException {

        CloseableHttpClient client = HttpClients.createDefault();
        List<BasicNameValuePair> list = new ArrayList<>();
//        list.add(new BasicNameValuePair("grant_type", "client_credential"));
//        list.add(new BasicNameValuePair("appID", "wx11f305cf799ba674"));
//        list.add(new BasicNameValuePair("appsecret", "1a8ee83680e190d95cc44d644ad6d429"));
//        //3、转化参数
//        String params = EntityUtils.toString(new UrlEncodedFormEntity(list, Consts.UTF_8));
//        System.out.println(params);
        //4、创建HttpGet请求
        HttpGet httpGet = new HttpGet("https://qyapi.weixin.qq.com/cgi-bin/user/getuserinfo?access_token=cATZgFZblJdE_ps-oxcFqs5YrIUjB9P006INOgfEyX-yIYytDcrfI-cAh208IId-iiCRuMYJJx8nOqwmeFzXZZGzv6nyoJJulTT3WEKhi3LD2vIsHUYfYuyI1t3Ledr9THl7ICUvP1nUG8LV65p4zA2TBFFyKAczYOom_MP_cPap_JyRqc0XcbYFWvhB6EftIV72at8rymyoI2EiaV8ohA&code=Rxpxdq-uVy65trl8BkrRU1iQCvlXEEJNsAQatWg49JE");
        CloseableHttpResponse response = client.execute(httpGet);
        //5、获取实体
        HttpEntity entity = response.getEntity();
        //将实体装成字符串
        String result = EntityUtils.toString(entity);
        System.out.println(result);
        response.close();
        return result;
    }


    public static String doGet(String url, Map<String, String> param) {
        // 创建Httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();
        String resultString = "";
        CloseableHttpResponse response = null;
        try {
            // 创建uri
            URIBuilder builder = new URIBuilder(url);
            if (param != null) {
                for (String key : param.keySet()) {
                    builder.addParameter(key, param.get(key));
                }
            }
            URI uri = builder.build();
            // 创建http GET请求
            HttpGet httpGet = new HttpGet(uri);
            // 执行请求
            response = httpclient.execute(httpGet);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } catch (Exception e) {
            System.out.println("error" + e);
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultString;
    }

}
