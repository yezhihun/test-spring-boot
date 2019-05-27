package com.yezhihun.demo.dao;

import com.yezhihun.demo.template.EquipTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by tianye on 2019/5/27.
 */
@Repository
public interface EquipTemplateDao extends BaseDao<EquipTemplate>{

    List<EquipTemplate> findByLevel(int level);
}
