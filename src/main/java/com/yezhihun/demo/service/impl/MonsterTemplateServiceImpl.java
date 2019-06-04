package com.yezhihun.demo.service.impl;

import com.yezhihun.demo.dao.MonsterTemplateDao;
import com.yezhihun.demo.service.MonsterTemplateService;
import com.yezhihun.demo.entity.template.MonsterTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by tianye on 2019/5/27.
 */
@Service
@Transactional
public class MonsterTemplateServiceImpl extends AbstractBaseServiceImpl<MonsterTemplate> implements MonsterTemplateService {
    @Autowired
    private MonsterTemplateDao monsterTemplateDao;

    @Override
    public void init() {
        this.baseDao = monsterTemplateDao;
    }

    @Override
    public List<MonsterTemplate> selectByLevel(int level) {
        return monsterTemplateDao.findByLevel(level);
    }
}
