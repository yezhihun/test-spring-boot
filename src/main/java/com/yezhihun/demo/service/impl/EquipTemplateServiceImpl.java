package com.yezhihun.demo.service.impl;

import com.yezhihun.demo.dao.EquipTemplateDao;
import com.yezhihun.demo.service.EquipTemplateService;
import com.yezhihun.demo.entity.template.EquipTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by tianye on 2019/5/27.
 */
@Service
@Transactional
public class EquipTemplateServiceImpl extends AbstractBaseServiceImpl<EquipTemplate> implements EquipTemplateService{
    @Autowired
    private EquipTemplateDao equipTemplateDao;

    @Override
    public void init() {
        this.baseDao = equipTemplateDao;
    }


    @Override
    public List<EquipTemplate> findByLevel(int level) {
        return equipTemplateDao.findByLevel(level);
    }
}
