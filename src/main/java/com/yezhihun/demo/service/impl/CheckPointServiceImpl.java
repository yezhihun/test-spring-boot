package com.yezhihun.demo.service.impl;

import com.yezhihun.demo.dao.CheckPointDao;
import com.yezhihun.demo.entity.Hero;
import com.yezhihun.demo.entity.checkpoint.CheckPoint;
import com.yezhihun.demo.entity.monster.Slime;
import com.yezhihun.demo.service.CheckPointService;
import com.yezhihun.demo.util.CalculatAttribute;
import com.yezhihun.demo.util.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * Created by tianye on 2019/5/25.
 */
@Service
public class CheckPointServiceImpl extends AbstractBaseServiceImpl<CheckPoint> implements CheckPointService {
    @Autowired
    private CheckPointDao mapDao;

    @Override
    @PostConstruct
    public void init() {
        this.baseDao = mapDao;
    }

    /**
     * 刷副本
     * @param hero
     * @param checkPoint
     */
    public void battleMap(Hero hero, CheckPoint checkPoint){
        /**
         * 1,关卡生成随机怪物（数量？boss?,怪物等级设置：副本等级*5向上浮动5以内)
         * 2,战斗（同一地图是否可加血）
         * 3,副本结算（随机掉落+固定掉落）
         */
        int baseLevel = checkPoint.getLevel()*5;
        boolean flag = true;
        for (int i=0;i<10 && flag;i++){
            Slime slime = new Slime();
            RandomUtil.getRandomMonster(checkPoint, slime, RandomUtil.getRandomMonsterPotential());

            flag = CalculatAttribute.battle(hero, CalculatAttribute.calculatHeroAttr(hero), slime);
        }
        if (!flag){
            //TODO 战斗失败 退出副本
        } else {
            //TODO 副本成功
        }
    }

    /**
     * 副本失败结算
     */
    private void settle4Fail(){

    }

    /**
     * 副本成功结算
     */
    private void settle4Success(){

    }
}
