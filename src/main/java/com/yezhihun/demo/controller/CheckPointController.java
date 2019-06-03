package com.yezhihun.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.yezhihun.demo.entity.Hero;
import com.yezhihun.demo.entity.checkpoint.CheckPoint;
import com.yezhihun.demo.service.CheckPointService;
import com.yezhihun.demo.service.HeroService;
import com.yezhihun.demo.service.MonsterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by tianye on 2019/5/25.
 */
@RestController
public class CheckPointController {

    @Autowired
    private MonsterService monsterService;
    @Autowired
    private CheckPointService checkPointService;
    @Autowired
    private HeroService heroService;

    /**
     * 1 生成英雄
     * 2 插入地图
     * 3 插入怪物模板
     * 4 插入装备模板
     *
     * @param heroId
     * @param checkPointId
     * @return
     */

    @RequestMapping(name = "battleMap", method = RequestMethod.POST)
    public JSONObject battleMap(@RequestParam(value = "heroId") int heroId, @RequestParam(value = "checkPointId") int checkPointId){
        Hero hero = heroService.selectByPrimaryKey(heroId);
        if (hero == null){
            //TODO 参数错误
        }
        CheckPoint checkPoint = checkPointService.selectByPrimaryKey(checkPointId);
        if (checkPoint == null){
            //TODO 参数错误
        }
        checkPointService.battleMap(hero, checkPoint);
        return null;
    }
}
