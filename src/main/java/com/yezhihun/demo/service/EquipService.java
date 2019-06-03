package com.yezhihun.demo.service;

import com.yezhihun.demo.entity.Equip;
import com.yezhihun.demo.entity.Hero;

/**
 * Created by tianye on 2019/5/27.
 */
public interface EquipService extends BaseService<Equip>{

    Equip insertEquipForHero(Equip equip, Hero hero);
}
