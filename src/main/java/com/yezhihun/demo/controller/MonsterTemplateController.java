package com.yezhihun.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.yezhihun.demo.entity.template.MonsterTemplate;
import com.yezhihun.demo.enums.Occupation;
import com.yezhihun.demo.service.MonsterTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by tianye on 2019/5/25.
 */
@Controller
@RequestMapping(value = "/monsterTemplate")
public class MonsterTemplateController {

    @Autowired
    private MonsterTemplateService monsterTemplateService;


    @RequestMapping(value = "/insert", method = RequestMethod.GET)
    public JSONObject insert(){
        MonsterTemplate monsterTemplate = new MonsterTemplate();
        monsterTemplate.setLevel(1);
        monsterTemplate.setName("史莱姆");
        monsterTemplate.setPower(10);
        monsterTemplate.setMentality(1);
        monsterTemplate.setMagicResist(0);
        monsterTemplate.setAggressivity(10);
        monsterTemplate.setBlood(100);
        monsterTemplate.setOccupation(Occupation.SOLDIER);

        MonsterTemplate mt = monsterTemplateService.selectByPrimaryKey(2);
        return null;
    }

    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public JSONObject query(){

        MonsterTemplate monsterTemplate = monsterTemplateService.selectByPrimaryKey(2);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data", monsterTemplate);
        return jsonObject;
    }
}
