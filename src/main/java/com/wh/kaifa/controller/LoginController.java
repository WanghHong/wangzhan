package com.wh.kaifa.controller;

import com.wh.kaifa.DTO.LoginDTO;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wanghong on 2020/6/27.
 */
@RestController
@RequestMapping("/api")
public class LoginController {

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody LoginDTO loginDTO) {
        System.out.println("login info" + loginDTO);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("code", "000000");
        resultMap.put("msg", "success");
        resultMap.put("token", "wh123456");
        return  resultMap;
    }

}
