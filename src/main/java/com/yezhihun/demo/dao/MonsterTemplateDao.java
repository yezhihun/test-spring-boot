package com.yezhihun.demo.dao;

import com.yezhihun.demo.entity.template.MonsterTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by tianye on 2019/5/27.
 */
@Repository
public interface MonsterTemplateDao extends BaseDao<MonsterTemplate>{

    List<MonsterTemplate> findByLevel(int level);
}
