package com.yezhihun.demo.service.impl;

import com.yezhihun.demo.dao.MonsterDao;
import com.yezhihun.demo.entity.monster.Monster;
import com.yezhihun.demo.service.MonsterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * Created by tianye on 2019/5/25.
 */
@Service
public class MonsterSeviceImpl extends AbstractBaseServiceImpl<Monster> implements MonsterService{
    @Autowired
    private MonsterDao monsterDao;

    @Override
    @PostConstruct
    public void init() {
        this.baseDao = monsterDao;
    }
}
