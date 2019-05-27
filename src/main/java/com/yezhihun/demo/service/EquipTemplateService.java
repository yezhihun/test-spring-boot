package com.yezhihun.demo.service;

import com.yezhihun.demo.template.EquipTemplate;

import java.util.List;

/**
 * Created by tianye on 2019/5/27.
 */
public interface EquipTemplateService extends BaseService<EquipTemplate>{

    List<EquipTemplate> findByLevel(int level);
}
