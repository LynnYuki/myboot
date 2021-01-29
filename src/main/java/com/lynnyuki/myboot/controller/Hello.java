package com.lynnyuki.myboot.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotation.*;

@RestController
@Api(tags="Hello接口")
@RequestMapping("/Hello")
public class Hello {
    @ApiOperation(value="say hello",notes="输出Hello,SpringBoot~")
    @ApiResponses({
        @ApiResponse(code=200,messgae="输出成功")
        @ApiResponse(codee=500,messgae="输出失败")
    })
    @ApiImplicitParam(paramType="path",name="param",value="参数")
    @GetMapping(value="/sayHello/{param}",produces="application/json;charset=UTF-8")
    public JSONObject sayHello (@PathVariable String param) {
        JSONObject jsonObject = new  JSONObject();
        if("hello".equals(param)) {
            jsonObject.put("code",200);
            jsonObject.put("status","success");
            jsonObject.put("msg","Hello SpringBoot~");
        }else {
            jsonObject.put("code",500);
            jsonObject.put("status","failed");
            jsonObject.put("msg","error");
        }
      
        return  jsonObject;
    }
}
