package com.yezhihun.demo.service.impl;

import com.yezhihun.demo.dao.EquipDao;
import com.yezhihun.demo.entity.Equip;
import com.yezhihun.demo.entity.Hero;
import com.yezhihun.demo.service.EquipService;
import com.yezhihun.demo.service.EquipTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by tianye on 2019/5/27.
 */
@Service
@Transactional
public class EquipServiceImpl extends AbstractBaseServiceImpl<Equip> implements EquipService{
    @Autowired
    private EquipDao equipDao;
    @Autowired
    private EquipTemplateService equipTemplateService;

    @Override
    public void init() {
        this.baseDao = equipDao;
    }


    @Override
    public Equip insertEquipForHero(Equip equip, Hero hero) {
        equip.setOwner(hero);
        return equipDao.save(equip);
    }
}
