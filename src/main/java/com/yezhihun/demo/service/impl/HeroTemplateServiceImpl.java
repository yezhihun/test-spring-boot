package com.yezhihun.demo.service.impl;

import com.yezhihun.demo.dao.HeroTemplateDao;
import com.yezhihun.demo.service.HeroTemplateService;
import com.yezhihun.demo.template.HeroTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by tianye on 2019/5/27.
 */
@Service
public class HeroTemplateServiceImpl extends AbstractBaseServiceImpl<HeroTemplate> implements HeroTemplateService{
    @Autowired
    private HeroTemplateDao heroTemplateDao;

    @Override
    public void init() {
        this.baseDao = heroTemplateDao;
    }
}
