package com.yezhihun.demo.service;

import com.yezhihun.demo.entity.template.MonsterTemplate;

import java.util.List;

/**
 * Created by tianye on 2019/5/27.
 */
public interface MonsterTemplateService extends BaseService<MonsterTemplate>{

    List<MonsterTemplate> selectByLevel(int level);
}
