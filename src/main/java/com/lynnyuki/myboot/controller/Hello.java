package com.lynnyuki.myboot.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Hello {
    @GetMapping("/sayHello")
    public JSONObject sayHello () {
        JSONObject jsonObject = new  JSONObject();
        jsonObject.put("code",200);
        jsonObject.put("status","success");
        jsonObject.put("msg","Hello World");
        return  jsonObject;
    }
}
