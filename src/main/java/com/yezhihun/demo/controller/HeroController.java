package com.yezhihun.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.yezhihun.demo.service.HeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by tianye on 2019/5/20.
 */
@RestController
public class HeroController {

    @Autowired
    private HeroService heroService;


    @RequestMapping("/createHero")
    public JSONObject createHero(){
        heroService.createHero();
        return null;
    }
}
