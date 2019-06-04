package com.yezhihun.demo.service;

import com.yezhihun.demo.entity.template.HeroTemplate;
import com.yezhihun.demo.enums.Occupation;

import java.util.List;

/**
 * Created by tianye on 2019/5/27.
 */
public interface HeroTemplateService extends BaseService<HeroTemplate>{

    List<HeroTemplate> selectAllByOccupation(Occupation occupation);

    HeroTemplate selectOneByOccupation(Occupation occupation);
}
