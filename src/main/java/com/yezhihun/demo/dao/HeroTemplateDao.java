package com.yezhihun.demo.dao;

import com.yezhihun.demo.entity.template.HeroTemplate;
import com.yezhihun.demo.enums.Occupation;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by tianye on 2019/5/27.
 */
@Repository
public interface HeroTemplateDao extends BaseDao<HeroTemplate>{

    List<HeroTemplate> findByOccupation(Occupation occupation);
}
