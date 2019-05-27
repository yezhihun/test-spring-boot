package com.yezhihun.demo.service.impl;

import com.yezhihun.demo.dao.MonsterTemplateDao;
import com.yezhihun.demo.service.MonsterTemplateService;
import com.yezhihun.demo.template.MonsterTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by tianye on 2019/5/27.
 */
@Service
public class MonsterTemplateServiceImpl extends AbstractBaseServiceImpl<MonsterTemplate> implements MonsterTemplateService {
    @Autowired
    private MonsterTemplateDao monsterTemplateDao;

    @Override
    public void init() {
        this.baseDao = monsterTemplateDao;
    }
}
