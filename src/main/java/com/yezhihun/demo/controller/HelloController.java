package com.yezhihun.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.yezhihun.demo.configuration.JedisProxy;
import com.yezhihun.demo.entity.User;
import com.yezhihun.demo.service.UserService;
import com.yezhihun.demo.util.TargetDataSource;

/**
 * Created by tianye on 2018/5/3.
 */
@RestController
public class HelloController {
	
	@Autowired
	private JedisProxy jedisProxy;

    @Autowired
    private UserService userService;

//    @Autowired
//    private RuleProducter ruleProducter;

    @RequestMapping("/hello")
    public String hello(){
    	jedisProxy.getJedis().set("test", "hello dj");
    	System.out.println(jedisProxy.getJedis().get("test"));
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
        User user = userService.selectByPrimaryKey(userId);
        System.out.println(user.getName());
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
