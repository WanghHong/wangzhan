package com.wh.kaifa.controller;

import org.springframework.web.bind.annotation.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * Created by wanghong on 2020/5/14
 */
@RestController
@RequestMapping("/wx")
public class WxController {

    @GetMapping("/test1")
    public String test(String timestamp, String signature,
                     String nonce, String echostr) {
        System.out.println("wanghong");
        System.out.println("===" + timestamp);
        System.out.println("===" + signature);
        System.out.println("===" + nonce);
        System.out.println("===" + echostr);


        String[] strs = new String[]{"wanghong", timestamp, nonce};
        Arrays.sort(strs);
        String str = strs[0] + strs[1] + strs[2];
        String s = sha1(str);
        if (s.equals(signature)) {
            System.out.println("11111");
            return echostr;
        } else {
            System.out.println("22222");
        }
        return "";

    }

    public static String sha1(String src) {
        try {
            MessageDigest sha1 = MessageDigest.getInstance("sha1");
            byte[] digest = sha1.digest(src.getBytes());
            char[] chars = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
            StringBuffer sb = new StringBuffer();
            for (byte b : digest) {
                sb.append(chars[(b >> 4)&15]);
                sb.append(chars[b&15]);
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }


    @GetMapping("/test")
    public void test(String code) {
        System.out.println("wanghong11");
        System.out.println("===" + code);
    }


}
