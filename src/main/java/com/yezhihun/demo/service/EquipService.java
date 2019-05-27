package com.yezhihun.demo.service;

import com.yezhihun.demo.entity.Equip;
import com.yezhihun.demo.entity.Hero;
import com.yezhihun.demo.template.EquipTemplate;

/**
 * Created by tianye on 2019/5/27.
 */
public interface EquipService extends BaseService<Equip>{

    boolean insertEquipByTemplateForHero(EquipTemplate equipTemplate, Hero hero);
}
