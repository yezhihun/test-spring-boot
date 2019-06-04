package com.yezhihun.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.yezhihun.demo.entity.Hero;
import com.yezhihun.demo.entity.checkpoint.CheckPoint;
import com.yezhihun.demo.entity.template.EquipTemplate;
import com.yezhihun.demo.entity.template.HeroTemplate;
import com.yezhihun.demo.entity.template.MonsterTemplate;
import com.yezhihun.demo.enums.EquipCategory;
import com.yezhihun.demo.enums.Occupation;
import com.yezhihun.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by tianye on 2019/5/25.
 */
@RestController
@RequestMapping(value = "/checkPoint")
public class CheckPointController {

    @Autowired
    private MonsterService monsterService;
    @Autowired
    private CheckPointService checkPointService;
    @Autowired
    private HeroService heroService;
    @Autowired
    private MonsterTemplateService monsterTemplateService;
    @Autowired
    private HeroTemplateService heroTemplateService;
    @Autowired
    private EquipTemplateService equipTemplateService;

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
    @RequestMapping(value = "/battleMap")
    public JSONObject battleMap(@RequestParam(value = "heroId") int heroId, @RequestParam(value = "checkPointId") int checkPointId){
        Hero hero = heroService.selectByPrimaryKey(heroId);
        hero.toString();
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

    @RequestMapping(value = "/createHero")
    public String createHero(){
        Hero hero = heroService.createHero();

        return "SUCCESS";
    }
    /**
     * 1 生成英雄
     * 2 插入地图
     * 3 插入怪物模板
     * 4 插入装备模板
     * @return
     */
    @RequestMapping(value = "/init")
    public String init(){

        /**
         * 插入英雄模板
         */
        HeroTemplate agileTemplate = new HeroTemplate();
        agileTemplate.setOccupation(Occupation.AGILE);
        agileTemplate.setBlood(100);
        agileTemplate.setAggressivity(10);
        agileTemplate.setAgile(10);
        agileTemplate.setArmor(3);
        agileTemplate.setMagic(50);
        agileTemplate.setMagicResist(1);
        agileTemplate.setPower(6);
        agileTemplate.setName("风行者");

        HeroTemplate soldierTemplate = new HeroTemplate();
        soldierTemplate.setOccupation(Occupation.SOLDIER);
        soldierTemplate.setBlood(100);
        soldierTemplate.setAggressivity(10);
        soldierTemplate.setAgile(5);
        soldierTemplate.setArmor(1);
        soldierTemplate.setMagic(100);
        soldierTemplate.setMagicResist(3);
        soldierTemplate.setPower(3);
        soldierTemplate.setName("战士");

        HeroTemplate masterTemplate = new HeroTemplate();
        masterTemplate.setOccupation(Occupation.MASTER);
        masterTemplate.setBlood(100);
        masterTemplate.setAggressivity(10);
        masterTemplate.setAgile(3);
        masterTemplate.setArmor(3);
        masterTemplate.setMagic(20);
        masterTemplate.setMagicResist(3);
        masterTemplate.setPower(10);
        masterTemplate.setName("法师");

        heroTemplateService.insert(masterTemplate);
        heroTemplateService.insert(agileTemplate);
        heroTemplateService.insert(soldierTemplate);

        /**
         * 插入装备模板
         */
        EquipTemplate equipTemplate = new EquipTemplate();
        equipTemplate.setName("裁决之刃");
        equipTemplate.setLevel(10);
        equipTemplate.setPower(10);
        equipTemplate.setAggressivity(10);
        equipTemplate.setOccupation(Occupation.SOLDIER);
        equipTemplate.setEquipCategory(EquipCategory.ARMS);

        EquipTemplate equipTemplate1 = new EquipTemplate();
        equipTemplate1.setEquipCategory(EquipCategory.JACKET);
        equipTemplate1.setLevel(10);
        equipTemplate1.setPower(10);
        equipTemplate1.setBlood(30);
        equipTemplate1.setMagicResist(5);
        equipTemplate1.setName("银鳞胸甲");

        equipTemplateService.insert(equipTemplate);
        equipTemplateService.insert(equipTemplate1);

        /**
         * 怪物模板
         */
        MonsterTemplate soldierMonsterTemplate = new MonsterTemplate();
        soldierMonsterTemplate.setLevel(1);
        soldierMonsterTemplate.setName("史莱姆");
        soldierMonsterTemplate.setPower(10);
        soldierMonsterTemplate.setMentality(1);
        soldierMonsterTemplate.setMagicResist(0);
        soldierMonsterTemplate.setAggressivity(10);
        soldierMonsterTemplate.setBlood(50);
        soldierMonsterTemplate.setOccupation(Occupation.SOLDIER);

        MonsterTemplate occupationMonsterTemplate = new MonsterTemplate();
        occupationMonsterTemplate.setOccupation(Occupation.AGILE);
        occupationMonsterTemplate.setLevel(1);
        occupationMonsterTemplate.setName("刺客");
        occupationMonsterTemplate.setBlood(50);
        occupationMonsterTemplate.setArmor(3);
        occupationMonsterTemplate.setAggressivity(4);
        occupationMonsterTemplate.setPower(4);

        MonsterTemplate masterMonsterTemplate = new MonsterTemplate();
        masterMonsterTemplate.setOccupation(Occupation.MASTER);
        masterMonsterTemplate.setLevel(1);
        masterMonsterTemplate.setName("僵尸");
        masterMonsterTemplate.setBlood(80);
        masterMonsterTemplate.setAggressivity(5);
        masterMonsterTemplate.setPower(8);

        monsterTemplateService.insert(masterMonsterTemplate);
        monsterTemplateService.insert(soldierMonsterTemplate);
        monsterTemplateService.insert(occupationMonsterTemplate);

        /**
         * 插入地图
         */
        CheckPoint checkPoint = new CheckPoint();
        checkPoint.setLevel(1);
        checkPoint.setName("新手村");
        checkPoint.setMinMonsterLevel(1);
        checkPoint.setMaxMonsterLevel(5);

        checkPointService.insert(checkPoint);
        return "SUCCESS";
    }
}
