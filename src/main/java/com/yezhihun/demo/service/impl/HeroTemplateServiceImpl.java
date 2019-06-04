package com.yezhihun.demo.service.impl;

import com.yezhihun.demo.dao.HeroTemplateDao;
import com.yezhihun.demo.enums.Occupation;
import com.yezhihun.demo.service.HeroTemplateService;
import com.yezhihun.demo.entity.template.HeroTemplate;
import com.yezhihun.demo.util.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by tianye on 2019/5/27.
 */
@Service
@Transactional
public class HeroTemplateServiceImpl extends AbstractBaseServiceImpl<HeroTemplate> implements HeroTemplateService{
    @Autowired
    private HeroTemplateDao heroTemplateDao;

    @Override
    public void init() {
        this.baseDao = heroTemplateDao;
    }

    @Override
    public List<HeroTemplate> selectAllByOccupation(Occupation occupation) {
        return heroTemplateDao.findByOccupation(occupation);
    }

    @Override
    public HeroTemplate selectOneByOccupation(Occupation occupation) {
        List<HeroTemplate> list = selectAllByOccupation(occupation);

        return (HeroTemplate) RandomUtil.getRandomObjectForList(list);
    }
}
