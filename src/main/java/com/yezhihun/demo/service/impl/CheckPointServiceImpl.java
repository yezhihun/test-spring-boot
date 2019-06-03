package com.yezhihun.demo.service.impl;

import com.yezhihun.demo.dao.CheckPointDao;
import com.yezhihun.demo.entity.Equip;
import com.yezhihun.demo.entity.Hero;
import com.yezhihun.demo.entity.checkpoint.CheckPoint;
import com.yezhihun.demo.entity.monster.Slime;
import com.yezhihun.demo.entity.template.EquipTemplate;
import com.yezhihun.demo.service.CheckPointService;
import com.yezhihun.demo.service.EquipService;
import com.yezhihun.demo.service.EquipTemplateService;
import com.yezhihun.demo.util.CalculatAttribute;
import com.yezhihun.demo.util.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by tianye on 2019/5/25.
 */
@Service
public class CheckPointServiceImpl extends AbstractBaseServiceImpl<CheckPoint> implements CheckPointService {
    @Autowired
    private CheckPointDao mapDao;
    @Autowired
    private EquipTemplateService equipTemplateService;
    @Autowired
    private EquipService equipService;

    @Override
    public void init() {
        this.baseDao = mapDao;
    }

    /**
     * 刷副本
     * @param hero
     * @param checkPoint
     */
    @Override
    public Map<String, Object> battleMap(Hero hero, CheckPoint checkPoint){
        /**
         * 1,关卡生成随机怪物（数量？boss?)
         * 2,战斗（同一地图是否可加血）
         * 3,副本结算（随机掉落+固定掉落）
         */
        boolean flag = true;
        for (int i=0;i<10 && flag;i++){
            Slime slime = new Slime();
            /**
             * TODO 根据模板生成怪物
             */
            RandomUtil.getRandomMonster(checkPoint, slime, RandomUtil.getRandomMonsterPotential());

            flag = CalculatAttribute.battle(hero, CalculatAttribute.calculatHeroAttr(hero), slime);
        }
        if (!flag){
            //TODO 战斗失败 退出副本
            settle4Fail(hero, checkPoint);
        } else {
            //TODO 副本成功
            settle4Success(hero, checkPoint);
        }

        return null;
    }

    /**
     * 副本失败结算
     */
    private void settle4Fail(Hero hero, CheckPoint checkPoint){
        //副本失败无物品获得
    }

    /**
     * 副本成功结算
     */
    private void settle4Success(Hero hero, CheckPoint checkPoint){
        //副本成功随机掉落+固定掉落
        //掉落等级装备*2
        //TODO 随机挑选几个模板
        List<EquipTemplate> equipTemplateList = equipTemplateService.findByLevel(checkPoint.getLevel());
        for (EquipTemplate et : equipTemplateList){
            Equip equip = RandomUtil.getRandomEquipByTemplate(et);
            equipService.insertEquipForHero(equip, hero);
        }
    }


}
