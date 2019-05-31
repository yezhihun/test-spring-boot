package com.yezhihun.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.yezhihun.demo.entity.User;
import com.yezhihun.demo.service.UserService;
import com.yezhihun.demo.util.TargetDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by tianye on 2018/5/3.
 */
@RestController
public class HelloController {

    @Autowired
    private UserService userService;

//    @Autowired
//    private RuleProducter ruleProducter;

    @RequestMapping("/hello")
    public String hello(){
        return "hello spring-boot";
    }

    @RequestMapping("/getUserById")
//    @ResponseBody
    public JSONObject getByUserId(Integer userId){
        User user = userService.selectByPrimaryKey(1);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("user", user);

        return jsonObject;
    }

    @RequestMapping("/getUserById2")
    @ResponseBody
    @TargetDataSource("read")
    public JSONObject getByUserId2(Integer userId){
        User user = userService.selectByPrimaryKey(1);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("user", user);

        return jsonObject;
    }

    @RequestMapping("/sendMessage")
    @ResponseBody
    public JSONObject sendMessage(){
//        User user = userService.selectByPrimaryKey(1);
//        try {
//            ruleProducter.send(user);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("user", 111);

        return jsonObject;
    }
}
